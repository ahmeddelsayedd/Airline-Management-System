-- -----------------------------------------------------
-- DATABASE
-- -----------------------------------------------------
CREATE DATABASE IF NOT EXISTS airline_mgmt;
USE airline_mgmt;

-- -----------------------------------------------------
-- TABLE: Aircraft
-- -----------------------------------------------------
CREATE TABLE Aircraft (
    aircraft_id INT AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(50),
    capacity INT,
    manufacture_year INT,
    range_km INT
);

-- -----------------------------------------------------
-- TABLE: Flight
-- -----------------------------------------------------
CREATE TABLE Flight (
    flight_id INT AUTO_INCREMENT PRIMARY KEY,
    origin VARCHAR(50),
    destination VARCHAR(50),
    departure_time DATETIME,
    arrival_time DATETIME,
    aircraft_id INT,
    FOREIGN KEY (aircraft_id) REFERENCES Aircraft(aircraft_id)
);

-- -----------------------------------------------------
-- TABLE: Passenger
-- -----------------------------------------------------
CREATE TABLE Passenger (
    passenger_id INT AUTO_INCREMENT PRIMARY KEY,
    fname VARCHAR(50),
    lname VARCHAR(50),
    email VARCHAR(100),
    phone VARCHAR(30),
    passport_no VARCHAR(30),
    nationality VARCHAR(50),
    password VARCHAR(255)
);

-- -----------------------------------------------------
-- TABLE: Pilot
-- -----------------------------------------------------
CREATE TABLE Pilot (
    pilot_id INT AUTO_INCREMENT PRIMARY KEY,
    fname VARCHAR(50),
    lname VARCHAR(50),
    email VARCHAR(100),
    phone VARCHAR(30),
    salary DECIMAL(10,2),
    license_no VARCHAR(50),
    flight_hours INT,
    plane_type VARCHAR(50),
    password VARCHAR(255)
);

-- -----------------------------------------------------
-- TABLE: CrewMember
-- -----------------------------------------------------
CREATE TABLE CrewMember (
    crew_id INT AUTO_INCREMENT PRIMARY KEY,
    fname VARCHAR(50),
    lname VARCHAR(50),
    email VARCHAR(100),
    phone VARCHAR(30),
    salary DECIMAL(10,2),
    position VARCHAR(50),
    language_spoken VARCHAR(50),
    password VARCHAR(255)
);

-- -----------------------------------------------------
-- TABLE: Booking
-- -----------------------------------------------------
CREATE TABLE Booking (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    passenger_id INT,
    price DECIMAL(10,2),
    seat_no VARCHAR(10),
    booking_date DATE,
    flight_id INT,
    FOREIGN KEY (passenger_id) REFERENCES Passenger(passenger_id),
    FOREIGN KEY (flight_id) REFERENCES Flight(flight_id)
);

-- -----------------------------------------------------
-- TABLE: Flight_Pilot
-- -----------------------------------------------------
CREATE TABLE Flight_Pilot (
    id INT AUTO_INCREMENT PRIMARY KEY,
    flight_id INT,
    pilot_id INT,
    FOREIGN KEY (flight_id) REFERENCES Flight(flight_id),
    FOREIGN KEY (pilot_id) REFERENCES Pilot(pilot_id)
);

-- -----------------------------------------------------
-- TABLE: Flight_Crew
-- -----------------------------------------------------
CREATE TABLE Flight_Crew (
    id INT AUTO_INCREMENT PRIMARY KEY,
    flight_id INT,
    crew_id INT,
    FOREIGN KEY (flight_id) REFERENCES Flight(flight_id),
    FOREIGN KEY (crew_id) REFERENCES CrewMember(crew_id)
);


-- =====================================================
-- INSERT DATA
-- =====================================================

-- -----------------------------------------------------
-- Aircraft
-- -----------------------------------------------------
INSERT INTO Aircraft (model, capacity, manufacture_year, range_km)
VALUES
('Airbus A320', 180, 2018, 6100),
('Boeing 737-800', 160, 2016, 5600),
('Airbus A350', 300, 2021, 15000);

-- -----------------------------------------------------
-- Flight
-- -----------------------------------------------------
INSERT INTO Flight (origin, destination, departure_time, arrival_time, aircraft_id)
VALUES
('Cairo', 'Dubai', '2025-01-10 09:00', '2025-01-10 13:00', 1),
('Cairo', 'London', '2025-01-11 06:30', '2025-01-11 11:50', 2),
('cairo', 'Paris', '2025-01-12 17:00', '2025-01-12 22:30', 3);

-- -----------------------------------------------------
-- Passenger
-- -----------------------------------------------------
INSERT INTO Passenger (fname, lname, email, phone, passport_no, nationality, password)
VALUES
('Omar', 'Hassan', 'omar@mail.com', '0100000001', 'A1234567', 'Egyptian', 'passOmar123'),
('Sara', 'Adel', 'sara@mail.com', '0100000002', 'B9876543', 'Egyptian', 'saraPass22'),
('John', 'Smith', 'john@mail.com', '0100000003', 'C1928374', 'British', 'johnKey44');

-- -----------------------------------------------------
-- Pilot
-- -----------------------------------------------------
INSERT INTO Pilot (fname, lname, email, phone, salary, license_no, flight_hours, plane_type, password)
VALUES
('Ali', 'Mahmoud', 'ali.pilot@mail.com', '0101000001', 45000, 'LIC123', 5000, 'A320', 'pilotAli@12'),
('David', 'Wilson', 'david.pilot@mail.com', '0101000002', 52000, 'LIC678', 7000, 'B737', 'davidPass99'),
('Khaled', 'Saleh', 'khaled.pilot@mail.com', '0101000003', 60000, 'LIC555', 9000, 'A350', 'khaled350!');

-- -----------------------------------------------------
-- CrewMember
-- -----------------------------------------------------
INSERT INTO CrewMember (fname, lname, email, phone, salary, position, language_spoken, password)
VALUES
('Mona', 'Ibrahim', 'mona.crew@mail.com', '0102000001', 15000, 'Flight Attendant', 'Arabic', 'MonaCrew11'),
('James', 'Taylor', 'james.crew@mail.com', '0102000002', 16000, 'Flight Attendant', 'English', 'JamesCrew22'),
('Rania', 'Khaled', 'rania.crew@mail.com', '0102000003', 17000, 'Purser', 'Arabic, English', 'RaniaPurser33');

-- -----------------------------------------------------
-- Booking
-- -----------------------------------------------------
INSERT INTO Booking (passenger_id, price, seat_no, booking_date, flight_id)
VALUES
(1, 2500, '12A', '2025-01-01', 1),
(2, 2700, '14B', '2025-01-02', 1),
(3, 5500, '4C',  '2025-01-05', 2);

-- -----------------------------------------------------
-- Flight_Pilot
-- -----------------------------------------------------
INSERT INTO Flight_Pilot (flight_id, pilot_id)
VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 3);

-- -----------------------------------------------------
-- Flight_Crew
-- -----------------------------------------------------
INSERT INTO Flight_Crew (flight_id, crew_id)
VALUES
(1, 1),
(1, 3),
(2, 2),
(3, 1),
(3, 2),
(3, 3);
