/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import Classes.Module;
import Classes.LearningGoal;
import java.sql.PreparedStatement;


/**
 *This class handles every database-query that has something to do with modules
 * @author Vegard
 */
public class ModuleDb extends Database {
    
    
    
    private static final String SLCT_ALL_MODULES = "select * from Module";
    private static final String SELECT_ONE_MODULE = "select * from Module where module_id = ?";
    private static final String SLCT_MODULES_WITH_GOALS = "select * from Module m inner join LearningGoal l on m.module_id = l.module_id where m.module_id = ?";
    private static final String SLCT_LEARNGOAL = "select * from LearningGoal where module_id = ?";
    private static final String ADD_MODULE = "insert into Module values (default, ?, ?, ?, ?, ?)";
    private static final String EDIT_MODULE = "update Module set module_name = ?, module_desc = ?, module_content = ?, module_isPublished = ?, module_inInterview = ? where module_id = ?";
    
    /**
     * This method retrieves all of the modules in the database, create an object of each record and is then
     * added to a list of modules
     * @return a list of module-objects.
     */

    public List<Module> getModuler(){
        List<Module> moduler = new ArrayList<>();
        
        try (
            Connection conn = getConnection();
            Statement stmt = getStatement(conn);
            ResultSet modulSet = stmt.executeQuery(SLCT_ALL_MODULES);
          ){
            while(modulSet.next()) {
                Module modul = new Module();
                modul.setId(Integer.parseInt(modulSet.getString("module_id")));
                modul.setName(modulSet.getString("module_name"));
                modul.setContent(modulSet.getString("module_content"));
                modul.setDesc(modulSet.getString("module_desc"));
                modul.setPublished(modulSet.getBoolean("module_isPublished"));
                moduler.add(modul);
            }
            return moduler;
        }
        catch (SQLException ex) {
            System.out.println("Query error:" + ex);
        }
        return null;
    }
    
    public Module getModuleWithLearningGoals(String module_id) {
        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(SLCT_MODULES_WITH_GOALS);
            ){
                ps.setString(1, module_id);
                
                try (ResultSet rs = ps.executeQuery();) {
    
                    Module module = new Module();
                    rs.first();
                    module.setId(Integer.parseInt(module_id));
                    module.setName(rs.getString("module_name"));
                    module.setDesc(rs.getString("module_desc"));
                    module.setContent(rs.getString("module_content"));
                    module.setinInterview(rs.getBoolean("module_inInterview"));
                    
                    LearningGoal lg = new LearningGoal();
                    lg.setLearn_goal_id(rs.getString("learn_goal_id"));
                    lg.setText(rs.getString("learn_goal_text"));
                    lg.setPoints(rs.getInt("learn_goal_points"));
                    
                    module.addLearningGoal(lg);
                    
                    while (rs.next()) {
                        LearningGoal lg2 = new LearningGoal();
                        lg2.setLearn_goal_id(rs.getString("learn_goal_id"));
                        lg2.setText(rs.getString("learn_goal_text"));
                        lg2.setPoints(rs.getInt("learn_goal_points"));
                        
                        module.addLearningGoal(lg2);
                        
                    }
                    
                    return module;
                }
        }
        
