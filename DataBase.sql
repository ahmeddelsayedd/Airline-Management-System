-- 1. Create the Database
CREATE DATABASE IF NOT EXISTS airline_management_system;
USE airline_management_system;

-- 2. Aircraft Table 
CREATE TABLE aircrafts (
    aircraft_id INT AUTO_INCREMENT PRIMARY KEY,
    registration_number VARCHAR(20) NOT NULL UNIQUE,
    model VARCHAR(50) NOT NULL,
    manufacturer VARCHAR(50) NOT NULL,
    capacity INT NOT NULL,
    range_km DOUBLE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 3. Passengers Table 
CREATE TABLE passengers (
    passenger_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    passport_number VARCHAR(20) NOT NULL UNIQUE,
    nationality VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 4. Employees Table 
CREATE TABLE employees (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_code VARCHAR(20) NOT NULL UNIQUE, 
    employee_type ENUM('Pilot', 'Crew') NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    salary DOUBLE NOT NULL,
    department VARCHAR(50) NOT NULL,
    
    -- Fields specific to Pilots (NULL if employee is Crew)
    license_number VARCHAR(50) UNIQUE,
    flight_hours INT,
    
    -- Fields specific to Crew (NULL if employee is Pilot)
    position VARCHAR(50)
);

-- 5. Pilot Certifications 
CREATE TABLE pilot_certifications (
    cert_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT NOT NULL,
    certification VARCHAR(50) NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id) ON DELETE CASCADE
);

-- 6. Crew Languages 
CREATE TABLE crew_languages (
    lang_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT NOT NULL,
    language VARCHAR(50) NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id) ON DELETE CASCADE
);

-- 7. Flights Table 
CREATE TABLE flights (
    flight_id INT AUTO_INCREMENT PRIMARY KEY,
    flight_number VARCHAR(10) NOT NULL UNIQUE,
    departure_airport CHAR(3) NOT NULL,
    arrival_airport CHAR(3) NOT NULL,   	
    departure_time TIME NOT NULL,      	
    arrival_time TIME NOT NULL,
    status VARCHAR(20) DEFAULT 'Scheduled',
    available_seats INT NOT NULL,
    
    -- Foreign Keys
    aircraft_id INT NOT NULL,
    pilot_id INT NOT NULL, 	
    
    FOREIGN KEY (aircraft_id) REFERENCES aircrafts(aircraft_id),
    FOREIGN KEY (pilot_id) REFERENCES employees(employee_id)
);

-- 8. Flight Crew Assignment 
CREATE TABLE flight_crew_assignments (
    assignment_id INT AUTO_INCREMENT PRIMARY KEY,
    flight_id INT NOT NULL,
    employee_id INT NOT NULL,
    FOREIGN KEY (flight_id) REFERENCES flights(flight_id) ON DELETE CASCADE,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);

-- 9. Bookings Table 
CREATE TABLE bookings (
    booking_db_id INT AUTO_INCREMENT PRIMARY KEY,
    booking_reference VARCHAR(20) NOT NULL UNIQUE, 
    booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    seat_number VARCHAR(5) NOT NULL,
    price DOUBLE NOT NULL,
    
    -- Relationships
    passenger_id INT NOT NULL,
    flight_id INT NOT NULL,
    
    FOREIGN KEY (passenger_id) REFERENCES passengers(passenger_id) ON DELETE CASCADE,
    FOREIGN KEY (flight_id) REFERENCES flights(flight_id) ON DELETE CASCADE
);


