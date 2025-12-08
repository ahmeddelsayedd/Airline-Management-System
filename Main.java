package AMS;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * this is the main class that runs the whole airline management system.
 * It loads everything from files when you start it up and keeps it all in memory.
 * Think of it like the brain of the whole operation - it's got the menu system
 * and handles all the big operations like adding passengers, creating bookings, etc
 */
public class Main {
    // These are the file names where we're storing all our data
    //passengers in passengers.txt, flights in flights.txt
    private static final String PASSENGERS_FILE = "passengers.txt";
    private static final String EMPLOYEES_FILE = "employees.txt";
    private static final String AIRCRAFTS_FILE = "aircrafts.txt";
    private static final String FLIGHTS_FILE = "flights.txt";
    private static final String BOOKINGS_FILE = "bookings.txt";
    
    // These ArrayLists store all our data in memory once we load it from the files
    // We use these throughout the program instead of hitting the files every time
    // Way faster and less annoying than constantly reading/writing files
    private static List<Passenger> passengers = new ArrayList<>();
    private static List<Employee> employees = new ArrayList<>();
    private static List<Aircraft> aircrafts = new ArrayList<>();
    private static List<Flight> flights = new ArrayList<>();
    private static List<Booking<Passenger>> bookings = new ArrayList<>();
    
    // Scanner for getting user input from the keyboard
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Main entry point for the whole system.
     * This method runs when you start the program - it loads data and then
     * keeps asking the user what they wanna do until they quit.
     */
    public static void main(String[] args) {
        System.out.println("=== AIRLINE MANAGEMENT SYSTEM ===");
        System.out.println("Loading all data from files...\n");
        
        // Load everything from the files
        // This happens first so all our data is ready to go
        loadAllData();
        
        // Show the user what got loaded used for debugging
        System.out.println("Data loaded successfully:");
        System.out.println("- " + passengers.size() + " passengers");
        System.out.println("- " + employees.size() + " employees");
        System.out.println("- " + aircrafts.size() + " aircrafts");
        System.out.println("- " + flights.size() + " flights");
        System.out.println("- " + bookings.size() + " bookings\n");
        
        // Main loop - keeps running until the user hits exit
        // This is where the user gets to interact with the system
        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();
            
            // Handle whatever the user picks from the menu
            // Pretty basic switch statement - each case handles a different menu option
            switch (choice) {
                case "1": 
                    addPassenger(); 
                    break;
                case "2": 
                    addEmployee(); 
                    break;
                case "3": 
                    viewAllData(); 
                    break;
                case "4": 
                    createBooking(); 
                    break;
                case "5": 
                    viewBookings(); 
                    break;
                case "6": 
                    saveAllData(); 
                    break;
                case "0":
                    // User wants to exit - save data before we bounce
                    System.out.println("Saving data and exiting...");
                    saveAllData();
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        // Clean up the scanner when weare done
        scanner.close();
    }
    
    /**
     * Just prints out the menu so the user knows what they can do.
     * Called every loop iteration so it's always visible.
     */
    private static void displayMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1. Add New Passenger");
        System.out.println("2. Add New Employee");
        System.out.println("3. View All Data");
        System.out.println("4. Create New Booking");
        System.out.println("5. View All Bookings");
        System.out.println("6. Save All Data");
        System.out.println("0. Exit");
    }
    
    /**
     * This method runs through all the loading methods to get everything from files.
     * It's like an orchestrator - it just calls all the load methods in the right order.
     * Makes the main method cleaner and easier to read.
     */
    private static void loadAllData() {
        loadPassengers();
        loadEmployees();
        loadAircrafts();
        loadFlights();
        loadBookings();
    }
    
    /**
     * Loads all the passengers from the passengers.txt file.
     * The file format is: Name,Email,Password,Phone,Passport,Nationality
     * We read line by line and ignore comments (lines that start with #) and blank lines.
     * Each valid line becomes a new Passenger object that we throw in our list.
     */
    private static void loadPassengers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PASSENGERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Clean up the line - remove extra whitespace
                line = line.trim();
                // Skip empty lines and comment lines (they start with #)
                if (line.isEmpty() || line.startsWith("#")) continue;
                
