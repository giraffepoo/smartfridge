package com.eightb.smartfridge.service;

import org.junit.jupiter.api.Test;

import static com.eightb.smartfridge.service.BingAnswer.SearchKnowledge;
import static com.eightb.smartfridge.service.BingAnswer.prettify;

public class BingAnswerTest {

    @Test
    void bingExpiryAnswer() throws Exception {
        String searchTerm = "how long do eggs last in the fridge";
        System.out.println("Searching the Web for: " + searchTerm);

        SearchResults result = SearchKnowledge(searchTerm);

        System.out.println("\nRelevant HTTP Headers:\n");
        for (String header : result.relevantHeaders.keySet())
            System.out.println(header + ": " + result.relevantHeaders.get(header));

        System.out.println("\nJSON Response:\n");
        System.out.println(prettify(result.jsonResponse));
    }
}
