/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AMS;
public class AirlineSystemDemo {
    public static void main(String[] args) {
        // Create management systems using generics
        AirlineManagementSystem<Flight> flightSystem = 
            new AirlineManagementSystem<>("Flight Management");
        AirlineManagementSystem<Passenger> passengerSystem = 
            new AirlineManagementSystem<>("Passenger Management");
        AirlineManagementSystem<Employee> employeeSystem = 
            new AirlineManagementSystem<>("Employee Management");
        
        // Create aircraft
        Aircraft boeing737 = new Aircraft("N12345", "737-800", "Boeing", 
                                         189, 5400, "Narrow-body");
        Aircraft airbusA320 = new Aircraft("N67890", "A320", "Airbus", 
                                          180, 6100, "Narrow-body");
        
        // Create pilots (final class, arrays)
        String[] certifications1 = {"Boeing 737", "Boeing 747"};
        Pilot pilot1 = new Pilot("P001", "John Smith", "john@airline.com",
                                "555-0101", "EMP001", 120000, "PL12345",
                                certifications1);
        
        // Create crew members (arrays)
        String[] languages1 = {"English", "Spanish", "French"};
        CrewMember crew1 = new CrewMember("C001", "Sarah Johnson", 
                                         "sarah@airline.com", "555-0102",
                                         "EMP002", 45000, "Senior Attendant",
                                         languages1);
        
        String[] languages2 = {"English", "German"};
        CrewMember crew2 = new CrewMember("C002", "Mike Davis",
                                         "mike@airline.com", "555-0103",
                                         "EMP003", 40000, "Flight Attendant",
                                         languages2);
        
        // Add employees to system (Polymorphism - dynamic binding)
        employeeSystem.add(pilot1);
        employeeSystem.add(crew1);
        employeeSystem.add(crew2);
        
        // Create flights
        Flight flight1 = new Flight("AA101", "New York", "London",
                                   "10:00", "22:00", boeing737);
        flight1.assignPilot(pilot1);
        flight1.addCrewMember(crew1);
        flight1.addCrewMember(crew2);
        
        Flight flight2 = new Flight("AA202", "Los Angeles", "Tokyo",
                                   "14:00", "18:00+1", airbusA320);
        
        flightSystem.add(flight1);
        flightSystem.add(flight2);
        
        // Create passengers
        Passenger passenger1 = new Passenger("PASS001", "Alice Brown",
                                            "alice@email.com", "555-1001",
                                            "X12345678", "USA");
        Passenger passenger2 = new Passenger("PASS002", "Bob Wilson",
                                            "bob@email.com", "555-1002",
                                            "Y87654321", "UK");
        
        passengerSystem.add(passenger1);
        passengerSystem.add(passenger2);
        
        // Create bookings using generics
        Booking<Passenger> booking1 = new Booking<>(passenger1, flight1, 
                                                     "12A", 850.00);
        Booking<Passenger> booking2 = new Booking<>(passenger2, flight1,
                                                     "12B", 850.00);
        
        flight1.addBooking(booking1);
        flight1.addBooking(booking2);
        
        // Add miles to passenger
        passenger1.addMiles(3500);
        passenger2.addMiles(3500);
        
        // Display system information (Polymorphism demonstration)
        System.out.println("========================================");
        System.out.println("   AIRLINE MANAGEMENT SYSTEM");
        System.out.println("========================================");
        
        flightSystem.displayAll();
        passengerSystem.displayAll();
        employeeSystem.displayAll();
        
        // Display bookings
        System.out.println("\n=== Bookings ===");
        System.out.println(booking1);
        System.out.println(booking2);
        
        // Static methods demonstration
        System.out.println("\n=== Statistics ===");
        System.out.println("Total Passengers: " + Passenger.getTotalPassengers());
        System.out.println("Total Flights: " + Flight.getTotalFlights());
        
        // Polymorphism: calling abstract methods
        System.out.println("\n=== Employee Bonuses ===");
        for (int i = 0; i < employeeSystem.size(); i++) {
            Employee emp = employeeSystem.get(i);
            System.out.println(emp.getName() + " (" + emp.getRole() + 
                             ") - Bonus: $" + emp.calculateBonus());
        }
        
        // Demonstrating polymorphism with Person array
        System.out.println("\n=== All People in System (Polymorphism) ===");
        Person[] allPeople = {pilot1, crew1, crew2, passenger1, passenger2};
        for (Person person : allPeople) {
            // Dynamic binding - calls appropriate getRole() at runtime
            System.out.println(person.getName() + " - Role: " + person.getRole());
        }
    }
}