        catch (SQLException e) {
            System.out.println(e);
        }
        return getModule(module_id);
    }
    
    
    /**
     * Redundant*
     * @param out 
     */
    public void skrivModuler(PrintWriter out) {
         System.out.println("The SQL query is: " + SLCT_ALL_MODULES); // Echo For debugging



     try( Connection connection = getConnection();
          PreparedStatement prepStatement = connection.prepareStatement(SLCT_ALL_MODULES);
          ResultSet rset = prepStatement.executeQuery();
                ) {         

            // Step 4: Process the ResultSet by scrolling the cursor forward via next().
            //  For each row, retrieve the contents of the cells with getXxx(columnName).
            out.println("The records selected are:" +"<br>");
            int rowCount = 0;
            while(rset.next()) {   // Move the cursor to the next row, return false if no more row
                String moduleID = rset.getString("module_id");
                String  moduleName = rset.getString("module_name");
                String moduleDescription = rset.getString("module_desc");
                String moduleContent = rset.getString("module_content");
                boolean isPublished = Boolean.parseBoolean(rset.getString("module_isPublished"));

                
                out.println("<a href=\"OneModule?id="+ moduleID+"\">" +moduleID +": " + moduleName + ", " + moduleDescription +"</a>");
                //if (userIsAdmin) {
                
                deleteUI(out, moduleID);
            

                ++rowCount;
                
                
             }  
             out.println("Total number of records = " + rowCount);

             
     }     
     catch (SQLException ex) {
            out.println("Error in function: SkrivModuler(): " + ex);
     }
    }
    
    public Module getModule(String moduleId) {
         try( Connection connection = getConnection();
          PreparedStatement ps = connection.prepareStatement(SELECT_ONE_MODULE);
          ) {  
             ps.setString(1, moduleId);
             try (ResultSet rs = ps.executeQuery();) {
                 if(rs.first()) {
                    Module module = new Module();
                    module.setId(Integer.parseInt(moduleId));
                    module.setContent(rs.getString("module_content"));
                    module.setDesc(rs.getString("module_desc"));
                    module.setName(rs.getString("module_name"));
                    module.setPublished(rs.getBoolean("module_isPublished"));

                    return module;
                 }
                 
             }
         } catch (SQLException ex) {
             System.out.println("Method: getModule(), Error: " + ex);
         }
         
        return null;
    }

    private void deleteUI(PrintWriter out, String id){
      
        out.println("<form action=\"RemoveModule\" method=\"POST\">");
        out.println("<input name=\"remove\" value=\"TRUE\" style=\"visibility:hidden;\"></input>");
        out.println("<input name=\"id\" value=\""+ id +"\" style=\"visibility:hidden;\"></input>");
        out.println("<input type=\"submit\" value=\"Remove module\"></submit>");
        out.println("</form><br>");
    }
    
    public boolean deleteModule(HttpServletRequest req) {
        
        String id = req.getParameter("id");
   
        String deleteModule = "delete from Module where module_id = " + id;
        
        try( Connection connection = getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(deleteModule);
             
                ) {
            
                prepStatement.executeUpdate();
                
        }
        
        catch (SQLException ex) {
            System.out.println("Could not execute statement: " + ex);
        }
        return false;
        }

    
    
    public boolean addModule(PrintWriter out, String modulnavn, String beskrivelse, String innhold, String leveringsform, boolean published)  {
        
        init();
        
        try( Connection connection = getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(ADD_MODULE);
             
                ) {

             
             prepStatement.setString(1, modulnavn);
             prepStatement.setString(2, beskrivelse);
             prepStatement.setString(3, innhold);
             prepStatement.setBoolean(4, published);
             
             if (leveringsform.equals("Muntlig")) {
             prepStatement.setBoolean(5, true);
             
                     }
             
             else {
                 prepStatement.setBoolean(4, false);
             }
             
            prepStatement.executeUpdate();
            
           
        }
        catch(SQLException e)   {
            out.println("Feilmelding i ModuleDb.addModule(): " + e);
            
        }
       
        return false;
     }
    

    public boolean editModule(PrintWriter out, HttpServletRequest request, String modulName, String modulDesc, String modulContent, boolean isPublished, boolean leveringsform)  {
        
   
      try(
             Connection connection = getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(EDIT_MODULE);
     
              ) {
               
              String moduleID = request.getParameter("id");
               
              prepStatement.setString(1, modulName);
              prepStatement.setString(2, modulDesc);
              prepStatement.setString(3, modulContent);
              prepStatement.setBoolean(4, isPublished);
              prepStatement.setBoolean(5, leveringsform);
              prepStatement.setString(6, moduleID);
              
        
              prepStatement.executeUpdate();
              
              
              return true;
      }

      catch(SQLException ex)    {
          out.println("Excption in editModule: " + ex);
      }
       return false;
       
    } 
    
// VVVVV Fosse VVVVV
    
    public int getModuleCount(PrintWriter out) {
        String modules = ("select * from Module where module_isPublished = 1");
        int moduleCount = 0;
        
        try(    Connection connection = getConnection();
                PreparedStatement prepStatement = connection.prepareStatement(modules);
                ResultSet rset = prepStatement.executeQuery();
                ){

            while(rset.next())   {
                moduleCount++;
            }  
            return moduleCount;
        }
        catch(SQLException liste) {
            out.println("SQL exception: in getModuleCount" + liste);
        }  
        return moduleCount;
    } 
    
// ^^^^ Fosse ^^^^
    
    
}

