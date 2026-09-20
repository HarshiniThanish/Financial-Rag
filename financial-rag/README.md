# Financial RAG (Spring Boot + Spring AI)

Upload financial PDFs, ask questions, and get answers grounded in the document with a hallucination check and confidence score. Low-confidence answers are flagged `needsHumanReview: true` (human-in-the-loop).

## Pipeline
PDF -> text extraction (PDFBox; add Tess4J for scanned/OCR) -> chunking -> embeddings -> vector store -> retrieval -> grounded prompt -> LLM -> evaluation (number/word grounding -> confidence) -> review flag

## Run
```bash
export OPENAI_API_KEY=sk-...
mvn spring-boot:run

curl -F "file=@report.pdf" localhost:8080/documents
curl -X POST localhost:8080/ask -H "Content-Type: application/json" \
     -d '{"question":"What was total revenue in FY2024?"}'
```

Example response:
```json
{"answer":"Total revenue was $4,200,000.","sources":["report.pdf#3"],
 "evaluation":{"confidence":0.92,"ungroundedNumbers":[],"needsHumanReview":false}}
```

## Next steps
Tess4J OCR, pgvector, LLM-as-judge evaluation, review queue UI.
