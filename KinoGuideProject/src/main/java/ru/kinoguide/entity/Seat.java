package ru.kinoguide.entity;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"row","column","cinema_hall_id"}))
public class Seat extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "cinema_hall_id", nullable = false) //fetchtype
    private CinemaHall cinemaHall;

    @Column(name = "row", nullable = false)
    private int row;

    @Column(name = "\"column\"", nullable = false)
    private int column;

    public Seat() {
    }

    public Seat(CinemaHall cinemaHall, int row, int column) {
        this.cinemaHall = cinemaHall;
        this.row = row;
        this.column = column;
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
