package ru.kinoguide.view;

import ru.kinoguide.entity.Film;
import ru.kinoguide.entity.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilmsSessionsView {

    private int page;

    private final int entitiesPerPage;

    private final int numberOfPages;

    private final List<Film> filmList = new ArrayList<>();

    private final Map<Film, List<Session>> filmSessionsMap = new HashMap<>();

    public FilmsSessionsView(List<Session> sessions, int page, int filmsPerPage) {
        this.page = page;
        this.entitiesPerPage = filmsPerPage;
        for (Session session : sessions) {
            List<Session> sessionList;
            if (!filmList.contains(session.getFilm())) {
                filmList.add(session.getFilm());
                sessionList = new ArrayList<>();
                filmSessionsMap.put(session.getFilm(), sessionList);
            } else {
                sessionList = filmSessionsMap.get(session.getFilm());
            }
            sessionList.add(session);
        }
        this.numberOfPages = filmList.size() / filmsPerPage + (filmList.size() % filmsPerPage == 0 ? 0 : 1);
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
     * @param film
     * @return list of sessions for the film in the order in which you have stored the elements during the creation of {@code FilmsSessionsView}
     */
    public List<Session> getSessionsByFilm(Film film) {
        return filmSessionsMap.get(film);
    }


    public Iterable<Film> getFilms() {
        return getFilms(page);
    }

    /**
     * @return film for current page
     */
    private Iterable<Film> getFilms(int page) {
        int fromIndex = entitiesPerPage * page;
        int toIndex = fromIndex + entitiesPerPage;
        if (toIndex > filmList.size()) {
            toIndex = filmList.size();
        }
        if (fromIndex > toIndex || fromIndex < 0) {
            return new ArrayList<>();
        }

        return filmList.subList(fromIndex, toIndex);
    }

    public Iterable<Film> getAllCinemaTheatres() {
        return filmList;
    }

    /**
     * @return true if there no sessions (and cinema halls respectively), otherwise false
     */
    public boolean isEmpty() {
        return filmList.isEmpty();
    }

}
