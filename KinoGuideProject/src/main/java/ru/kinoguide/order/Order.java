package ru.kinoguide.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import ru.kinoguide.BaseEntity;
import ru.kinoguide.user.User;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "orders")
@Entity
public class Order extends BaseEntity {
    //    @ManyToMany()
//    private Set<Order> orderSet;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "date", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "UTC")
    private Instant date;
}
