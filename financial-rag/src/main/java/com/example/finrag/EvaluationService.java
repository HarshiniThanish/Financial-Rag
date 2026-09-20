package com.example.finrag;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.*;

/** Lightweight hallucination detection: checks that numbers and words in the answer are grounded in the retrieved context. */
@Service
public class EvaluationService {
    private static final Pattern NUM = Pattern.compile("\\d[\\d,]*\\.?\\d*");
    private final double threshold;

    public EvaluationService(@Value("${finrag.review-threshold}") double threshold) { this.threshold = threshold; }

    public record Evaluation(double confidence, List<String> ungroundedNumbers, boolean needsHumanReview) {}

    public Evaluation evaluate(String answer, String context) {
        String ctx = context.replace(",", "");
        List<String> nums = new ArrayList<>(), ungrounded = new ArrayList<>();
        Matcher m = NUM.matcher(answer);
        while (m.find()) nums.add(m.group().replace(",", ""));
        for (String n : nums) if (!ctx.contains(n)) ungrounded.add(n);
        double numScore = nums.isEmpty() ? 1.0 : 1.0 - (double) ungrounded.size() / nums.size();

        Set<String> ctxWords = new HashSet<>(Arrays.asList(context.toLowerCase().split("\\W+")));
        String[] words = Arrays.stream(answer.toLowerCase().split("\\W+")).filter(w -> w.length() > 3).toArray(String[]::new);
        long hits = Arrays.stream(words).filter(ctxWords::contains).count();
        double lexScore = words.length == 0 ? 0 : (double) hits / words.length;

        double confidence = Math.round((0.6 * numScore + 0.4 * lexScore) * 100) / 100.0;
        boolean review = confidence < threshold || !ungrounded.isEmpty();
        return new Evaluation(confidence, ungrounded, review);
    }
}
