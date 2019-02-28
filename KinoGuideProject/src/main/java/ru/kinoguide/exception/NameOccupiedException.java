package ru.kinoguide.exception;

public class NameOccupiedException extends Exception {

    private final String name;

    public NameOccupiedException(String name) {
        super("Name " + name + " is already occupied by another user");
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
