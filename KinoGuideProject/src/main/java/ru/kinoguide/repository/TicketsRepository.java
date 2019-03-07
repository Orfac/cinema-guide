package ru.kinoguide.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kinoguide.entity.Session;
import ru.kinoguide.entity.Ticket;

import java.util.List;

public interface TicketsRepository extends CrudRepository<Ticket, Integer> {
    public List<Ticket> findTicketsBySessionAndPriceLessThanEqual(Session session, double price);
}