                // Split the line by commas to get the individual fields
                String[] parts = line.split(",");
                // Make sure we got all 6 fields we're expecting
                if (parts.length == 6) {
                    // Create a new passenger with the data from the file
                    Passenger passenger = new Passenger(
                        parts[0].trim(), // name
                        parts[1].trim(), // email
                        parts[2].trim(), // password
                        parts[3].trim(), // phone
                        parts[4].trim(), // passport number
                        parts[5].trim()  // nationality
                    );
                    passengers.add(passenger);
                }
            }
        } catch (IOException e) {
            // If something goes wrong reading the file, just print the error
            // The program keeps running even if this fails
            System.out.println("Error loading passengers: " + e.getMessage());
        }
    }
    
    /**
     * Loads employees from the employees.txt file.
     * Employees can be either Pilots or CrewMembers, so this is a bit more complex.
     * Format: Type,Name,Email,Password,Phone,EmployeeID,Salary,Department,ExtraData
     * The ExtraData part is different depending on whether it's a Pilot or Crew.
     */
    private static void loadEmployees() {
        try (BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                // Skip empty lines and comments
                if (line.isEmpty() || line.startsWith("#")) continue;
                
                // Split by comma to get all the basic employee info
                String[] parts = line.split(",");
                if (parts.length >= 8) {
                    // Get the common fields that every employee has
                    String type = parts[0].trim();
                    String name = parts[1].trim();
                    String email = parts[2].trim();
                    String password = parts[3].trim();
                    String phone = parts[4].trim();
                    String empId = parts[5].trim();
                    double salary = Double.parseDouble(parts[6].trim());
                    String department = parts[7].trim();
                    
                    // Now check what type of employee this is
                    if (type.equals("Pilot")) {
                        // For pilots, the extra data includes license, flight hours, and certifications
                        // All separated by pipes (|)
                        String[] extra = parts[8].split("\\|");
                        String license = extra[0];
                        int hours = Integer.parseInt(extra[1]);
                        // Whatever's left after license and hours are certs
                        String[] certs = new String[extra.length - 2];
                        System.arraycopy(extra, 2, certs, 0, certs.length);
                        
                        // Create the pilot and add to our list
                        Pilot pilot = new Pilot(name, email, password, phone, empId, 
                                               salary, department, license, hours, certs);
                        employees.add(pilot);
                        
                    } else if (type.equals("Crew")) {
                        // For crew members, the extra data is position and languages
                        String[] extra = parts[8].split("\\|");
                        String position = extra[0];
                        // Languages are comma-separated
                        String[] languages = extra[1].split(",");
                        
                        // Create the crew member and add to our list
                        CrewMember crew = new CrewMember(name, email, password, phone, 
                                                        empId, salary, department, 
                                                        position, languages);
                        employees.add(crew);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading employees: " + e.getMessage());
        }
    }
    
    /**
     * Loads all the aircrafts from the aircrafts.txt file.
     * File format: RegistrationNumber,Model,Manufacturer,Capacity,Range
     * each line is one airplane.
     */
    private static void loadAircrafts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(AIRCRAFTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                // Skip empty lines and comments
                if (line.isEmpty() || line.startsWith("#")) continue;
                
                // Split by comma
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    // Create a new aircraft with all the data
                    Aircraft aircraft = new Aircraft(
                        parts[0].trim(),                              // registration number
                        parts[1].trim(),                              // model
                        parts[2].trim(),                              // manufacturer
                        Integer.parseInt(parts[3].trim()),            // capacity (number of seats)
                        Double.parseDouble(parts[4].trim())           // range in km
                    );
                    aircrafts.add(aircraft);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading aircrafts: " + e.getMessage());
        }
    }
    
    /**
     * Loads all the flights from the flights.txt file.
     * File format: FlightNumber,DepartureAirport,ArrivalAirport,DepartureTime,ArrivalTime,AircraftIndex,PilotIndex
     * This is a bit tricky because flights reference both aircraft and pilots by index.
     */
    private static void loadFlights() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FLIGHTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                // Skip empty lines and comments
                if (line.isEmpty() || line.startsWith("#")) continue;
                
                // Split by comma
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    // Get the basic flight info
                    String flightNum = parts[0].trim();
                    String departure = parts[1].trim();
                    String arrival = parts[2].trim();
                    String depTime = parts[3].trim();
                    String arrTime = parts[4].trim();
                    
                    // Get indices for aircraft and pilot from the file
                    int aircraftIndex = Integer.parseInt(parts[5].trim());
                    int pilotIndex = Integer.parseInt(parts[6].trim());
                    
                    // Get the actual aircraft using the index
                    // We use modulo in case the index is out of bounds - makes it wrap around
                    Aircraft aircraft = aircrafts.get(aircraftIndex % aircrafts.size());
                    
                    // Find the first pilot we can find
                    // In a real system, we'd use the pilot index more carefully
                    Pilot pilot = null;
                    for (Employee emp : employees) {
                        if (emp instanceof Pilot) {
                            pilot = (Pilot) emp;
                            break;
                        }
                    }
                    
                    // Create the flight with all this data
                    Flight flight = new Flight(flightNum, departure, arrival, 
                                             depTime, arrTime, aircraft, pilot, 
                                             new CrewMember[0]); // Start with no crew
                    flights.add(flight);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading flights: " + e.getMessage());
        }
    }
    
    /**
     * Loads all the bookings from the bookings.txt file.
     * File format: PassengerIndex,FlightIndex,Seat,Price
     * This links passengers to flights - so when you load a booking,
     * like saying "this passenger is booked on this flight in this seat".
     */
    private static void loadBookings() {
        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKINGS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                // Skip empty lines and comments
                if (line.isEmpty() || line.startsWith("#")) continue;
                
                // Split by comma
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    // Get the indices and other booking info
                    int passengerIndex = Integer.parseInt(parts[0].trim());
                    int flightIndex = Integer.parseInt(parts[1].trim());
                    String seat = parts[2].trim();
                    double price = Double.parseDouble(parts[3].trim());
                    
                    // Create the booking using the actual passenger and flight objects
                    // Again using modulo to wrap around if the indices are weird
                    Booking<Passenger> booking = new Booking<>(
                        passengers.get(passengerIndex % passengers.size()),
                        flights.get(flightIndex % flights.size()),
                        price, 
                        seat
                    );
                    bookings.add(booking);
                    
                    // Tell the flight that a seat got booked
                    // This updates the available seats counter on the flight
                    flights.get(flightIndex % flights.size()).bookSeat();
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading bookings: " + e.getMessage());
        }
    }
    
    /**
     * Lets the user add a new passenger to the system.
     * This doesn't save to file right away - it just adds to memory.
     * User has to call the save option to actually persist this to disk.
     */
