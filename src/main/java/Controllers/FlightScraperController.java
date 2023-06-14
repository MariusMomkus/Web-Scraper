package Controllers;

import com.example.demo.Flight;
import com.example.demo.FlightCombination;
import com.example.demo.SearchParams;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FlightScraperController {

    @GetMapping("/scrape")
    public void scrapeFlights() {
        List<SearchParams> searchParamsList = buildSearchParams();
        List<FlightCombination> allFlightCombinations = new ArrayList<>();

        for (SearchParams searchParams : searchParamsList) {
            String apiUrl = buildApiUrl(searchParams);
            String jsonResponse = performApiRequest(apiUrl);

            List<Flight> outboundFlights = extractFlightsFromJson(jsonResponse, "outbound");
            List<Flight> inboundFlights = extractFlightsFromJson(jsonResponse, "inbound");

            List<FlightCombination> flightCombinations = makeFlightCombinations(outboundFlights, inboundFlights);
            allFlightCombinations.addAll(flightCombinations);
        }

        calculateTaxes(allFlightCombinations);
        List<FlightCombination> cheapestOptions = findCheapestOptions(allFlightCombinations);

        saveToCSV(allFlightCombinations, "flight_data.csv");
        saveToCSV(cheapestOptions, "cheapest_options.csv");
    }

    private List<SearchParams> buildSearchParams() {
        List<SearchParams> searchParamsList = new ArrayList<>();

        // Add search parameters for MAD to AUH flights
        SearchParams params1 = new SearchParams();
        params1.setOrigin("MAD");
        params1.setDestination("AUH");
        params1.setOutboundDate("2023-06-15");
        params1.setInboundDate("2023-06-22");
        searchParamsList.add(params1);

        // Add more search parameter sets as needed

        return searchParamsList;
    }

    private String buildApiUrl(SearchParams searchParams) {
        // Build the API URL based on the search parameters
        String baseUrl = "http://homeworktask.infare.lt/flights?";
        String origin = "origin=" + searchParams.getOrigin();
        String destination = "destination=" + searchParams.getDestination();
        String outboundDate = "outboundDate=" + searchParams.getOutboundDate();
        String inboundDate = "inboundDate=" + searchParams.getInboundDate();

        return baseUrl + origin + "&" + destination + "&" + outboundDate + "&" + inboundDate;
    }

    private String performApiRequest(String apiUrl) {
        // Implement logic to perform the API request and retrieve the JSON response
        // Use HttpClient or any other library for making HTTP requests

        return ""; // Placeholder, replace with actual API request logic
    }

    private List<Flight> extractFlightsFromJson(String jsonResponse, String flightType) {
        // Implement logic to parse the JSON response and extract flight data
        // Use a JSON parsing library like Jackson or Gson

        List<Flight> flights = new ArrayList<>();

        // Placeholder logic, replace with actual JSON parsing and extraction
        if (flightType.equals("outbound")) {
            Flight flight1 = new Flight();
            flight1.setAirline("Airline 1");
            flight1.setFlightNumber("123");
            flight1.setDepartureAirport("MAD");
            flight1.setArrivalAirport("AUH");
            flight1.setDepartureDateTime("2023-06-15 10:00");
            flight1.setArrivalDateTime("2023-06-15 15:00");
            flights.add(flight1);

            Flight flight2 = new Flight();
            flight2.setAirline("Airline 2");
            flight2.setFlightNumber("456");
            flight2.setDepartureAirport("MAD");
            flight2.setArrivalAirport("AUH");
            flight2.setDepartureDateTime("2023-06-15 12:00");
            flight2.setArrivalDateTime("2023-06-15 17:00");
            flights.add(flight2);
        } else if (flightType.equals("inbound")) {
            Flight flight1 = new Flight();
            flight1.setAirline("Airline 1");
            flight1.setFlightNumber("789");
            flight1.setDepartureAirport("AUH");
            flight1.setArrivalAirport("MAD");
            flight1.setDepartureDateTime("2023-06-22 09:00");
            flight1.setArrivalDateTime("2023-06-22 13:00");
            flights.add(flight1);

            Flight flight2 = new Flight();
            flight2.setAirline("Airline 2");
            flight2.setFlightNumber("012");
            flight2.setDepartureAirport("AUH");
            flight2.setArrivalAirport("MAD");
            flight2.setDepartureDateTime("2023-06-22 11:00");
            flight2.setArrivalDateTime("2023-06-22 15:00");
            flights.add(flight2);
        }

        return flights;
    }

    private List<FlightCombination> makeFlightCombinations(List<Flight> outboundFlights, List<Flight> inboundFlights) {
        // Implement logic to make flight combinations for each price category
        // For example, combine outbound and inbound flights based on price categories

        List<FlightCombination> flightCombinations = new ArrayList<>();

        // Placeholder logic, replace with actual flight combination logic
        for (Flight outboundFlight : outboundFlights) {
            for (Flight inboundFlight : inboundFlights) {
                FlightCombination combination = new FlightCombination();
                combination.setOutboundFlight(outboundFlight);
                combination.setInboundFlight(inboundFlight);
                combination.setPrice(1000.0); // Placeholder price
                flightCombinations.add(combination);
            }
        }

        return flightCombinations;
    }

    private void calculateTaxes(List<FlightCombination> flightCombinations) {
        // Implement logic to calculate taxes for each flight combination
        // Update the taxes field of each FlightCombination object
    }

    private List<FlightCombination> findCheapestOptions(List<FlightCombination> flightCombinations) {
        // Implement logic to find the cheapest price option for each flight combination
        // Return a list of FlightCombination objects representing the cheapest options
        return new ArrayList<>(); // Placeholder, replace with actual logic
    }

    private void saveToCSV(List<FlightCombination> flightCombinations, String filePath) {
        // Implement logic to save the flight data to a CSV file
        // Use a CSV library like OpenCSV or Apache Commons CSV

        try (FileWriter writer = new FileWriter(filePath)) {
            // Placeholder logic, replace with actual CSV writing logic
            for (FlightCombination combination : flightCombinations) {
                String line = combination.getOutboundFlight().getAirline() + ","
                        + combination.getOutboundFlight().getFlightNumber() + ","
                        + combination.getOutboundFlight().getDepartureAirport() + ","
                        + combination.getOutboundFlight().getArrivalAirport() + ","
                        + combination.getOutboundFlight().getDepartureDateTime() + ","
                        + combination.getOutboundFlight().getArrivalDateTime() + ","
                        + combination.getInboundFlight().getAirline() + ","
                        + combination.getInboundFlight().getFlightNumber() + ","
                        + combination.getInboundFlight().getDepartureAirport() + ","
                        + combination.getInboundFlight().getArrivalAirport() + ","
                        + combination.getInboundFlight().getDepartureDateTime() + ","
                        + combination.getInboundFlight().getArrivalDateTime() + ","
                        + combination.getPrice() + ","
                        + combination.getTaxes();

                writer.write(line);
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


