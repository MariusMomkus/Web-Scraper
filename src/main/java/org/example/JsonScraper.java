package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonScraper {
    public String fetchJsonData(String url) {
        try {
            // Fetch the webpage and parse it into a Document object
            Document doc = Jsoup.connect(url).get();

            // Extract the JSON data from the script tag
            Element scriptElement = doc.selectFirst("script[type=application/ld+json]");
            if (scriptElement != null) {
                String jsonData = scriptElement.html();
                return jsonData;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getFlightCombinations(String jsonData, String csvFilePath, String origin, String destination, String outboundDate, String inboundDate, String connectionAirport) {
        try {
            // Parse the provided JSON data into a Document object
            Document doc = Jsoup.parse(jsonData);

            // Extract outbound flight data
            Element outboundContainer = doc.selectFirst(".flights-container .flight-out");
            if (outboundContainer == null) {
                System.out.println("No outbound flights found.");
                return;
            }
            Elements outboundFlights = outboundContainer.select(".flight-info");

            // Extract inbound flight data
            Element inboundContainer = doc.selectFirst(".flights-container .flight-return");
            if (inboundContainer == null) {
                System.out.println("No inbound flights found.");
                return;
            }
            Elements inboundFlights = inboundContainer.select(".flight-info");

            // Extract price and tax data
            Elements priceElements = doc.select(".price-category .price");
            Elements taxElements = doc.select(".price-category .tax");

            if (outboundFlights.size() != inboundFlights.size() || outboundFlights.isEmpty()) {
                System.out.println("No flight combinations found.");
                return;
            }

            // Create CSV file writer
            FileWriter csvWriter = new FileWriter(csvFilePath);
            csvWriter.append("Outbound Flight");
            csvWriter.append(",");
            csvWriter.append("Inbound Flight");
            csvWriter.append(",");
            csvWriter.append("Price");
            csvWriter.append(",");
            csvWriter.append("Tax");
            csvWriter.append("\n");

            double cheapestPrice = Double.MAX_VALUE;

            for (int i = 0; i < outboundFlights.size(); i++) {
                Element outboundFlight = outboundFlights.get(i);
                Element inboundFlight = inboundFlights.get(i);
                String price = priceElements.get(i).text();
                String tax = taxElements.get(i).text();

                // Skip flights with 2 connections
                if (!outboundFlight.text().contains("2 connections") && !inboundFlight.text().contains("2 connections")) {
                    // Check if the flight has the specified connection airport or is a direct flight
                    if (outboundFlight.text().contains(connectionAirport) || outboundFlight.text().contains("Direct")) {
                        double currentPrice = Double.parseDouble(price);

                        if (currentPrice < cheapestPrice) {
                            cheapestPrice = currentPrice;
                        }

                        csvWriter.append(outboundFlight.text());
                        csvWriter.append(",");
                        csvWriter.append(inboundFlight.text());
                        csvWriter.append(",");
                        csvWriter.append(price);
                        csvWriter.append(",");
                        csvWriter.append(tax);
                        csvWriter.append("\n");
                    }
                }
            }

            // Write the cheapest price to the CSV file
            csvWriter.append("Cheapest Price");
            csvWriter.append(",,");
            csvWriter.append(String.valueOf(cheapestPrice));
            csvWriter.append("\n");

            // Close CSV writer
            csvWriter.flush();
            csvWriter.close();

            System.out.println("Flight combinations saved to CSV file: " + csvFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}