/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AMS;

public class Flight {
    private String flightNumber;
    private String origin;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private Aircraft aircraft;
    private Pilot pilot;
    private CrewMember[] crewMembers; // Arrays
    private Booking[] bookings;
    private int bookingCount;
    
    // Static field (Lect#3)
    private static int totalFlights = 0;
    
    public Flight(String flightNumber, String origin, String destination,
                 String departureTime, String arrivalTime, Aircraft aircraft) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.aircraft = aircraft;
        this.crewMembers = new CrewMember[10];
        this.bookings = new Booking[aircraft.getCapacity()];
        this.bookingCount = 0;
        totalFlights++;
    }
    
    public static int getTotalFlights() {
        return totalFlights;
    }
    
    public void assignPilot(Pilot pilot) {
        this.pilot = pilot;
    }
    
    public void addCrewMember(CrewMember crew) {
        for (int i = 0; i < crewMembers.length; i++) {
            if (crewMembers[i] == null) {
                crewMembers[i] = crew;
                break;
            }
        }
    }
    
    public boolean addBooking(Booking booking) {
        if (bookingCount < bookings.length) {
            bookings[bookingCount++] = booking;
            return true;
        }
        return false;
    }
    
    public int getAvailableSeats() {
        return aircraft.getCapacity() - bookingCount;
    }
    
    // Getters with encapsulation
    public String getFlightNumber() { return flightNumber; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public Aircraft getAircraft() { return aircraft; }
    
    @Override
    public String toString() {
        return "Flight " + flightNumber + ": " + origin + " -> " + destination +
               " | Departure: " + departureTime + " | Available Seats: " + 
               getAvailableSeats();
    }
}
