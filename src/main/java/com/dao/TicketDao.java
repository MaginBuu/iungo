package com.dao;

import com.model.Ticket;

import java.util.List;

public interface TicketDao {

    void addTicket(Ticket ticket);
    void updateTicket(Ticket ticket);
    List<Ticket> getOngoingCreatedTickets();
    Ticket getTicketById(String id);
}
