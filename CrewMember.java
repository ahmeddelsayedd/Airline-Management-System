package AMS;

/**
 * CrewMember class demonstrating MULTIPLE INHERITANCE
 * - Extends Person (inheritance from class)
 * - Implements Employable (inheritance from interface)
 */
public class CrewMember extends Person implements Employable {
    // Employee-related fields
    private String employeeId;
    private double salary;
    private String department;
    
    // Crew-specific fields
    private String position;
    private String[] languagesSpoken;
    
    public CrewMember(String name, String email, String password, String phone,
                      String employeeId, double salary, String department,
                      String position, String[] languagesSpoken) {
        super(name, email, password, phone); // Inherit from Person
        setEmployeeId(employeeId);
        setSalary(salary);
        setDepartment(department);
        setPosition(position);
        setLanguagesSpoken(languagesSpoken);
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
        return "Responsible for passenger service and safety during flights";
    }
    
    // ========== Crew-specific methods ==========
    
    public String getPosition() {
        return position;
    }
    
    public void setPosition(String position) {
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
        if (languagesSpoken == null || languagesSpoken.length == 0) {
            throw new IllegalArgumentException("Crew member must speak at least one language");
        }
        for (String lang : languagesSpoken) {
            if (lang == null || lang.trim().isEmpty()) {
                throw new IllegalArgumentException("Language cannot be empty");
            }
        }
        this.languagesSpoken = languagesSpoken;
    }
    
    public String displayLanguages() {
        if (languagesSpoken == null || languagesSpoken.length == 0) {
            return "No languages";
        }
        StringBuilder langs = new StringBuilder();
        for (String lang : languagesSpoken) {
            langs.append(lang).append(", ");
        }
        return langs.substring(0, langs.length() - 2);
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Employee ID: " + employeeId + 
               ", Department: " + department + ", Position: " + position + 
               ", Salary: $" + salary + ", Languages: " + displayLanguages();
    }
}
