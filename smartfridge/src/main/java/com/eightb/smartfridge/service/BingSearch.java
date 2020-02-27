package com.eightb.smartfridge.service;

import com.microsoft.azure.cognitiveservices.search.websearch.BingWebSearchAPI;
import com.microsoft.azure.cognitiveservices.search.websearch.BingWebSearchManager;
import com.microsoft.azure.cognitiveservices.search.websearch.models.SearchResponse;
import com.microsoft.azure.cognitiveservices.search.websearch.models.WebPage;

public class BingSearch {
    private static final String subscriptionKey = "9cf2786324a64dd9809cf07652991a57";

    public static void preformSearch(String queryString) {
        BingWebSearchAPI client = BingWebSearchManager.authenticate(subscriptionKey);
        runSample(client, queryString);
    }

    public static String searchNearbyGroceryStores (String location) {
        BingWebSearchAPI client = BingWebSearchManager.authenticate(subscriptionKey);

        String queryString = "grocery stores near " + location;
        StringBuilder sb = new StringBuilder();
        sb.append("The nearest grocery stores are: \n");


        SearchResponse webData = client.bingWebs().search()
                .withQuery(queryString)
                .withMarket("en-us")
                .withCount(10)
                .execute();

        if (webData != null && webData.webPages() != null && webData.webPages().value() != null &&
                webData.webPages().value().size() > 0) {
            // find the first web page
            for (int i = 0; i < 3; i++) {
                WebPage webPageResult = webData.webPages().value().get(i);
                if (webPageResult == null) {
                    break;
                }
                sb.append(i+1).append(". ").append(webPageResult.name()).append("\n").append(webPageResult.url()).append("\n");
            }
        } else {
            sb.append("No nearby grocery locations found :(");
        }
        return sb.toString();
    }


    private static void runSample(BingWebSearchAPI client, String queryString) {
        try {
            /*
             * Performs a search based on the .withQuery and prints the name and
             * url for the first web pages, image, news, and video result
             * included in the response.
             */
            System.out.println("Searched Web for: " + queryString);
            // Construct the request.
            SearchResponse webData = client.bingWebs().search()
                    .withQuery(queryString)
                    .withMarket("en-us")
                    .withCount(10)
                    .execute();

            /*
             * WebPages
             * If the search response has web pages, the first result's name
             * and url are printed.
             */
            if (webData != null && webData.webPages() != null && webData.webPages().value() != null &&
                    webData.webPages().value().size() > 0) {
                // find the first web page
                WebPage firstWebPagesResult = webData.webPages().value().get(0);

                if (firstWebPagesResult != null) {
                    System.out.println(String.format("Webpage Results#%d", webData.webPages().value().size()));
                    System.out.println(String.format("First web page name: %s ", firstWebPagesResult.name()));
                    System.out.println(String.format("First web page URL: %s ", firstWebPagesResult.url()));
                } else {
                    System.out.println("Couldn't find the first web result!");
                }
            } else {
                System.out.println("Didn't find any web pages...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
