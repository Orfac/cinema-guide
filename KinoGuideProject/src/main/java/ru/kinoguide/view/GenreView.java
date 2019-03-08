package ru.kinoguide.view;

import ru.kinoguide.entity.Genre;

public class GenreView {

    private boolean selected;

    private Genre genre;


    public GenreView(boolean selected, Genre genre) {
        this.selected = selected;
        this.genre = genre;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getName() {
        return genre.getName();
    }

    public Integer getId() {
        return genre.getId();
    }
}
