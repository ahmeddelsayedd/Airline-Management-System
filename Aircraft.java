package AMS;

/**
 * Aircraft class demonstrating encapsulation and toString overriding.
 */
public class Aircraft {
    private String registrationNumber;
    private String model;
    private String manufacturer;
    private int capacity;
    private double rangeInKm;
    
    public Aircraft(String registrationNumber, String model, String manufacturer, 
                   int capacity, double rangeInKm) {
this.registrationNumber = registrationNumber;
this.model = model;
this.manufacturer = manufacturer;
this.capacity = capacity;
this.rangeInKm = rangeInKm;
    }
    
    // Getters and setters
    public String getRegistrationNumber() {
        return registrationNumber;
    }
    
    public void setRegistrationNumber(String registrationNumber) {
        // Registration number validation: alphanumeric, 5-10 characters
        if (registrationNumber == null || registrationNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Registration number cannot be empty");
        }
        String cleanReg = registrationNumber.trim().toUpperCase();
        if (!cleanReg.matches("[A-Z0-9-]{5,10}")) {
            throw new IllegalArgumentException("Registration number must be 5-10 alphanumeric characters with optional hyphens");
        }
        this.registrationNumber = cleanReg;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        // Model validation
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Model cannot be empty");
        }
        if (model.length() < 2 || model.length() > 20) {
            throw new IllegalArgumentException("Model must be between 2 and 20 characters");
        }
        this.model = model.trim();
    }
    
    public String getManufacturer() {
        return manufacturer;
    }
    
    public void setManufacturer(String manufacturer) {
        // Manufacturer validation
        if (manufacturer == null || manufacturer.trim().isEmpty()) {
            throw new IllegalArgumentException("Manufacturer cannot be empty");
        }
        String[] validManufacturers = {"Boeing", "Airbus", "Embraer", "Bombardier", "ATR"};
        boolean valid = false;
        for (String manu : validManufacturers) {
            if (manu.equalsIgnoreCase(manufacturer.trim())) {
                this.manufacturer = manu;
                valid = true;
                break;
            }
        }
        if (!valid) {
            throw new IllegalArgumentException("Invalid manufacturer. Valid manufacturers: " + 
                                              String.join(", ", validManufacturers));
        }
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    public void setCapacity(int capacity) {
        // Capacity validation: positive value, reasonable range
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be at least 1");
        }
        if (capacity > 1000) {
            throw new IllegalArgumentException("Capacity cannot exceed 1000");
        }
        this.capacity = capacity;
    }
    
    public double getRangeInKm() {
        return rangeInKm;
    }
    
    public void setRangeInKm(double rangeInKm) {
        // Range validation: positive value
        if (rangeInKm <= 0) {
            throw new IllegalArgumentException("Range must be positive");
        }
        if (rangeInKm > 20000) {
            throw new IllegalArgumentException("Range cannot exceed 20000 km");
        }
        this.rangeInKm = rangeInKm;
    }
    
    @Override
    public String toString() {
        return "Aircraft: " + manufacturer + " " + model + 
               " (Reg: " + registrationNumber + "), Capacity: " + capacity + 
               ", Range: " + rangeInKm + "km";
    }
}
