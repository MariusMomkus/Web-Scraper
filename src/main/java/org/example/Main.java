package org.example;

public class Main {
    public static void main(String[] args) {
        String url = "http://homeworktask.infare.lt/";
        String csvFilePath = "flight_combinations.csv";

        JsonScraper scraper = new JsonScraper();

        // Define multiple search parameter sets
        String[][] searchParameters = {
                {"MAD", "AUH", "2023-07-01", "2023-07-07", "LHR"}, // Origin, Destination, Outbound Date, Inbound Date, Connection Airport
                {"JFK", "LHR", "2023-08-01", "2023-08-07", "CDG"},
                {"CDG", "FRA", "2023-09-01", "2023-09-07", "AMS"},
                {"CPH", "MAD", "2023-10-01", "2023-10-07", "FRA"},
                {"LAX", "SYD", "2023-11-01", "2023-11-07", "HKG"},
                {"AMS", "BCN", "2023-12-01", "2023-12-07", "CDG"},
                {"ORD", "NRT", "2024-01-01", "2024-01-07", "HKG"},
                {"IST", "DXB", "2024-02-01", "2024-02-07", "CDG"},
                {"HKG", "SIN", "2024-03-01", "2024-03-07", "KUL"},
                {"DEL", "BOM", "2024-04-01", "2024-04-07", "DXB"}
        };

        // Loop through the search parameter sets and save flight combinations for each set
        for (String[] params : searchParameters) {
            String origin = params[0];
            String destination = params[1];
            String outboundDate = params[2];
            String inboundDate = params[3];
            String connectionAirport = params[4];

            // Get flight combinations and save to CSV
            scraper.getFlightCombinations(url, csvFilePath, origin, destination, outboundDate, inboundDate, connectionAirport);
        }
    }
}









