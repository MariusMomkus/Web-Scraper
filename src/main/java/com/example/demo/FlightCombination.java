package com.example.demo;

public class FlightCombination {
    private Flight outboundFlight;
    private Flight inboundFlight;
    private double totalPrice;
    private double totalTaxes;

    public FlightCombination() {
    }

    // Add getter and setter methods

    public Flight getOutboundFlight() {
        return outboundFlight;
    }

    public void setOutboundFlight(Flight outboundFlight) {
        this.outboundFlight = outboundFlight;
    }

    public Flight getInboundFlight() {
        return inboundFlight;
    }

    public void setInboundFlight(Flight inboundFlight) {
        this.inboundFlight = inboundFlight;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalTaxes() {
        return totalTaxes;
    }

    public void setTotalTaxes(double totalTaxes) {
        this.totalTaxes = totalTaxes;
    }

    public void setPrice(double v) {
    }

    public String getPrice() {
        return null;
    }

    public String getTaxes() {
        return null;
    }
}
