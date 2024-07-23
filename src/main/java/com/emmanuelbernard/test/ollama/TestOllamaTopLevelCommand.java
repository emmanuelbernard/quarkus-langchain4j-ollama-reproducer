package com.emmanuelbernard.test.ollama;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import jakarta.inject.Inject;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static dev.langchain4j.data.document.loader.FileSystemDocumentLoader.loadDocument;

@Command(name = "testollama", mixinStandardHelpOptions = true)
public class TestOllamaTopLevelCommand implements Runnable {

    @Inject
    Ingestor ingestor;

    @Inject
    InMemoryEmbeddingStore store;

    @Command(description = "ingest files")
    public void ingest(
            @CommandLine.Option(names = {"--directory", "-d"}, defaultValue = "./")
            String directory
    ) {
        File dir = new File(directory);

        if (dir.isDirectory()) {
            // Filter files that end with .md
            System.out.println(dir.getAbsolutePath());
            File[] mdFiles = dir.listFiles((dir1, name) -> name.endsWith(".md"));

            List<Document> documents = new ArrayList<Document>();
            if (mdFiles != null) {
                Arrays.sort(mdFiles, Comparator.comparingLong(File::lastModified).reversed());
                for (int i = 0; i < Math.min(10, mdFiles.length); i++) {
                    Document document = loadDocument(mdFiles[i].getPath(), new TextDocumentParser());
                    documents.add(document);
                    System.out.println(mdFiles[i].getName());
                }
            } else {
                System.out.println("The directory is empty or an I/O error occurred.");
            }
            ingestor.ingest(documents);
            //TODO read from file
            store.serializeToFile("./embedding-store.json");
        } else {
            System.out.println("The provided path is not a directory: " + directory);
        }
    }

    @Override
    public void run() {
        System.out.printf("Hello\n");
        System.out.println(store.serializeToJson().length());
        ingest("./");
    }

}
