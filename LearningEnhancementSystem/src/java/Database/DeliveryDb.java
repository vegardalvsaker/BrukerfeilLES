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
        
        String deliveries = "select d.delivery_id, d.delivery_content, d.delivery_timestamp, d.delivery_isEvaluated, w.worklist_id, s.user_id, m.module_id, m.module_name\n" +
        "from Delivery d\n" +
        "inner join Users s on d.student_id = s.user_id\n" +
        "inner join Module m on d.module_id = m.module_id\n" +
        "inner join Worklist w on d.worklist_id = w.worklist_id\n" +
        "where student_id = ?;";
      
       try (
            Connection connection = getConnection();
            PreparedStatement prepStatement = connection.prepareStatement(deliveries);
           
            ){
           
           prepStatement.setString(1, studentID);  
          
           
           try(ResultSet rset = prepStatement.executeQuery();)  { 
            
            ArrayList <Delivery> deliveryList = new ArrayList<>();

            while(rset.next())   {
                Delivery del = new Delivery();
                del.setModuleName(rset.getString("module_name"));
                del.setDeliveryID(rset.getInt("delivery_id"));
                del.setStudentID(rset.getString("user_id"));
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
