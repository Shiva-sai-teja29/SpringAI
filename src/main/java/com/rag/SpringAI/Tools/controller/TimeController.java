package com.rag.SpringAI.Tools.controller;

import com.rag.SpringAI.RAG.dto.ChatRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@RestController
@RequestMapping("/api/tools")
public class TimeController {

    private final ChatClient chatClient;

    public TimeController(@Qualifier("timeChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @PostMapping("/local-time")
    public ResponseEntity<String> localTime(@RequestBody ChatRequest request) {
        String answer = chatClient.prompt()
                .advisors(a -> a.param(CONVERSATION_ID, request.getUsername()))
                .user(request.getMessage())
                .call().content();
        return ResponseEntity.ok(answer);
    }
}
