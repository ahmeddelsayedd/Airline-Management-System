/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AMS;

public class CrewMember extends Employee {
    private String position; // e.g., "Flight Attendant", "Senior Attendant"
    private String[] languagesSpoken; // Arrays (Lect#3)
    
    private static final double BONUS_RATE = 0.10;
    
    public CrewMember(String id, String name, String email, String phone,
                     String employeeId, double salary, String position,
                     String[] languagesSpoken) {
        super(id, name, email, phone, employeeId, salary, "Cabin Crew");
        this.position = position;
        this.languagesSpoken = languagesSpoken;
    }
    
    @Override
    public String getRole() {
        return "Crew Member - " + position;
    }
    
    @Override
    public double calculateBonus() {
        return getSalary() * BONUS_RATE;
    }
    
    public String[] getLanguagesSpoken() {
        return languagesSpoken;
    }
}
