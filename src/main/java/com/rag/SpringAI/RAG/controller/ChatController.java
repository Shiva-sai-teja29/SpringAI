package com.rag.SpringAI.RAG.controller;

import com.rag.SpringAI.RAG.dto.ChatRequest;
import com.rag.SpringAI.RAG.dto.ChatResponse;
import com.rag.SpringAI.RAG.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody ChatRequest request) {
        return chatService.chat(request);
    }
}
