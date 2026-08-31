package com.rag.SpringAI.Tools.helper;

import com.rag.SpringAI.Tools.entity.HelpDeskTicket;
import com.rag.SpringAI.Tools.model.TicketRequest;
import com.rag.SpringAI.Tools.service.HelpDeskTicketService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HelpDeskTools {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelpDeskTools.class);

    private final HelpDeskTicketService service;

    @Tool(name = "createTicket", description = "create the support ticket")
    String createTicket(@ToolParam(description = "Details to create a Support ticket")TicketRequest ticketRequest, ToolContext toolContext){
        String username = (String) toolContext.getContext().get("username");
        HelpDeskTicket savedTicket = service.createTicket(ticketRequest, username);
        return "Ticket #"+ savedTicket.getId() + " created successfully for user " + savedTicket.getUsername();
    }

    @Tool(description = "Fetch the status of the open tickets based on a given username")
    List<HelpDeskTicket> getTicketStatus(ToolContext toolContext){
        String username = (String) toolContext.getContext().get("username");
        return service.getTicketByUsername(username);
    }

    // returnDirect = true
    // @Tool(name = "createTicket", description = "create the support ticket", returnDirect = true)
}
