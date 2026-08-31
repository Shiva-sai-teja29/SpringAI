package com.rag.SpringAI.Tools.service;

import com.rag.SpringAI.Tools.entity.HelpDeskTicket;
import com.rag.SpringAI.Tools.model.TicketRequest;
import com.rag.SpringAI.Tools.repository.HelpDeskTicketRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HelpDeskTicketService {

    private final HelpDeskTicketRepo helpDeskTicketRepo;

    public HelpDeskTicket createTicket(TicketRequest ticketInput, String username) {
        HelpDeskTicket ticket = HelpDeskTicket.builder()
                .issue(ticketInput.issue())
                .username(username)
                .status("OPEN")
                .createdAt(LocalDateTime.now())
                .eta(LocalDateTime.now().plusDays(7)).build();
        return helpDeskTicketRepo.save(ticket);
    }

    public List<HelpDeskTicket> getTicketByUsername(String username) {
        return helpDeskTicketRepo.findByUsername(username);
    }
}
