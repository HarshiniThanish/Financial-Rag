# Financial-Rag


# Financial RAG (Spring Boot + Spring AI)

Upload financial PDFs, ask questions, and get answers grounded in the document with a hallucination check and confidence score. Low-confidence answers are flagged `needsHumanReview: true` (human-in-the-loop).

## Pipeline
PDF -> text extraction (PDFBox; add Tess4J for scanned/OCR) -> chunking -> embeddings -> vector store -> retrieval -> grounded prompt -> LLM -> evaluation (number/word grounding -> confidence) -> review flag

