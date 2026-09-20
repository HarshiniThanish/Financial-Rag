// package com.example.finrag;

// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.multipart.MultipartFile;

// import java.io.IOException;
// import java.util.Map;

// @RestController
// public class FinanceController {
//     private final IngestionService ingestion;
//     private final RagService rag;

//     public FinanceController(IngestionService ingestion, RagService rag) {
//         this.ingestion = ingestion;
//         this.rag = rag;
//     }

//     @PostMapping("/documents")
//     public Map<String, Object> upload(@RequestParam("file") MultipartFile file) throws IOException {
//         return Map.of("file", file.getOriginalFilename(), "chunks", ingestion.ingest(file.getOriginalFilename(), file.getBytes()));
//     }

//     @PostMapping("/ask")
//     public RagService.Result ask(@RequestBody Map<String, String> body) {
//         return rag.ask(body.get("question"));
//     }
// }
