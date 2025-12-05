/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AMS;

public class Aircraft {
    private String registrationNumber;
    private String model;
    private String manufacturer;
    private int capacity;
    private int rangeInKm;
    
    // final field
    private final String aircraftType;
    
    public Aircraft(String registrationNumber, String model, 
                   String manufacturer, int capacity, int rangeInKm, 
                   String aircraftType) {
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.manufacturer = manufacturer;
        this.capacity = capacity;
        this.rangeInKm = rangeInKm;
        this.aircraftType = aircraftType;
    }
    

    public String getRegistrationNumber() { return registrationNumber; }
    public String getModel() { return model; }
    public int getCapacity() { return capacity; }
    public String getAircraftType() { return aircraftType; }
    
    @Override
    public String toString() {
        return manufacturer + " " + model + " (" + registrationNumber + 
               ") - Capacity: " + capacity + " passengers";
    }
}
