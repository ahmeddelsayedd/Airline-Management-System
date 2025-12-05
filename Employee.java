/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AMS;

public abstract class Employee extends Person {
    private String employeeId;
    private double salary;
    private String department;
    
    public Employee(String id, String name, String email, String phone,
                   String employeeId, double salary, String department) {
        super(id, name, email, phone);
        this.employeeId = employeeId;
        this.salary = salary;
        this.department = department;
    }
    
    // Abstract method for calculating bonus (Lect#5)
    public abstract double calculateBonus();
    
    // Getters and Setters
    public String getEmployeeId() { return employeeId; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    
    @Override
    public String toString() {
        return super.toString() + ", Employee ID: " + employeeId + 
               ", Salary: $" + salary;
    }
}
