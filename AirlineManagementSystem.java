/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AMS;

import java.util.ArrayList;

public class AirlineManagementSystem<T> { // Generics
    private ArrayList<T> items;
    private String systemName;
    
    public AirlineManagementSystem(String systemName) {
        this.systemName = systemName;
        this.items = new ArrayList<>();
    }
    
    public void add(T item) {
        items.add(item);
    }
    
    public T get(int index) {
        return items.get(index);
    }
    
    public int size() {
        return items.size();
    }
    
    // Polymorphism demonstration
    public void displayAll() {
        System.out.println("\n=== " + systemName + " ===");
        for (T item : items) {
            System.out.println(item.toString()); // Dynamic binding
        }
    }
    
    public ArrayList<T> getAll() {
        return items;
    }
}
