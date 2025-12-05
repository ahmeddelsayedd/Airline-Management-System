/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AMS;

public class Booking<T extends Person> { // Generics
    private String bookingId;
    private T passenger; // Generic type
    private Flight flight;
    private String seatNumber;
    private double price;
    private String bookingDate;
    
    private static int bookingCounter = 0;
    
    public Booking(T passenger, Flight flight, String seatNumber, double price) {
        this.bookingId = "BK" + (++bookingCounter);
        this.passenger = passenger;
        this.flight = flight;
        this.seatNumber = seatNumber;
        this.price = price;
        this.bookingDate = java.time.LocalDate.now().toString();
    }
    
    public String getBookingId() { return bookingId; }
    public T getPassenger() { return passenger; }
    public Flight getFlight() { return flight; }
    public double getPrice() { return price; }
    
    @Override
    public String toString() {
        return "Booking ID: " + bookingId + " | Passenger: " + 
               passenger.getName() + " | Flight: " + flight.getFlightNumber() +
               " | Seat: " + seatNumber + " | Price: $" + price;
    }
}
