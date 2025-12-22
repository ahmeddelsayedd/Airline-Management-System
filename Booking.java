package AMS;

import java.util.Date;

/**
 * Booking class demonstrating BOUNDED GENERICS and polymorphism.
 * @param <T> Type of booking - MUST extend Person class
 * This ensures only Person or its subclasses (Passenger, Pilot, CrewMember) can book
 */
public class Booking<T extends Person> {
    private String bookingId;
    private T bookedBy; // Generic type bounded to Person and its subclasses
    private Flight flight;
    private Date bookingDate;
    private double price;
    private String seatNumber;
    
    // Static counter for booking IDs
    private static int bookingCounter = 10000;
    
    /**
     * Constructor using bounded generics
     * Only accepts objects that extend Person class
     */
    public Booking(T bookedBy, Flight flight, double price, String seatNumber) {
        this.bookingId = "BKG" + (bookingCounter++);
        setBookedBy(bookedBy);    
        setFlight(flight);   
        this.bookingDate = new Date();
        setPrice(price);        
        setSeatNumber(seatNumber); 
    }
    
    // Getters and setters
    public String getBookingId() {
        return bookingId;
    }
    
    public T getBookedBy() {
        return bookedBy; // Returns Person or its subclass - demonstrates dynamic binding
    }
    
    public void setBookedBy(T bookedBy) {
        if (bookedBy == null) {
            throw new IllegalArgumentException("Booked by cannot be null");
        }
        this.bookedBy = bookedBy;
    }
    
    public Flight getFlight() {
        return flight;
    }
    
    public void setFlight(Flight flight) {
        if (flight == null) {
            throw new IllegalArgumentException("Flight cannot be null");
        }
        if (flight.getAvailableSeats() <= 0) {
            throw new IllegalArgumentException("Cannot book on a full flight");
        }
        this.flight = flight;
    }
    
    public Date getBookingDate() {
        return bookingDate;
    }
    
    public void setBookingDate(Date bookingDate) {
        if (bookingDate == null) {
            throw new IllegalArgumentException("Booking date cannot be null");
        }
        this.bookingDate = bookingDate;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        // Price validation: positive value, reasonable maximum
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        if (price > 100000) {
            throw new IllegalArgumentException("Price cannot exceed $100,000");
        }
        this.price = price;
    }
    
    public String getSeatNumber() {
        return seatNumber;
    }
    
    public void setSeatNumber(String seatNumber) {
        if (seatNumber == null || seatNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Seat number cannot be empty");
        }
        String cleanSeat = seatNumber.trim().toUpperCase();
        if (!cleanSeat.matches("\\d{1,2}[A-F]")) {
            throw new IllegalArgumentException("Seat number must be in format like 12A, 1C, etc. (Rows 1-99, Seats A-F)");
        }
        this.seatNumber = cleanSeat;
    }
    
    /**
     * Demonstrates polymorphism and dynamic binding
     * The bookedBy.getName() will call the appropriate method at runtime
     */
    public void displayBookingDetails() {
        System.out.println("=== Booking Details ===");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Booked By: " + bookedBy.getName() + " (ID: " + bookedBy.getId() + ")");
        System.out.println("Email: " + bookedBy.getEmail());
        System.out.println("Flight: " + flight.getFlightNumber());
        System.out.println("From: " + flight.getDepartureAirport() + " to " + flight.getArrivalAirport());
        System.out.println("Departure: " + flight.getDepartureTime());
        System.out.println("Seat: " + seatNumber);
        System.out.println("Price: $" + price);
        System.out.println("Booking Date: " + bookingDate);
    }
    
    @Override
    public String toString() {
        return "Booking ID: " + bookingId + ", Flight: " + flight.getFlightNumber() + 
               ", Passenger: " + bookedBy.getName() + ", Seat: " + seatNumber + ", Price: $" + price;
    }
}
