package Database;


import java.sql.*;
import Classes.Delivery;
import java.sql.*;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Filip
 */
public class DeliveryDb extends Database{

    private static final String SELECT_DELIVERY = "select * from Delivery where (student_id, module_id) = (?,?)";
    private static final String SELECT_DELIVERY_WITH_USER_NAME = "select u.user_name, d.delivery_id, d.delivery_timestamp, d.student_id, d.module_id,  d.worklist_id, d.delivery_content from Delivery d inner join Users u on d.student_id = u.user_id where d.delivery_id = ?";
    private static final String ADD_DELIVERY = "insert into Delivery values (default, ?, ?, ?, ?, default, default)";
    private static final String SLCT_ALL_DELIVERIES = "select * from Delivery where module_id = ?";
    private static final String CHECK_DELIVERY = "select delivery_id, delivery_content, module_id, D.student_id, user_id, user_name from Delivery D inner join Users U on D.student_id = U.user_id where module_id = ? and user_id = ?";
    private static final String EDIT_DELIVERY = "select delivery_id, delivery content from Delivery";
    private static final String UPDATE_DELIVERY ="update Delivery set delivery_content = ? where delivery_id = ?";
    private static final String SELECT_ALL_DELIVERIES = "select * from Delivery D inner join Users U on D.student_id = U.user_id inner join Module M on D.module_id = M.module_id where worklist_id = ? and delivery_isEvaluated = 0";
    private static final String UPDATE_ISEVALUATED = "update Delivery set delivery_isEvaluated = true where delivery_id = ?";
    
    public DeliveryDb() {
        init();
    }
    
    public void updateIsEvaluated(String deliveryId) {
        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(UPDATE_ISEVALUATED);
                ) {
            ps.setString(1, deliveryId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("method: updateIsEvaluated(), error: " + ex);
        }
            
    }
    
