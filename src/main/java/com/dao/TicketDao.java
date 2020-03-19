package com.dao;

import com.model.Ticket;

import java.util.List;

public interface TicketDao {

    void addTicket(Ticket ticket);
    List<Ticket> getOngoingCreatedTickets();
}
