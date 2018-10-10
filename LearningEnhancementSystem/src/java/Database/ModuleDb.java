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
import java.sql.PreparedStatement;

/**
 *This class handles every database-query that has something to do with modules
 * @author Vegard
 */
public class ModuleDb extends Database {
    
    static final String SLCT_MODULE = "select * from Module";
    
    
    public List<Module> getModuler(){
        List<Module> moduler = new ArrayList<>();
        
        try (
            Connection conn = getConnection();
            Statement stmt = getStatement(conn);
            ResultSet modulSet = stmt.executeQuery(SLCT_MODULE);
          ){
            while(modulSet.next()) {
                Module modul = new Module();
                modul.setNumber(modulSet.getInt("module_id"));
                modul.setName(modulSet.getString("module_name"));
                modul.setDescription(modulSet.getString("module_description"));
                moduler.add(modul);
            }
            return moduler;
        }
        catch (SQLException ex) {
            System.out.println("Query error:" + ex);
        }
        return null;
    }
    /**
     * Redundant*
     * @param out 
     */
    public void skrivModuler(PrintWriter out) {
    
     System.out.println("The SQL query is: " + SLCT_MODULE); // Echo For debugging

     System.out.println();

     try( Connection connection = getConnection();
          PreparedStatement prepStatement = connection.prepareStatement(SLCT_MODULE);
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
            //}

                ++rowCount;
             }  
             out.println("Total number of records = " + rowCount);

             
     }     
     catch (SQLException ex) {
            out.println("Error in function: SkrivModuler(): " + ex);
     }
    }
    
    /*
    public void addModules(HttpServletRequest req) {
        Connection conn = getConnection();
        Statement stmt = getStatement(conn);
        
        String[] values = {req.getParameter("moduleno"),
        req.getParameter("shortDesc"),
        req.getParameter("longDesc")};
        
        String sql = "insert into Module values ("+ values[0] +", '"+values[1]+"', '"+ values[2] + "')";
        System.out.println(sql);
        try {
            stmt.execute(sql);
            System.out.println("Succsessfully added a module");
            conn.close();
        }
        catch (SQLException ex) {
            System.out.println("Could not insert into table, because: " + ex);
        }
    }*/
    
    private void deleteUI(PrintWriter out, String id){
      
        out.println("<form action=\"RemoveModule\" method=\"POST\">");
        out.println("<input name=\"remove\" value=\"TRUE\" style=\"visibility:hidden;\"></input>");
        out.println("<input name=\"id\" value=\""+ id +"\" style=\"visibility:hidden;\"></input>");
        out.println("<input type=\"submit\" value=\"Remove module\"></submit>");
        out.println("</form><br>");
    }
    
    public boolean deleteModule(HttpServletRequest req) {
        
        String id = req.getParameter("id");
   
        String sql = "delete from Module where module_id = " + id;
        
        try( Connection connection = getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(sql);
             
                ) {
            
                prepStatement.executeUpdate(sql);
                
        }
        
        catch (SQLException ex) {
            System.out.println("Could not execute statement: " + ex);
        }
        return false;
        }

    
    
    public boolean addModule(PrintWriter out, String modulnr, String modulnavn, String beskrivelse)  {
        
        init();
        
        String sql = "insert into Module values (default, ?, ?, ?, true)";
        
        try( Connection connection = getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(sql);
             
                ) {

            //Må legge til published variabel
            
             
             prepStatement.setString(1, modulnr);
             prepStatement.setString(2, modulnavn);
             prepStatement.setString(3, beskrivelse);
             //prepStatement.setBoolean(5, published);
            
             prepStatement.executeUpdate();
            
            return true;
        }
        catch(SQLException e)   {
            out.println("Ugyldig SQL query");
            out.println("Feilmelding: " + e);
            
        }
       
        return false;
     }
    
    public void printModules(PrintWriter out)   {
        
        String print = "select * from Module";
        
        try(
                Connection connection = getConnection();
                PreparedStatement prepStatement = connection.prepareStatement(print);
                ResultSet rset = prepStatement.executeQuery();) {
        
                out.println("<h1>Moduler</h1>");
                
                while(rset.next())  {
                    
                    String moduleID = rset.getString("module_id");
                    String moduleName = rset.getString("module_name");
                    String moduleDesc = rset.getString("module_desc");
                    String moduleContent = rset.getString("module_content");
                    boolean modulePublished = Boolean.parseBoolean(rset.getString("module_isPublished"));
                    
                    Module moduler = new Module();
                    
                    
                }
            
    }
        catch(SQLException exep)   {
            out.println("SQLException in printModules() " + exep);
        }
     }
    
    
        
    private void editUI(PrintWriter out)   {
        
        out.println("<form action=\"editModule\" method=\"POST\">");
        out.println("<input name=\"edit\" value=\"TRUE\" style=\"visibility:hidden;\"></input>");
        out.println("<input name=\"id\" value=\""+ id +"\" style=\"visibility:hidden;\"></input>");
        out.println("<input type=\"submit\" value=\"Edit Module\"></submit>");
        out.println("</form><br>");
        
        
    }
    

    
    public boolean editModule(PrintWriter out)  {
        
       // String edit = "alter "
        return false;
        
    }
    
}

