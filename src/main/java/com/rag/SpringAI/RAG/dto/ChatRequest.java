package com.rag.SpringAI.RAG.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatRequest {
    private String username;
    private String message;
}
