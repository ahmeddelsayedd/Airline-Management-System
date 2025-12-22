
package AMS;

/**
 * Interface demonstrating multiple inheritance concept.
 * Classes can implement this interface to add employee functionality.
 */
public interface Employable {
    
    // Interface methods - must be implemented by classes
    String getEmployeeId();
    void setEmployeeId(String employeeId);
    
    double getSalary();
    void setSalary(double salary);
    
    String getDepartment();
    void setDepartment(String department);
    
    // Abstract method that must be implemented
    String getJobDescription();
    
    // Default method (Java 8+) - can be overridden but has default implementation
    default String getEmploymentStatus() {
        return "Active";
    }
}
