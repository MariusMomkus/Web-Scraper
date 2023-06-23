package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String url = "http://homeworktask.infare.lt/";
        String csvFilePath = "flight_combinations.csv";

        JsonScraper scraper = new JsonScraper();

        // Scrape JSON data
        String jsonData = scraper.scrapeJsonData(url);
        if (jsonData != null) {
            System.out.println("JSON Data:");
            System.out.println(jsonData);
        } else {
            System.out.println("Failed to scrape JSON data.");
        }

        // Get flight combinations and save to CSV
        scraper.getFlightCombinations(url, csvFilePath);
    }
}







