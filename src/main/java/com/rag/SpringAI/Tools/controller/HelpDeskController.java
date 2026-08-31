package com.rag.SpringAI.Tools.controller;

import com.rag.SpringAI.RAG.dto.ChatRequest;
import com.rag.SpringAI.Tools.helper.HelpDeskTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@RestController
@RequestMapping("/api/tools")
public class HelpDeskController {

    private final ChatClient chatClient;
    private final HelpDeskTools helpDeskTools;

    public HelpDeskController(@Qualifier("helpDeskChatClient") ChatClient chatClient,
                              HelpDeskTools helpDeskTools) {
        this.chatClient = chatClient;
        this.helpDeskTools=helpDeskTools;
    }

    @PostMapping("/help-desk")
    public ResponseEntity<String> helpDesk(@RequestBody ChatRequest request) {
        String answer = chatClient.prompt()
                .advisors(a -> a.param(CONVERSATION_ID, request.getUsername()))
                .user(request.getMessage())
                .tools(helpDeskTools)
                .toolContext(Map.of("username", request.getUsername()))
                .call().content();
        return ResponseEntity.ok(answer);
    }
}
