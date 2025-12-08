package AMS;

/**
 * Abstract Employee class demonstrating abstract keyword.
 * Cannot be instantiated directly.
 */
public abstract class Employee extends Person {
    private String employeeId;
    private double salary;
    private String department;
    
    /**
     * Constructor for Employee using super keyword
     */
    public Employee(String name, String email, String password, String phone,
                   String employeeId, double salary, String department) {
        super(name, email, password, phone);
    this.employeeId = employeeId;
    this.salary = salary;
    this.department = department;
    }
    
    // Abstract method - must be implemented by concrete subclasses
    public abstract String getJobDescription();
    
    // Getters and setters
    public String getEmployeeId() {
        return employeeId;
    }
    
    public void setEmployeeId(String employeeId) {
        // Employee ID validation: should start with EMP followed by 3 digits
        if (employeeId == null || employeeId.trim().isEmpty()) {
            throw new IllegalArgumentException("Employee ID cannot be empty");
        }
        if (!employeeId.matches("EMP\\d{3}")) {
            throw new IllegalArgumentException("Employee ID must be in format EMP001, EMP002, etc.");
        }
        this.employeeId = employeeId;
    }
    
    public double getSalary() {
        return salary;
    }
    
    public void setSalary(double salary) {
        // Salary validation: positive value, reasonable maximum
        if (salary < 1000) {
            throw new IllegalArgumentException("Salary must be at least 1000");
        }
        if (salary > 1000000) {
            throw new IllegalArgumentException("Salary cannot exceed 1,000,000");
        }
        this.salary = salary;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        // Department validation
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
    public String toString() {
        return super.toString() + ", Employee ID: " + employeeId + 
               ", Department: " + department + ", Salary: $" + salary;
    }
}
