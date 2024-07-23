package com.emmanuelbernard.test.ollama;

import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Emmanuel Bernard emmanuel@hibernate.org
 */
public class InMemoryEmbeddingStoreProvider {
    public static final String EMBEDDING_STORE_FILE = "./embedding-store.json";
    @Produces
    @ApplicationScoped
    public InMemoryEmbeddingStore create() {
        return new InMemoryEmbeddingStore<>();
    }
}
