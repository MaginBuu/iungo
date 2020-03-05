package com.service;

import com.dao.TicketDao;
import com.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;

public class ticketServiceImpl {

    @Autowired
    TicketDao ticketDao;

    public void addTicket(Ticket ticket){
        ticketDao.addTicket(ticket);
    }
}
