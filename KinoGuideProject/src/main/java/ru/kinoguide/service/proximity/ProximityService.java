package ru.kinoguide.service.proximity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kinoguide.entity.Film;
import ru.kinoguide.entity.User;
import ru.kinoguide.entity.UsersRatingProximity;
import ru.kinoguide.repository.RatingRepository;
import ru.kinoguide.repository.UserRepository;
import ru.kinoguide.repository.UsersRatingProximityRepository;
import ru.kinoguide.view.UserRelatedProximity;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProximityService implements ApplicationListener<ContextRefreshedEvent> {

    private UserRepository userRepository;

    private RatingRepository ratingRepository;

    private UsersRatingProximityRepository usersRatingProximityRepository;

    private LinearUserProximityCalculator userProximityCalculator;


    @Autowired
    public ProximityService(UserRepository userRepository, RatingRepository ratingRepository, UsersRatingProximityRepository usersRatingProximityRepository,
                            @Qualifier("linearUserProximityCalculator") LinearUserProximityCalculator userProximityCalculator) {
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
        this.usersRatingProximityRepository = usersRatingProximityRepository;
        this.userProximityCalculator = userProximityCalculator;
    }

    /**
     * Recalculate proximities using more redundancy info approach that implies less complicated queries to write (two rows per relationship)
     */
    @Scheduled(cron = "0 0 0/12 * * *")
    public void recalculateProximities() {
        usersRatingProximityRepository.deleteAll();
        List<User> users = userRepository.findAll();
        List<UsersRatingProximity> proximities = new ArrayList<>(users.size() * users.size());
        for (int i = 0; i < users.size(); i++) {
            for (int k = i + 1; k < users.size(); k++) {
                double proximity = userProximityCalculator.calculateProximityByCommonRatings(ratingRepository.findCommonRates(users.get(i), users.get(k)));
                proximities.add(new UsersRatingProximity(users.get(i), users.get(k), proximity));
                proximities.add(new UsersRatingProximity(users.get(k), users.get(i), proximity));
            }
        }
        usersRatingProximityRepository.save(proximities);
    }

    /**
     * Proximities recalculation on start of application
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recalculateProximities();
    }

    /**
     * @return List of objects which contain info about one of the users who has similar film favours (high proximity) and his rate for the specified film
     */
    public List<UserRelatedProximity> getUsersWithClosestProximityByFilm(User user, Film film, int resultsNumber) {
        List<UserRelatedProximity> userRelatedProximityList = new ArrayList<>();
        List<UsersRatingProximity> proximityList = usersRatingProximityRepository.getUsersWithClosestProximityByFilm(user.getId(), film.getId(), resultsNumber);

        for (UsersRatingProximity ratingProximity : proximityList) {
            UserRelatedProximity userRelatedProximity = new UserRelatedProximity(ratingProximity, film);
            userRelatedProximityList.add(userRelatedProximity);
        }
        return userRelatedProximityList;
    }
}
