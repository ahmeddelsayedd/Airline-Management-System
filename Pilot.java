/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AMS;

public final class Pilot extends Employee {
    private String licenseNumber;
    private int flightHours;
    private String[] certifiedAircraftTypes; // Arrays (Lect#3)
    
    // final constant (Lect#5)
    private static final double BONUS_RATE = 0.15;
    
    public Pilot(String id, String name, String email, String phone,
                String employeeId, double salary, String licenseNumber,
                String[] certifiedAircraftTypes) {
        super(id, name, email, phone, employeeId, salary, "Flight Operations");
        this.licenseNumber = licenseNumber;
        this.flightHours = 0;
        this.certifiedAircraftTypes = certifiedAircraftTypes;
    }
    
    @Override
    public String getRole() {
        return "Pilot";
    }
    
    @Override
    public double calculateBonus() {
        return getSalary() * BONUS_RATE;
    }
    
    public void addFlightHours(int hours) {
        this.flightHours += hours;
    }
    
    public String[] getCertifiedAircraftTypes() {
        return certifiedAircraftTypes;
    }
    
    @Override
    public String toString() {
        return super.toString() + ", License: " + licenseNumber + 
               ", Flight Hours: " + flightHours;
    }
}
