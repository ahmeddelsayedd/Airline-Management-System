/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AMS;
public class Passenger extends Person {
    private String passportNumber;
    private String nationality;
    private int frequentFlyerMiles;
    
    // Static counter (Lect#3)
    private static int totalPassengers = 0;
    
    public Passenger(String id, String name, String email, String phone, 
                     String passportNumber, String nationality) {
        super(id, name, email, phone); // Inheritance - super keyword (Lect#4)
        this.passportNumber = passportNumber;
        this.nationality = nationality;
        this.frequentFlyerMiles = 0;
        totalPassengers++;
    }
    
    // Static method (Lect#3)
    public static int getTotalPassengers() {
        return totalPassengers;
    }
    
    @Override
    public String getRole() {
        return "Passenger";
    }
    
    public void addMiles(int miles) {
        this.frequentFlyerMiles += miles;
    }
    
    // Getters with encapsulation
    public String getPassportNumber() { return passportNumber; }
    public int getFrequentFlyerMiles() { return frequentFlyerMiles; }
    
    @Override
    public String toString() {
        return super.toString() + ", Passport: " + passportNumber + 
               ", Miles: " + frequentFlyerMiles;
    }
}
