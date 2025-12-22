package AMS;

/**
 * Pilot class demonstrating MULTIPLE INHERITANCE and FINAL keyword
 * - Extends Person (inheritance from class)
 * - Implements Employable (inheritance from interface)
 * - Final class: cannot be extended by any other class
 */
public final class Pilot extends Person implements Employable {
    // Employee-related fields
    private String employeeId;
    private double salary;
    private String department;
    
    // Pilot-specific fields
    private String licenseNumber;
    private int flightHours;
    private String[] certifications;
    
    public Pilot(String name, String email, String password, String phone,
                 String employeeId, double salary, String department,
                 String licenseNumber, int flightHours, String[] certifications) {
        super(name, email, password, phone); // Inherit from Person
        setEmployeeId(employeeId);
        setSalary(salary);
        setDepartment(department);
        setLicenseNumber(licenseNumber);
        setFlightHours(flightHours);
        setCertifications(certifications);
    }
    
    // ========== Implementation of Employable Interface ==========
    
    @Override
    public String getEmployeeId() {
        return employeeId;
    }
    
    @Override
    public void setEmployeeId(String employeeId) {
        if (employeeId == null || employeeId.trim().isEmpty()) {
            throw new IllegalArgumentException("Employee ID cannot be empty");
        }
        if (!employeeId.matches("EMP\\d{3}")) {
            throw new IllegalArgumentException("Employee ID must be in format EMP001, EMP002, etc.");
        }
        this.employeeId = employeeId;
    }
    
    @Override
    public double getSalary() {
        return salary;
    }
    
    @Override
    public void setSalary(double salary) {
        if (salary < 1000) {
            throw new IllegalArgumentException("Salary must be at least 1000");
        }
        if (salary > 1000000) {
            throw new IllegalArgumentException("Salary cannot exceed 1,000,000");
        }
        this.salary = salary;
    }
    
    @Override
    public String getDepartment() {
        return department;
    }
    
    @Override
    public void setDepartment(String department) {
        if (department == null || department.trim().isEmpty()) {
            throw new IllegalArgumentException("Department cannot be empty");
        }
        String[] validDepartments = {"Flight Operations", "Cabin Crew", "Maintenance", "Ground Services", "Administration"};
        boolean valid = false;
        for (String dept : validDepartments) {
            if (dept.equalsIgnoreCase(department.trim())) {
                this.department = dept;
                valid = true;
                break;
            }
        }
        if (!valid) {
            throw new IllegalArgumentException("Invalid department. Valid departments: " + 
                                              String.join(", ", validDepartments));
        }
    }
    
    @Override
    public String getJobDescription() {
        return "Responsible for safely flying aircraft and ensuring passenger safety";
    }
    
    // ========== Pilot-specific methods ==========
    
    public String getLicenseNumber() {
        return licenseNumber;
    }
    
    public void setLicenseNumber(String licenseNumber) {
        if (licenseNumber == null || licenseNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("License number cannot be empty");
        }
        if (!licenseNumber.matches("LIC-\\d{6}")) {
            throw new IllegalArgumentException("License number must be in format LIC-123456");
        }
        this.licenseNumber = licenseNumber;
    }
    
    public int getFlightHours() {
        return flightHours;
    }
    
    public void setFlightHours(int flightHours) {
        if (flightHours < 0) {
            throw new IllegalArgumentException("Flight hours cannot be negative");
        }
        if (flightHours > 30000) {
            throw new IllegalArgumentException("Flight hours cannot exceed 30000");
        }
        this.flightHours = flightHours;
    }
    
    public String[] getCertifications() {
        return certifications;
    }
    
    public void setCertifications(String[] certifications) {
        if (certifications == null || certifications.length == 0) {
            throw new IllegalArgumentException("Pilot must have at least one certification");
        }
        for (String cert : certifications) {
            if (cert == null || cert.trim().isEmpty()) {
                throw new IllegalArgumentException("Certification cannot be empty");
            }
        }
        this.certifications = certifications;
    }
    
    public String displayCertifications() {
        if (certifications == null || certifications.length == 0) {
            return "No certifications";
        }
        StringBuilder certs = new StringBuilder();
        for (String cert : certifications) {
            certs.append(cert).append(", ");
        }
        return certs.substring(0, certs.length() - 2);
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Employee ID: " + employeeId + 
               ", Department: " + department + ", License: " + licenseNumber + 
               ", Flight Hours: " + flightHours + ", Salary: $" + salary + 
               ", Certifications: " + displayCertifications();
    }
}
