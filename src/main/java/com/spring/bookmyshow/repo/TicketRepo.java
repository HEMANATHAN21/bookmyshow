package com.spring.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.bookmyshow.entity.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Integer> {

}
