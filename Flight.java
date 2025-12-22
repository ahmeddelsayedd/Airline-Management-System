package AMS;

public class Flight {
    private String flightNumber;
    private String departureAirport;
    private String arrivalAirport;
    private String departureTime;
    private String arrivalTime;
    private Aircraft aircraft;
    private Pilot pilot;
    private CrewMember[] crewMembers;
    private int availableSeats;
    
    private static final String[] STATUS_OPTIONS = {"Scheduled", "Boarding", "In Flight", "Landed", "Cancelled"};
    private String status;
    
    public Flight(String flightNumber, String departureAirport, String arrivalAirport,
                 String departureTime, String arrivalTime, Aircraft aircraft, 
                 Pilot pilot, CrewMember[] crewMembers) {
this.flightNumber = flightNumber;
this.departureAirport = departureAirport;
this.arrivalAirport = arrivalAirport;
this.departureTime = departureTime;
this.arrivalTime = arrivalTime;
this.aircraft = aircraft;
this.pilot = pilot;
this.crewMembers = crewMembers;
        this.availableSeats = aircraft.getCapacity();
        this.status = STATUS_OPTIONS[0]; // Default to "Scheduled"
    }
    
    // Getters and setters
    public String getFlightNumber() {
        return flightNumber;
    }
    
    public void setFlightNumber(String flightNumber) {
        // Flight number validation: 2 letters + 1-4 digits
        if (flightNumber == null || flightNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Flight number cannot be empty");
        }
        String cleanFlightNum = flightNumber.trim().toUpperCase();
        if (!cleanFlightNum.matches("[A-Z]{2}\\d{1,4}")) {
            throw new IllegalArgumentException("Flight number must be in format AA123 or similar");
        }
        this.flightNumber = cleanFlightNum;
    }
    
    public String getDepartureAirport() {
        return departureAirport;
    }
    
    public void setDepartureAirport(String departureAirport) {
        // Airport code validation: 3 letters
        if (departureAirport == null || departureAirport.trim().isEmpty()) {
            throw new IllegalArgumentException("Departure airport cannot be empty");
        }
        String cleanAirport = departureAirport.trim().toUpperCase();
        if (!cleanAirport.matches("[A-Z]{3}")) {
            throw new IllegalArgumentException("Airport code must be 3 letters (e.g., CAI, JFK)");
        }
        this.departureAirport = cleanAirport;
    }
    
    public String getArrivalAirport() {
        return arrivalAirport;
    }
    
    public void setArrivalAirport(String arrivalAirport) {
        // Airport code validation: 3 letters, cannot be same as departure
        if (arrivalAirport == null || arrivalAirport.trim().isEmpty()) {
            throw new IllegalArgumentException("Arrival airport cannot be empty");
        }
        String cleanAirport = arrivalAirport.trim().toUpperCase();
        if (!cleanAirport.matches("[A-Z]{3}")) {
            throw new IllegalArgumentException("Airport code must be 3 letters (e.g., CAI, JFK)");
        }
        if (cleanAirport.equals(this.departureAirport)) {
            throw new IllegalArgumentException("Arrival airport cannot be same as departure airport");
        }
        this.arrivalAirport = cleanAirport;
    }
    
    public String getDepartureTime() {
        return departureTime;
    }
    
    public void setDepartureTime(String departureTime) {
        // Time validation: HH:MM format
        if (departureTime == null || departureTime.trim().isEmpty()) {
            throw new IllegalArgumentException("Departure time cannot be empty");
        }
        if (!departureTime.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) {
            throw new IllegalArgumentException("Departure time must be in HH:MM format (24-hour)");
        }
        this.departureTime = departureTime;
    }
    
    public String getArrivalTime() {
        return arrivalTime;
    }
    
    public void setArrivalTime(String arrivalTime) {
        // Time validation: HH:MM format
        if (arrivalTime == null || arrivalTime.trim().isEmpty()) {
            throw new IllegalArgumentException("Arrival time cannot be empty");
        }
        if (!arrivalTime.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) {
            throw new IllegalArgumentException("Arrival time must be in HH:MM format (24-hour)");
        }
        this.arrivalTime = arrivalTime;
    }
    
    public Aircraft getAircraft() {
        return aircraft;
    }
    
    public void setAircraft(Aircraft aircraft) {
        if (aircraft == null) {
            throw new IllegalArgumentException("Aircraft cannot be null");
        }
        this.aircraft = aircraft;
    }
    
    public Pilot getPilot() {
        return pilot;
    }
    
    public void setPilot(Pilot pilot) {
        if (pilot == null) {
            throw new IllegalArgumentException("Pilot cannot be null");
        }
        this.pilot = pilot;
    }
    
    public CrewMember[] getCrewMembers() {
        return crewMembers;
    }
    
    public void setCrewMembers(CrewMember[] crewMembers) {
        // At least 2 crew members required for a flight
        if (crewMembers == null || crewMembers.length < 2) {
            throw new IllegalArgumentException("Flight must have at least 2 crew members");
        }
        this.crewMembers = crewMembers;
    }
    
    public int getAvailableSeats() {
        return availableSeats;
    }
    
    public void setAvailableSeats(int availableSeats) {
        // Available seats cannot exceed aircraft capacity
        if (availableSeats < 0) {
            throw new IllegalArgumentException("Available seats cannot be negative");
        }
        if (availableSeats > aircraft.getCapacity()) {
            throw new IllegalArgumentException("Available seats cannot exceed aircraft capacity");
        }
        this.availableSeats = availableSeats;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(int statusIndex) {
        if (statusIndex < 0 || statusIndex >= STATUS_OPTIONS.length) {
            throw new IllegalArgumentException("Invalid status index. Must be between 0 and " + (STATUS_OPTIONS.length - 1));
        }
        this.status = STATUS_OPTIONS[statusIndex];
    }
    
    // Static method to get all status options
    public static String[] getStatusOptions() {
        return STATUS_OPTIONS;
    }
    
    public boolean bookSeat() {
        if (availableSeats > 0) {
            availableSeats--;
            return true;
        }
        return false;
    }
    
    public void cancelSeat() {
        if (availableSeats < aircraft.getCapacity()) {
            availableSeats++;
        }
    }
    
    @Override
    public String toString() {
        return "Flight " + flightNumber + ": " + departureAirport + " → " + arrivalAirport +
               " (" + departureTime + " - " + arrivalTime + "), Status: " + status +
               ", Available Seats: " + availableSeats + "/" + aircraft.getCapacity();
    }
}
