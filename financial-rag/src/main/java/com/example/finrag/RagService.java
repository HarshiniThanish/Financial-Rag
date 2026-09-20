// package com.example.finrag;

// import org.springframework.ai.chat.client.ChatClient;
// import org.springframework.ai.document.Document;
// // import org.springframework.ai.vectorstore.SearchRequest;
// // import org.springframework.ai.vectorstore.VectorStore;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class RagService {
//     private static final String SYSTEM = """
//         You are a financial document analyst. Answer ONLY from the provided context.
//         If the answer is not in the context, reply exactly: "Not found in the provided documents."
//         Quote figures exactly as written. Be concise.
//         """;

//     private final ChatClient chat;
//     //private final VectorStore store;
//     private final EvaluationService eval;

//     public RagService(ChatClient.Builder builder, VectorStore store, EvaluationService eval) {
//         this.chat = builder.defaultSystem(SYSTEM).build();
//         //this.store = store;
//         this.eval = eval;
//     }

//     public record Result(String answer, List<String> sources, EvaluationService.Evaluation evaluation) {}

//     public Result ask(String question) {
//         //List<Document> docs = store.similaritySearch(SearchRequest.builder().query(question).topK(4).build());
//         //String context = String.join("\n---\n", docs.stream().map(Document::getText).toList());
//         String answer = chat.prompt()
//                 .user(u -> u.text("Context:\n{context}\n\nQuestion: {q}").param("context", context).param("q", question))
//                 .call().content();
//         //List<String> sources = docs.stream().map(d -> d.getMetadata().get("source") + "#" + d.getMetadata().get("chunk")).toList();
//         //return new Result(answer, sources, eval.evaluate(answer, context));
//     }
// }
