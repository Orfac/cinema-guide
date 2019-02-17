package ru.kinoguide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kinoguide.entity.User;
import ru.kinoguide.entity.UsersRatingProximity;
import ru.kinoguide.repository.RatingRepository;
import ru.kinoguide.repository.UserRepository;
import ru.kinoguide.repository.UsersRatingProximityRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ScheduledProximityCalculationTask implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UsersRatingProximityRepository usersRatingProximityRepository;

    @Autowired
    @Qualifier("linearUserProximityCalculator")
    private LinearUserProximityCalculator userProximityCalculator;

    /**
     * Recalculate proximities using more redundancy info approach that implies less complicated queries to write (two rows per relationship)
     */
    @Scheduled(cron = "* * 0/12 * * *")
    public void recalculateProximities() {
        usersRatingProximityRepository.deleteAll();
        List<User> users = userRepository.findAll();
        List<UsersRatingProximity> proximities = new ArrayList<>(users.size() * users.size());
        for (int i = 0; i < users.size(); i++) {
            for (int k = i + 1; k < users.size(); k++) {
                double proximity = userProximityCalculator.calculateProximityByCommonRatings(ratingRepository.findCommonRates(users.get(i), users.get(k)));
                proximities.add( new UsersRatingProximity(users.get(i), users.get(k), proximity));
                proximities.add( new UsersRatingProximity(users.get(k), users.get(i), proximity));
            }
        }
        usersRatingProximityRepository.save(proximities);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recalculateProximities();
    }
}