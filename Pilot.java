package AMS;

public final class Pilot extends Employee {
    private String licenseNumber;
    private int flightHours;
    private String[] certifications;
    

    public Pilot(String name, String email, String password, String phone,
                String employeeId, double salary, String department,
                String licenseNumber, int flightHours, String[] certifications) {
        super(name, email, password, phone, employeeId, salary, department);
this.licenseNumber = licenseNumber;
this.flightHours = flightHours;
this.certifications = certifications;
    }
    
    // Implementation of abstract method from Employee
    @Override
    public String getJobDescription() {
        return "Responsible for safely flying aircraft and ensuring passenger safety";
    }
    
    // Getters and setters
    public String getLicenseNumber() {
        return licenseNumber;
    }
    
    public void setLicenseNumber(String licenseNumber)
    {
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
    
    public void setFlightHours(int flightHours)
    {
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
    
    public void setCertifications(String[] certifications)
    {
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
        return super.toString() + ", License: " + licenseNumber + 
               ", Flight Hours: " + flightHours + ", Certifications: " + displayCertifications();
    }
}