     public ArrayList<Delivery> getUnevaluatedDeliveriesWithinWorklist(String worklistId) {
        ArrayList<Delivery> deliveries = new ArrayList<>();
        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(SELECT_ALL_DELIVERIES)) {
            ps.setString(1, worklistId);
            try (ResultSet rset = ps.executeQuery();) {
                while (rset.next()) {
                    String delId = rset.getString("delivery_id");
                    String userName = rset.getString("user_name");
                    String moduleName = rset.getString("module_name");
                    String desc = rset.getString("delivery_content");
                    String workId = rset.getString("worklist_id");
                    String timestamp = rset.getString("delivery_timestamp");
                    boolean isEvaluated = rset.getBoolean("delivery_isEvaluated");
                                        
                    Delivery del = new Delivery(delId, userName, moduleName, desc, workId, timestamp, isEvaluated);
                    deliveries.add(del);

                }
                return deliveries;
            }
        } catch (SQLException ex) {
            System.out.print("Method: getUnevaluatedDeliveriesWithinWorklist(), error:" + ex);
            return null;
        }
        
    }
    
    public List<Delivery> getDeliveryWithUserIdAndModuleId(String moduleId, String studentId) {
        ArrayList<Delivery> delivery =new ArrayList<>();
        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(CHECK_DELIVERY);)
               {
             ps.setString(1, moduleId);
            ps.setString(2, studentId);   
            try (ResultSet deliverySet = ps.executeQuery();) {
            while(deliverySet.next()) {
                Delivery deliveries = new Delivery();
                deliveries.setDeliveryID(deliverySet.getString("delivery_id"));
                deliveries.setDeliveryContent(deliverySet.getString("delivery_content"));
                deliveries.setUserName(deliverySet.getString("user_name"));
                delivery.add(deliveries);
            }
            return delivery;
        }
               }
        catch (SQLException ex) {
            System.out.println("Query error:" + ex);
        }
        return null;
    }
    
    public void editDelivery(String deliveryContent, String deliveryId) {
   try( Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_DELIVERY);
                ) {
            
            ps.setString(1, deliveryContent);
            ps.setString(2,deliveryId);
            ps.executeUpdate();
            
        }
        catch(SQLException ex)   {
            System.out.println(ex);
        }

     }
    

    
    public Delivery getDeliveryWithUser(String deliveryId) {
        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(SELECT_DELIVERY_WITH_USER_NAME);
                ) {
            ps.setString(1, deliveryId);
            try (
                    ResultSet rs = ps.executeQuery();
                    ) {
                rs.next();
         
                Delivery delivery = new Delivery();
                
                delivery.setDeliveryID(rs.getString("delivery_id"));
                delivery.setStudentID(rs.getString("student_id"));
                delivery.setUserName(rs.getString("user_name"));
                delivery.setModuleID(rs.getString("module_id"));
                delivery.setDeliveryContent(rs.getString("delivery_content"));
                delivery.setWorklistID(rs.getString("worklist_id"));
                delivery.setDeliveryTimestamp(rs.getString("delivery_timestamp"));
                
                return delivery;
            }
        } catch (SQLException ex) {
            System.out.println("Method: getDeliveryWithUser(), error: " + ex);
            return null;
        }
    }
   
        public void getNrOfDeliveries(String module_id,PrintWriter out){
           try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(SLCT_ALL_DELIVERIES); 
                   ){
                ps.setString(1, module_id);
                try (ResultSet rs = ps.executeQuery();) {
                   int i = 0; 
                    while (rs.next()){
                       i++;
                    }
                    out.println("<div class=\"alert alert-info\" role=\"alert\">For modul " + module_id + ", er det " + i + " stk som har levert"+"</div>");
                }
        }
           catch (SQLException e) {
            System.out.println(e);
        }
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
                del.setDeliveryID(rset.getString("delivery_id"));
                del.setStudentID(rset.getString("user_id"));
                del.setModuleID(rset.getString("module_id"));
                del.setDeliveryContent(rset.getString("delivery_content"));
                del.setWorklistID(rset.getString("worklist_id"));
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
    public void addDelivery(String studentid, String moduleid, String content, String worklistid) {
        try( Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(ADD_DELIVERY);
                ) {
            
            ps.setString(1,studentid);
            ps.setString(2,moduleid);
            ps.setString(3,content);
            ps.setString(4,worklistid);
            ps.executeUpdate();
            
        }
        catch(SQLException ex)   {
            System.out.println(ex);
        }
    }

    /*
    public boolean editDelivery (String deliveryId, String content){
        
        String editDeliveryContent = "update Delivery set delivery_content = ?, where delivery_id = ?";
        
        try(
            Connection connection = getConnection();
            PreparedStatement prepStatement = connection.prepareStatement(editDeliveryContent);
        
        ) {
            prepStatement.setString(1, content);
            prepStatement.setString(2, deliveryId);
        
            prepStatement.executeUpdate();
            
            
            return true;
          }
        
        catch(SQLException ex)  {
            System.out.println("Error");
        }
        return false;
    }*/
    

    
    //VVVVVVVVV

    //Returnerer int total antall evaluerte deliveries 
    public int getAllEvaluatedDeliveries(PrintWriter out) {
        String oneDelivery = ("select * from Delivery where delivery_isEvaluated = 1;");
        int allEvaluatedDeliveriesCount = 0;
        
        try(    Connection connection = getConnection();
                PreparedStatement prepStatement = connection.prepareStatement(oneDelivery);
                ){
            
            try(ResultSet rset = prepStatement.executeQuery(); ){
                while(rset.next())   {
                    allEvaluatedDeliveriesCount++;
                }  
                return allEvaluatedDeliveriesCount;
            }
        }
        catch(SQLException liste) {
            out.println("SQL exception: in getAllEvaluatedDeliveries" + liste);
        }  
        return allEvaluatedDeliveriesCount;
    }     

    
    //Tar i mot student_id og returnerer total antall evaluerte deliveries for en student
    public int getEvaluatedDeliveries(PrintWriter out, String id) {
        String oneDelivery = ("select * from Delivery where student_id = ? AND delivery_isEvaluated = 1;");
        int evaluatedDeliveriesCount = 0;
        
        try(    Connection connection = getConnection();
                PreparedStatement prepStatement = connection.prepareStatement(oneDelivery);
                ){
            prepStatement.setString(1, id);
            
            try(ResultSet rset = prepStatement.executeQuery(); ){
                while(rset.next())   {
                    evaluatedDeliveriesCount++;
                }  
                return evaluatedDeliveriesCount;
            }
        }
        catch(SQLException liste) {
            out.println("SQL exception: in getEvaluatedDeliveries" + liste);
        }  
        return evaluatedDeliveriesCount;
    }   

    //Recives a module id, retrives the amount of deliveries for said module and returns that number
    public int getAmountOfDeliveriesPerModule(PrintWriter out, String id) {
        String oneDelivery = ("select * from Delivery where module_id = ? AND delivery_isEvaluated = 1;");
        int evaluatedDeliveriesCount = 0;
        
        try(    Connection connection = getConnection();
                PreparedStatement prepStatement = connection.prepareStatement(oneDelivery);
                ){
            prepStatement.setString(1, id);
            
            try(ResultSet rset = prepStatement.executeQuery(); ){
                while(rset.next())   {
                    evaluatedDeliveriesCount++;
                }  
                return evaluatedDeliveriesCount;
            }
        }
        catch(SQLException liste) {
            out.println("SQL exception: in getAmountOfDeliveriesPerModule" + liste);
        }  
        return evaluatedDeliveriesCount;
    }   
    //^^^^ ^^^^

}
 
     

