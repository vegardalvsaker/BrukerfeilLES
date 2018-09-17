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

/**
 *This class handles every database-query that has something to do with modules
 * @author Vegard
 */
public class ModuleDb extends Database {
    
    static final String SLCT_MODULE = "select * from Module";
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
    Connection conn = getConnection();
    Statement stmt = getStatement(conn);

     

     System.out.println("The SQL query is: " + SLCT_MODULE); // Echo For debugging

     System.out.println();

     try {
            ResultSet rset = stmt.executeQuery(SLCT_MODULE);

            // Step 4: Process the ResultSet by scrolling the cursor forward via next().
            //  For each row, retrieve the contents of the cells with getXxx(columnName).
            out.println("The records selected are:" +"<br>");
            int rowCount = 0;
            while(rset.next()) {   // Move the cursor to the next row, return false if no more row
                String moduleID = rset.getString("module_id");
                String  moduleName = rset.getString("module_name");
                String moduleDescription = rset.getString("module_description");

                out.println("<a href=\"OneModule?id="+ moduleID+"\">" +moduleID +": " + moduleName + ", " + moduleDescription +"</a>");
                //if (userIsAdmin) {
                deleteUI(out, moduleID);
            //}

                ++rowCount;
             }  
             out.println("Total number of records = " + rowCount);

             conn.close();
     }     
     catch (SQLException ex) {
            out.println("Database error: " +ex);
     }
      //stmt.close(); 
    }
    
    
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
        Connection conn = getConnection();
        Statement stmt = getStatement(conn);
        
        String sql = "delete from Module where module_id = " + id;
        
        try {
            stmt.execute(sql);
            conn.close();
            return true;
        }
        catch (SQLException ex) {
            System.out.println("Could not execute statement: " + ex);
        }
        return false;
    }
}
