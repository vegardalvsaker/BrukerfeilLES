package Database;

import Classes.Delivery;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Vegard
 */
public class DeliveryDb extends Database{
    private static final String SELECT_DELIVERY = "select * from Delivery where (student_id, module_no) = (?,?)";
    public DeliveryDb() {
        init();
    }
    
    public ArrayList<Delivery> getDelivery(PrintWriter out, String studentID) {
        
        String deliveries = "select * from Delivery where student_id = ?";
      //  String moduleName = "select module_name from Module where module_id = ?";
       try (
            Connection connection = getConnection();
            PreparedStatement prepStatement = connection.prepareStatement(deliveries);
           // PreparedStatement pStatement = connection.prepareStatement(moduleName);
            ){
           
           prepStatement.setString(1, studentID);  
          
           
           try(ResultSet rset = prepStatement.executeQuery();)  { 
            
           Delivery del = new Delivery();
           ArrayList <Delivery> deliveryList = new ArrayList<>();
           
           while(rset.next())   {
               
               del.setDeliveryID(rset.getInt("delivery_id"));
               del.setStudentID(rset.getString("student_id"));
               del.setModuleID(rset.getString("module_id"));
               del.setDeliveryContent(rset.getString("delivery_content"));
               del.setWorklistID(rset.getInt("worklist_id"));
               del.setDeliveryTimestamp(rset.getString("delivery_timestamp"));
               del.setIsEvaluated(rset.getBoolean("delivery_isEvaluated"));
               
               deliveryList.add(del);
           }
           return deliveryList;
           }
           
          
       }
       
       
       
       
        catch(SQLException e)    {
           out.println("SQLException in getDelivery(): " + e);
    }
       
        return null;
    
    }
     
}
