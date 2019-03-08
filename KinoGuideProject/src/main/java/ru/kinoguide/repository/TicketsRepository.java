package ru.kinoguide.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kinoguide.entity.Session;
import ru.kinoguide.entity.Ticket;

import java.util.List;

public interface TicketsRepository extends CrudRepository<Ticket, Integer> {
    List<Ticket> findTicketsBySessionAndPriceLessThanEqual(Session session, double price);
    List<Ticket> findTicketsByPriceLessThanEqual(double price);
}
