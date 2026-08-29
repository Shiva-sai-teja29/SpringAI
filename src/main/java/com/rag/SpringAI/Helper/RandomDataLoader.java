package com.rag.SpringAI.Helper;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RandomDataLoader {

    private final VectorStore vectorStore;

    @PostConstruct
    public void loadSentencesIntoVectorStore(){
        List<String> sentences = List.of("Python: Known for simplicity and readability, used in data science, machine learning, and web development.\n" +
                "Java: Platform-independent, popular for enterprise applications and Android development.\n" +
                "JavaScript: Essential for web development, enabling interactive websites.\n" +
                "C++: High-performance language for system programming, games, and real-time applications.\n" +
                "C#: Used for Windows applications and game development with Unity.\n" +
                "Swift: Apple’s language for iOS and macOS apps.\n" +
                "Go: Designed for scalability and cloud computing.\n" +
                "Ruby: Known for web frameworks like Ruby on Rails.");

        List<Document> documents = sentences.stream().map(Document::new).toList();
        vectorStore.add(documents);
    }
}
