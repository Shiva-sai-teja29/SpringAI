DROP TABLE IF EXISTS spring_ai_chat_memory;

CREATE TABLE spring_ai_chat_memory (
    conversation_id VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    type VARCHAR(100) NOT NULL,
    "timestamp" TIMESTAMP WITH TIME ZONE NOT NULL,
    sequence_id BIGINT NOT NULL
);