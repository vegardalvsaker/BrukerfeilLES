package Database;

import java.io.PrintWriter;
import java.util.ArrayList;
import Classes.WorklistNotEval;
import Classes.WorklistNotEval.WorklistEvaluated;
import java.sql.*;

/**
 *
 * @author Espen
 */
public class WorklistDb extends Database {
    
    //private static final String uses the SQL database to collect the data we need and converts it to Stings so that we can use it later;
    private static final String SLCT_DELIVERY_WITH_EVALUTAION =     "select delivery_id, student_id, module_id, delivery_content, worklist_id, delivery_timestamp, delivery_isEvaluated from Delivery where delivery_isEvaluated = TRUE;";
    private static final String SLCT_DELIVERY_WITHOUT_EVALUTAION =  "select delivery_id, student_id, module_id, delivery_content, worklist_id, delivery_timestamp, delivery_isEvaluated from Delivery where delivery_isEvaluated = FALSE";
    private static final String GIVE_WORKLIST_ID =                  "insert into Worklist VALUES (1,1), (2,2), (3,1), (3,2)";      
    private static final String GIVE_DELIVERY_WORKLISTID =          "insert into Delivery VALUES";
    private static final String SLCT_DELIVERY_WORLISTID_1 =         "select delivery_id, student_id, module_id, delivery_content, worklist_id, delivery_timestamp, delivery_isEvaluated from Delivery where worklist_id =1 and delivery_isEvaluated =0;";
    private static final String SLCT_DELIVERY_WORLISTID_2 =         "select delivery_id, student_id, module_id, delivery_content, worklist_id, delivery_timestamp, delivery_isEvaluated from Delivery where worklist_id =2 and delivery_isEvaluated =0;";
    private static final String GET_WORKLIST_ID =                   "select worklist_id from Worklist where teacher_id = ?";
//Worklist for teacher 1
    
    public String getWorklistId(String userId) {
        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(GET_WORKLIST_ID)) {
            ps.setString(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                return rs.getString("worklist_id");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Method: getWorklistId, error:" + ex);
            return null;
        }
        return null;
    }
    
    public void getWorklistNotEvalTeacher1(PrintWriter out)    {
        
        String list = SLCT_DELIVERY_WORLISTID_1;
        
        try(
                Connection connection = getConnection();
                PreparedStatement prepStatement = connection.prepareStatement(list);
                ResultSet rset = prepStatement.executeQuery();) {
                ArrayList<WorklistNotEval> notEvaluatedForTeacher1 = new ArrayList();
                
               out.println("<h1>Worklist for tasks that are not evaluated:</h1>");
               
               while(rset.next())   {
                   
                   String DelId = rset.getString("delivery_id");
                   String StudentId = rset.getString("student_id");
                   String ModuleId = rset.getString("module_id");
                   String Desc = rset.getString("delivery_content");
                   String workId = rset.getString("worklist_id");
                   String Timestamp = rset.getString("delivery_timestamp");
                   boolean isEvaluated = rset.getBoolean("delivery_isEvaluated");
                   
                WorklistNotEval objekt = new WorklistNotEval(DelId, StudentId, ModuleId, Desc, workId, Timestamp, isEvaluated); 
                notEvaluatedForTeacher1.add(objekt); 
                
                }
                
               for (WorklistNotEval objekt : notEvaluatedForTeacher1)  {
                    out.println("<br>" + objekt.getDelId() + "&#160;|&#160;" + objekt.getUserID() + "&#160;|&#160;"  + objekt.getDesc() +"</br>"); 
        }
    }
        catch(SQLException liste) {
            out.println("SQL exception: in getWorklist" + liste);
        }
    }
    
//Worklist for Teacher 2    
public void getWorklistNotEvalTeacher2(PrintWriter out)    {
        
        String list = SLCT_DELIVERY_WORLISTID_2; {
       
        try(
                Connection connection = getConnection();
                PreparedStatement prepStatement = connection.prepareStatement(list);
                ResultSet rset = prepStatement.executeQuery();) {
                ArrayList<WorklistNotEval> notEvaluatedForTeacher2 = new ArrayList();
                
               out.println("<h1>Worklist for tasks that are not evaluated:</h1>");
               
               while(rset.next())   {
                   
                   String DelId = rset.getString("delivery_id");
                   String StudentId = rset.getString("student_id");
                   String ModuleId = rset.getString("module_id");
                   String Desc = rset.getString("delivery_content");
                   String workId = rset.getString("worklist_id");
                   String Timestamp = rset.getString("delivery_timestamp");
                   boolean isEvaluated = rset.getBoolean("delivery_isEvaluated");
               
                   WorklistNotEval objekt = new WorklistNotEval(DelId, StudentId, ModuleId, Desc, workId, Timestamp, isEvaluated); 
                   notEvaluatedForTeacher2.add(objekt); 
                }
                
               for (WorklistNotEval objekt : notEvaluatedForTeacher2)  {
                    out.println("<br>" + objekt.getDelId() + "&#160;|&#160;" +  objekt.getUserID() + "&#160;|&#160;" + objekt.getDesc() +"</br>"); 
                   
        }
    }
        catch(SQLException liste) {
            out.println("SQL exception: in getWorklist" + liste);
        }
    }
}
    
public void getWorklistEvaluated(PrintWriter out)    {  
        
        String list = SLCT_DELIVERY_WITH_EVALUTAION;
        
        try(
                Connection connection = getConnection();
                PreparedStatement prepStatement = connection.prepareStatement(list);
                ResultSet rset = prepStatement.executeQuery();) {
                ArrayList<WorklistEvaluated> evaluated = new ArrayList();
                
               out.println("<h1>Worklist over tasks already evaluated:</h1>");
               
               while(rset.next())   {
                   
                   String DelId = rset.getString("delivery_id");
                   String StudentId = rset.getString("student_id");
                   String ModuleId = rset.getString("module_id");
                   String Desc = rset.getString("delivery_content");
                   String workId = rset.getString("worklist_id");
                   String Timestamp = rset.getString("delivery_timestamp");
                   boolean isEvaluated = rset.getBoolean("delivery_isEvaluated");
                   
                   
                   WorklistEvaluated objekter = new WorklistEvaluated(DelId, StudentId, ModuleId, Desc, Timestamp, isEvaluated); 
                   evaluated.add(objekter); 
                
                }
                
               for (WorklistEvaluated objekter : evaluated)  {   
                out.println("<br>" + objekter.getDelId() + "&#160;|&#160;" + objekter.getStudentId() + "&#160;|&#160;" + objekter.getDesc() +"</br>"); 
        }
    }
        catch(SQLException liste) {
            out.println("SQL exception: in getWorklistEvaluated" + liste);
        }
    }
    
}
