package AMS;


public class Passenger extends Person {
    private String passportNumber;
    private String nationality;
    

public Passenger(String name, String email, String password, String phone, 
                String passportNumber, String nationality) {
    super(name, email, password, phone);
    setPassportNumber(passportNumber);  
    setNationality(nationality);        
}
    
    // Getters and setters
    public String getPassportNumber() {
        return passportNumber;
    }
    
    public void setPassportNumber(String passportNumber)
    {
        if (passportNumber == null || passportNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Passport number cannot be empty");
        }
        String cleanPassport = passportNumber.trim().toUpperCase();
        if (!cleanPassport.matches("[A-Z0-9]{8,9}")) {
            throw new IllegalArgumentException("Passport number must be 8-9 alphanumeric characters");
        }
        this.passportNumber = cleanPassport;
    }
    
    public String getNationality() {
        return nationality;
    }
    
    public void setNationality(String nationality)
    {
        if (nationality == null || nationality.trim().isEmpty()) {
            throw new IllegalArgumentException("Nationality cannot be empty");
        }
        if (!nationality.matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("Nationality should contain only letters");
        }
        if (nationality.length() < 2 || nationality.length() > 30) {
            throw new IllegalArgumentException("Nationality should be between 2 and 30 characters");
        }
        this.nationality = nationality.trim();
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Passport: " + passportNumber + ", Nationality: " + nationality;
    }
}
