package com.service;

import com.model.Ticket;

import java.util.List;

public interface TaskService {

    void addTicket(Ticket ticket);
    void updateTicket(Ticket ticket);
    List<Ticket> getOngoingCreatedTickets();
    Ticket getTicketById(String id);
}
