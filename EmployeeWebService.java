package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "EmployeeWebService")
public class EmployeeWebService {
    
    @WebMethod(operationName = "getEmployeeInfo")
    public String getEmployeeInfo(@WebParam(name = "empId") int empId) {
        StringBuilder result = new StringBuilder();
        try {
            // Establishing connection to the database
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/employee");
            
            // Preparing SQL query
            String query = "SELECT name, position, department, salary FROM employee WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, empId);
            
            // Executing query
            ResultSet rs = stmt.executeQuery();
            
            // Processing result set
            while (rs.next()) {
                String name = rs.getString("name");
                String position = rs.getString("position");
                String department = rs.getString("department");
                double salary = rs.getDouble("salary");
                
                // Appending employee information to result string
                result.append("Name: ").append(name).append("\n");
                result.append("Position: ").append(position).append("\n");
                result.append("Department: ").append(department).append("\n");
                result.append("Salary: ").append(salary).append("\n");
            }
            
            // Closing resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }
        return result.toString(); // Returning employee information
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "operation")
    public String operation(@WebParam(name = "id") int id, @WebParam(name = "name") String name, @WebParam(name = "parameter1") String parameter1) {
        //TODO write your implementation code here:
        return null;
    }
}
