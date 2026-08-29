package com.rag.SpringAI.controller;

import com.rag.SpringAI.dto.ChatRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@RestController
@RequestMapping("/api/rag")
public class RAGController {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    @Value("classpath:promptTemplates/systemPromptRandomDataTemplate.st")
    Resource promptTemplate;

    @Value("classpath:promptTemplates/systemPromptTemplate.st")
    Resource hrSystemTemplate;

    public RAGController(@Qualifier("chatMemoryChatClient") ChatClient chatClient, VectorStore vectorStore) {
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
    }

    @PostMapping("/random/chat")
    public ResponseEntity<String> randomChat(@RequestBody ChatRequest request) {
//        SearchRequest searchRequest = SearchRequest.builder().query(request.getMessage()).topK(3).similarityThreshold(0.5).build();
//        List<Document> similarDoc = vectorStore.similaritySearch(searchRequest);
//        String similarContext = similarDoc.stream().map(Document::getText).collect(Collectors.joining(System.lineSeparator()));
        String answer = chatClient.prompt()
//                .system(promptSystemSpec -> promptSystemSpec.text(promptTemplate).param("documents", similarContext))
                .advisors(a -> a.param(CONVERSATION_ID, request.getUsername())).user(request.getMessage())
                .call().content();
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/document/chat")
    public ResponseEntity<String> documentChat(@RequestBody ChatRequest request) {
//        SearchRequest searchRequest = SearchRequest.builder()
//                .query(request.getMessage())
//                .topK(3).similarityThreshold(0.5).build();
//        List<Document> similarDoc = vectorStore.similaritySearch(searchRequest);
//        String similarContext = similarDoc.stream()
//                .map(Document::getText)
//                .collect(Collectors.joining(System.lineSeparator()));
        String answer = chatClient.prompt()
//                .system(promptSystemSpec -> promptSystemSpec.text(hrSystemTemplate)
//                        .param("documents", similarContext))
                .advisors(a -> a.param(CONVERSATION_ID, request.getUsername())).user(request.getMessage())
                .call().content();
        return ResponseEntity.ok(answer);
    }
}
