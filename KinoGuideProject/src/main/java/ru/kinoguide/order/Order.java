package ru.kinoguide.order;

import ru.kinoguide.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Table(name = "orders")
@Entity
public class Order extends BaseEntity {
    @ManyToMany()
    private Set<Order> orderSet;
}
