package AMS;

/**
 * Base Person class demonstrating encapsulation.
 */
public abstract class Person {
    // Static counter for auto-generating IDs (Static element)
    private static int idCounter = 1000;
    
    // Private fields for encapsulation
    private final int id; // Final field - cannot be changed after initialization
    private String name;
    private String email;
    private String password;
    private String phone;
    
    /**
     * Constructor using super keyword would be called in child classes
     */
    public Person(String name, String email, String password, String phone) {
        this.id = generateId();
    setName(name);
    setEmail(email);
    setPassword(password);
    setPhone(phone);
    }
    
    // Static method to generate unique IDs
    private static int generateId() {
        return idCounter++;
    }
    
    // Getters and setters for encapsulation
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        // Validation: Name should only contain letters and spaces
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (!name.matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("Name should contain only letters and spaces");
        }
        if (name.length() < 2 || name.length() > 50) {
            throw new IllegalArgumentException("Name should be between 2 and 50 characters");
        }
        this.name = name.trim();
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        // Basic email validation
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = email.trim();
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        // Password validation: at least 6 characters
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
        this.password = password;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        // Phone validation: digits only, 10-15 digits
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be empty");
        }
        String cleanedPhone = phone.replaceAll("[^0-9]", "");
        if (cleanedPhone.length() < 10 || cleanedPhone.length() > 15) {
            throw new IllegalArgumentException("Phone number must be between 10 and 15 digits");
        }
        this.phone = cleanedPhone;
    }
    
    // Override toString method for better object representation
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Email: " + email + ", Phone: " + phone;
    }
}