private static void addPassenger() {
    System.out.println("\n=== ADD NEW PASSENGER ===");
    
    try {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        
        System.out.print("Enter passport number: ");
        String passport = scanner.nextLine();
        
        System.out.print("Enter nationality: ");
        String nationality = scanner.nextLine();
        
        // Create passenger with default password
        Passenger passenger = new Passenger(name, email, "default123", 
                                          phone, passport, nationality);
        passengers.add(passenger);
        
        System.out.println("✓ Passenger added successfully! ID: " + passenger.getId());
        
    } catch (IllegalArgumentException e) {
        System.out.println("❌ Error: " + e.getMessage());
        System.out.println("Please try again.");
    }
}
    
    /**
     * Lets the user add a new employee - either a pilot or crew member.
     * This also doesn't save to file automatically.
     */
private static void addEmployee() {
    System.out.println("\n=== ADD NEW EMPLOYEE ===");
    
    try {
        System.out.print("Enter type (Pilot/Crew): ");
        String type = scanner.nextLine();
        
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        
        System.out.print("Enter employee ID (format EMP001): ");
        String empId = scanner.nextLine();
        
        System.out.print("Enter salary: ");
        double salary = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Enter department: ");
        String department = scanner.nextLine();
        
        if (type.equalsIgnoreCase("Pilot")) {
            System.out.print("Enter license number (format LIC-123456): ");
            String license = scanner.nextLine();
            
            System.out.print("Enter flight hours: ");
            int hours = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Enter certifications (comma separated): ");
            String certsInput = scanner.nextLine();
            String[] certifications = certsInput.split(",");
            
            Pilot pilot = new Pilot(name, email, "pilot123", phone, empId, 
                                   salary, department, license, hours, certifications);
            employees.add(pilot);
            System.out.println("✓ Pilot added successfully!");
            
        } else if (type.equalsIgnoreCase("Crew")) {
            System.out.print("Enter position: ");
            String position = scanner.nextLine();
            
            System.out.print("Enter languages spoken (comma separated): ");
            String langsInput = scanner.nextLine();
            String[] languages = langsInput.split(",");
            
            CrewMember crew = new CrewMember(name, email, "crew123", phone, 
                                            empId, salary, department, position, languages);
            employees.add(crew);
            System.out.println("✓ Crew Member added successfully!");
        } else {
            System.out.println("Invalid employee type.");
        }
        
    } catch (IllegalArgumentException e) {
        System.out.println("❌ Error: " + e.getMessage());
        System.out.println("Please try again.");
    }
}
    
    /**
     * Shows the user a sub menu where they can choose what data to view.
     * They can see passengers, employees, aircraft, or flights.
     */
    private static void viewAllData() {
        System.out.println("\n=== VIEW DATA ===");
        System.out.println("1. Passengers");
        System.out.println("2. Employees");
        System.out.println("3. Aircrafts");
        System.out.println("4. Flights");
        System.out.print("Enter choice: ");
        
        String choice = scanner.nextLine();
        System.out.println();
        
        // Show whatever they picked
        switch (choice) {
            case "1":
                // Show all passengers
                System.out.println("=== PASSENGERS ===");
                for (Passenger p : passengers) {
                    System.out.println(p);
                }
                break;
                
            case "2":
                // Show all employees
                System.out.println("=== EMPLOYEES ===");
                for (Employee e : employees) {
                    System.out.println(e);
                }
                break;
                
            case "3":
                // Show all aircraft
                System.out.println("=== AIRCRAFTS ===");
                for (Aircraft a : aircrafts) {
                    System.out.println(a);
                }
                break;
                
            case "4":
                // Show all flights
                System.out.println("=== FLIGHTS ===");
                for (Flight f : flights) {
                    System.out.println(f);
                }
                break;
                
            default:
                System.out.println("Invalid choice.");
        }
    }
    
    /**
     * Lets the user create a new booking.
     * They pick a flight, pick a passenger, enter a seat number and price.
     * This is where passengers actually get booked on flights.
     */
