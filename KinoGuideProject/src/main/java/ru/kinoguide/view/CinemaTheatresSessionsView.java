package ru.kinoguide.view;

import ru.kinoguide.entity.CinemaTheatre;
import ru.kinoguide.entity.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CinemaTheatresSessionsView {

    private int page;

    private final int entitiesPerPage;

    private final int numberOfPages;

    private final List<CinemaTheatre> cinemaTheatreList = new ArrayList<>();

    private final Map<CinemaTheatre, List<Session>> cinemaHallSessionListMap = new HashMap<>();


    public CinemaTheatresSessionsView(List<Session> sessions, int page, int theatresPerPage) {
        this.page = page;
        this.entitiesPerPage = theatresPerPage;
        for (Session session : sessions) {
            List<Session> sessionList;
            if (!cinemaTheatreList.contains(session.getCinemaHall().getCinemaTheatre())) {
                cinemaTheatreList.add(session.getCinemaHall().getCinemaTheatre());
                sessionList = new ArrayList<>();
                cinemaHallSessionListMap.put(session.getCinemaHall().getCinemaTheatre(), sessionList);
            } else {
                sessionList = cinemaHallSessionListMap.get(session.getCinemaHall().getCinemaTheatre());
            }
            sessionList.add(session);
        }
        this.numberOfPages = cinemaTheatreList.size() / theatresPerPage + (cinemaTheatreList.size() % theatresPerPage == 0 ? 0 : 1);
    }


    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public boolean hasPreviousPage() {
        return page > 0;
    }

    public boolean hasNextPage() {
        return page < numberOfPages - 1;
    }

    /**
     * @param cinemaTheatre
     * @return list of sessions for the cinema theatre in the order in which you have stored the elements during the creation of {@code CinemaTheatresSessionsView}
     */
    public List<Session> getSessionsByCinemaTheatre(CinemaTheatre cinemaTheatre) {
        return cinemaHallSessionListMap.get(cinemaTheatre);
    }


    public Iterable<CinemaTheatre> getCinemaTheatres() {
        return getCinemaTheatres(page);
    }

    /**
     * @return cinema theatres for current page
     */
    private Iterable<CinemaTheatre> getCinemaTheatres(int page) {
        int fromIndex = entitiesPerPage * page;
        int toIndex = fromIndex + entitiesPerPage;
        if (toIndex > cinemaTheatreList.size()) {
            toIndex = cinemaTheatreList.size();
        }
        if (fromIndex > toIndex || fromIndex < 0) {
            return new ArrayList<>();
        }

        return cinemaTheatreList.subList(fromIndex, toIndex);
    }

    public Iterable<CinemaTheatre> getAllCinemaTheatres() {
        return cinemaTheatreList;
    }

    /**
     * @return true if there no sessions (and cinema halls respectively), otherwise false
     */
    public boolean isEmpty() {
        return cinemaTheatreList.isEmpty();
    }

}
