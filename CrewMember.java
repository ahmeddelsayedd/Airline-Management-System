package AMS;

/**
 * CrewMember class demonstrating array usage.
 */
public class CrewMember extends Employee {
    private String position;
    private String[] languagesSpoken; // Array of languages
    
    public CrewMember(String name, String email, String password, String phone,
                     String employeeId, double salary, String department,
                     String position, String[] languagesSpoken) {
        super(name, email, password, phone, employeeId, salary, department);
    this.position = position;
    this.languagesSpoken = languagesSpoken;
    }
    
    @Override
    public String getJobDescription() {
        return "Responsible for passenger service and safety during flights";
    }
    
    // Getters and setters
    public String getPosition() {
        return position;
    }
    
    public void setPosition(String position) {
        // Position validation
        if (position == null || position.trim().isEmpty()) {
            throw new IllegalArgumentException("Position cannot be empty");
        }
        String[] validPositions = {"Flight Attendant", "Senior Flight Attendant", 
                                   "Lead Flight Attendant", "Purser", "Chief Purser"};
        boolean valid = false;
        for (String pos : validPositions) {
            if (pos.equalsIgnoreCase(position.trim())) {
                this.position = pos;
                valid = true;
                break;
            }
        }
        if (!valid) {
            throw new IllegalArgumentException("Invalid position. Valid positions: " + 
                                              String.join(", ", validPositions));
        }
    }
    
    public String[] getLanguagesSpoken() {
        return languagesSpoken;
    }
    
    public void setLanguagesSpoken(String[] languagesSpoken) {
        // Languages validation: at least one language required
        if (languagesSpoken == null || languagesSpoken.length == 0) {
            throw new IllegalArgumentException("Crew member must speak at least one language");
        }
        for (String lang : languagesSpoken) {
            if (lang == null || lang.trim().isEmpty()) {
                throw new IllegalArgumentException("Language cannot be empty");
            }
            if (!lang.matches("[a-zA-Z\\s]+")) {
                throw new IllegalArgumentException("Language names should contain only letters");
            }
        }
        this.languagesSpoken = languagesSpoken;
    }
    
    // Method to display languages
    public String displayLanguages() {
        if (languagesSpoken == null || languagesSpoken.length == 0) {
            return "No languages specified";
        }
        
        StringBuilder languages = new StringBuilder();
        for (String lang : languagesSpoken) {
            languages.append(lang).append(", ");
        }
        return languages.substring(0, languages.length() - 2);
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Position: " + position + 
               ", Languages: " + displayLanguages();
    }
}
