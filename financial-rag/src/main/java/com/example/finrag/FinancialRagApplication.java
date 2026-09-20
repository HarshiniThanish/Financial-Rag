package com.example.finrag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinancialRagApplication {
    public static void main(String[] args) {
        SpringApplication.run(FinancialRagApplication.class, args);
    }

    // @Bean
    // VectorStore vectorStore(EmbeddingModel embeddingModel) {
    //     return SimpleVectorStore.builder(embeddingModel).build(); // in-memory; swap for pgvector in prod
    // }
}
