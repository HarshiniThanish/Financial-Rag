package com.example.finrag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;

/** Extracts text from a PDF, chunks it, and indexes it. For scanned PDFs, plug in Tess4J OCR here. */
@Service
public class IngestionService {
    private static final int CHUNK = 800;
    //private final VectorStore store;

    // public IngestionService(VectorStore store) { this.store = store; }

    public int ingest(String filename, byte[] pdf) throws IOException {
        String text;
        try (PDDocument doc = Loader.loadPDF(pdf)) {
            text = new PDFTextStripper().getText(doc);
        }
        List<Document> chunks = new ArrayList<>();
        for (int i = 0, n = 0; i < text.length(); i += CHUNK, n++) {
            String part = text.substring(i, Math.min(text.length(), i + CHUNK)).trim();
            if (!part.isBlank()) chunks.add(new Document(part, Map.of("source", filename, "chunk", n)));
        }
        //store.add(chunks);
        return chunks.size();
    }
}
