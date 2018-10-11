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
import Classes.Worklist;
import java.sql.*;

/**
 *
 * @author Espen-PC
 */
public class WorklistDb extends Database {
    
    
    
    private static final String SLCT_ALL_DELIVERABELS = "select * from Delivery";
    private static final String SLCT_EVALUATION_WITH_DELIVERY = "select d.delivery_id, u.student_id, m.module_id, d.delivery_content, w.worklist_id, d.delivery_timestamp";
    
    public void getWorklist(PrintWriter out)    {
        
        String list = ("select * from Delivery");
        
        try(
                Connection connection = getConnection();
                PreparedStatement prepStatement = connection.prepareStatement(list);
                ResultSet rset = prepStatement.executeQuery();) {
                ArrayList<Worklist> notEvaluted = new ArrayList();
                
               out.println("<h1>Worklist:</h1>");
               
               while(rset.next())   {
                   
                   String DelId = rset.getString("delivery_id");
                   String StudentId = rset.getString("student_id");
                   String ModuleId = rset.getString("module_id");
                   String Desc = rset.getString("delivery_content");
                   String workId = rset.getString("worklist_id");
                   String Timestamp = rset.getString("delivery_timestamp");
                   boolean isEvaluated = rset.getBoolean("delivery_isEvaluated");
                   
                   Worklist tull = new Worklist(DelId, StudentId, ModuleId, Desc, workId, Timestamp, isEvaluated); 
                   notEvaluted.add(tull); 
               } 
               
                for (Worklist objekt : notEvaluted)   {
                   out.println("<br>" + objekt.getDelId() + objekt.getStudentId() + objekt.getDesc()+ "</br>"); 
               }
        
        }
        
        catch(SQLException liste) {
            
            out.println("SQL exception: in getWorklist" + liste);
           }
           
    }
    
}

