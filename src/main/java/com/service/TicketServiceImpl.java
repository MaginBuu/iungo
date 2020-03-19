package com.service;

import com.dao.TicketDao;
import com.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketDao ticketDao;

    public void addTicket(Ticket ticket){
        ticketDao.addTicket(ticket);
    }
    public List<Ticket> getOngoingCreatedTickets() {return ticketDao.getOngoingCreatedTickets(); }
}
