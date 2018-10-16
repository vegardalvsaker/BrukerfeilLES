/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Classes.Evaluation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Classes.WorklistNotEval;
import Classes.WorklistNotEval.WorklistEvaluated;
import java.sql.*;

/**
 *
 * @author Espen
 */
public class WorklistDb extends Database {
    
    
    
    //private static final String SLCT_ALL_DELIVERABELS = "select * from Delivery";
    private static final String SLCT_DELIVERY_WITH_EVALUTAION = "select delivery_id, student_id, module_id, delivery_content, worklist_id, delivery_timestamp, delivery_isEvaluated from Delivery where delivery_isEvaluated = TRUE";
    private static final String SLCT_DELIVERY_WITHOUT_EVALUTAION = "select delivery_id, student_id, module_id, delivery_content, worklist_id, delivery_timestamp, delivery_isEvaluated from Delivery where delivery_isEvaluated = FALSE";

    public void getWorklistNotEval(PrintWriter out)    {
        
        String list = SLCT_DELIVERY_WITHOUT_EVALUTAION;
        
        try(
                Connection connection = getConnection();
                PreparedStatement prepStatement = connection.prepareStatement(list);
                ResultSet rset = prepStatement.executeQuery();) {
                ArrayList<WorklistNotEval> notEvaluated = new ArrayList();
                
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
                    //if(boolean.class.equals("delivery_isEvaluated boolean default false,") == true) {
                    notEvaluated.add(objekt); 
                
                }
                
               for (WorklistNotEval objekt : notEvaluated)  {   
                out.println("<br>" + objekt.getDelId() + objekt.getStudentId() + objekt.getDesc() +"</br>"); 
        }
    }
        catch(SQLException liste) {
            out.println("SQL exception: in getWorklist" + liste);
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
                    //if(boolean.class.equals("delivery_isEvaluated boolean default false,") == true) {
                    evaluated.add(objekter); 
                
                }
                
               for (WorklistEvaluated objekter : evaluated)  {   
                out.println("<br>" + objekter.getDelId() + objekter.getStudentId() + objekter.getDesc() +"</br>"); 
        }
    }
        catch(SQLException liste) {
            out.println("SQL exception: in getWorklistEvaluated" + liste);
        }
    }
    
}
          
 