private static void createBooking() {
    System.out.println("\n=== CREATE BOOKING ===");
    
    try {
        // Show available flights
        System.out.println("Available Flights:");
        for (int i = 0; i < flights.size(); i++) {
            Flight f = flights.get(i);
            System.out.println(i + ". " + f.getFlightNumber() + ": " + 
                             f.getDepartureAirport() + " → " + f.getArrivalAirport() +
                             " (Available: " + f.getAvailableSeats() + ")");
        }
        
        System.out.print("\nSelect flight number: ");
        int flightIndex = Integer.parseInt(scanner.nextLine());
        
        if (flightIndex < 0 || flightIndex >= flights.size()) {
            System.out.println("❌ Invalid flight selection.");
            return;
        }
        
        // Show passengers
        System.out.println("\nAvailable Passengers:");
        for (int i = 0; i < passengers.size(); i++) {
            System.out.println(i + ". " + passengers.get(i).getName());
        }
        
        System.out.print("\nSelect passenger number: ");
        int passengerIndex = Integer.parseInt(scanner.nextLine());
        
        if (passengerIndex < 0 || passengerIndex >= passengers.size()) {
            System.out.println("❌ Invalid passenger selection.");
            return;
        }
        
        System.out.print("Enter seat number (format like 12A, 1C): ");
        String seat = scanner.nextLine();
        
        System.out.print("Enter price: $");
        double price = Double.parseDouble(scanner.nextLine());
        
        // Create booking
        Booking<Passenger> booking = new Booking<>(
            passengers.get(passengerIndex),
            flights.get(flightIndex),
            price, seat
        );
        bookings.add(booking);
        
        // Book seat
        flights.get(flightIndex).bookSeat();
        
        System.out.println("\n✓ Booking created!");
        System.out.println("Booking ID: " + booking.getBookingId());
        
    } catch (IllegalArgumentException e) {
        System.out.println("❌ Error: " + e.getMessage());
        System.out.println("Please try again.");
    }
}
    
    /**
     * Shows all the bookings that currently exist in the system.
     * This is basically "who's booked on what flight".
     */
    private static void viewBookings() {
        System.out.println("\n=== ALL BOOKINGS ===");
        for (Booking<Passenger> b : bookings) {
            System.out.println(b);
        }
    }
    
    /**
     * This is the big one - saves everything back to the files.
     * Gets called both when the user manually saves and when they exit the program.
     * Writes passengers, employees, aircraft, flights, and bookings to their respective files.
     */
    private static void saveAllData() {
        System.out.println("\nSaving data to files...");
        
        try {
            // SAVE PASSENGERS
            // Create a writer that'll write to the passengers file
            PrintWriter writer = new PrintWriter(new FileWriter(PASSENGERS_FILE));
            // Write a comment line explaining the format
            writer.println("# Format: Name,Email,Password,Phone,Passport,Nationality");
            // Write each passenger on its own line
            for (Passenger p : passengers) {
                writer.println(p.getName() + "," + p.getEmail() + ",default123," +
                              p.getPhone() + "," + p.getPassportNumber() + "," +
                              p.getNationality());
            }
            writer.close();
            System.out.println("✓ Saved " + passengers.size() + " passengers");
            
            // SAVE EMPLOYEES
            writer = new PrintWriter(new FileWriter(EMPLOYEES_FILE));
            writer.println("# Format: Type,Name,Email,Password,Phone,EmployeeID,Salary,Department,ExtraData");
            // Write employees - they can be pilots or crew, so we handle each differently
            for (Employee e : employees) {
                if (e instanceof Pilot) {
                    Pilot p = (Pilot) e;
                    // For pilots, extra data is license|hours|certifications
                    writer.println("Pilot," + p.getName() + "," + p.getEmail() + ",pilot123," +
                                  p.getPhone() + "," + p.getEmployeeId() + "," + p.getSalary() + "," +
                                  p.getDepartment() + "," + p.getLicenseNumber() + "|" + 
                                  p.getFlightHours() + "|ATP");
                } else if (e instanceof CrewMember) {
                    CrewMember c = (CrewMember) e;
                    // For crew members, extra data is position|languages
                    writer.println("Crew," + c.getName() + "," + c.getEmail() + ",crew123," +
                                  c.getPhone() + "," + c.getEmployeeId() + "," + c.getSalary() + "," +
                                  c.getDepartment() + "," + c.getPosition() + "|English");
                }
            }
            writer.close();
            System.out.println("✓ Saved " + employees.size() + " employees");
            
            // SAVE AIRCRAFT
            writer = new PrintWriter(new FileWriter(AIRCRAFTS_FILE));
            // Write each aircraft - no special format header needed here
            for (Aircraft a : aircrafts) {
                writer.println(a.getRegistrationNumber() + "," + a.getModel() + "," +
                              a.getManufacturer() + "," + a.getCapacity() + "," +
                              a.getRangeInKm());
            }
            writer.close();
            System.out.println("✓ Saved " + aircrafts.size() + " aircrafts");
            
            // SAVE FLIGHTS
            writer = new PrintWriter(new FileWriter(FLIGHTS_FILE));
            writer.println("# Format: FlightNumber,DepartureAirport,ArrivalAirport,DepartureTime,ArrivalTime,AircraftIndex,PilotIndex");
            // Write each flight
            for (Flight f : flights) {
                // Figure out which aircraft this flight uses by finding its index
                int aircraftIndex = aircrafts.indexOf(f.getAircraft());
                // Figure out which pilot this flight has
                int pilotIndex = 0;
                for (int i = 0; i < employees.size(); i++) {
                    if (employees.get(i) == f.getPilot()) {
                        pilotIndex = i;
                        break;
                    }
                }
                
                writer.println(f.getFlightNumber() + "," + f.getDepartureAirport() + "," +
                              f.getArrivalAirport() + "," + f.getDepartureTime() + "," +
                              f.getArrivalTime() + "," + aircraftIndex + "," + pilotIndex);
            }
            writer.close();
            System.out.println("✓ Saved " + flights.size() + " flights");
            
            // SAVE BOOKINGS
            writer = new PrintWriter(new FileWriter(BOOKINGS_FILE));
            writer.println("# Format: PassengerIndex,FlightIndex,Seat,Price");
            // Write each booking
            for (Booking<Passenger> b : bookings) {
                // Find which passenger this booking is for
                int passengerIndex = passengers.indexOf(b.getBookedBy());
                // Find which flight this booking is for
                int flightIndex = flights.indexOf(b.getFlight());
                
                writer.println(passengerIndex + "," + flightIndex + "," +
                              b.getSeatNumber() + "," + b.getPrice());
            }
            writer.close();
            System.out.println("✓ Saved " + bookings.size() + " bookings");
            
            System.out.println("\nAll data saved successfully!");
            
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}
