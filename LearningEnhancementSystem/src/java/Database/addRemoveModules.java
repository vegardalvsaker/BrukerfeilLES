package Database;

import java.io.PrintWriter;
import java.sql.*; 



public class addRemoveModules extends Database {
   
    
    public boolean addModule(PrintWriter out, String modulnr, String modulnavn, String beskrivelse)  {
        
        init();
        
        
        //String addmodule = "insert into Module values ('" + modulnr + "', '" + modulnavn + "', '" + beskrivelse + "')";
        String sql = "insert into Module"
                + " values (?, ?, ?, ?, ?)";
      
        try( Connection connection = getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(sql);
             
                ) {
             
            
            
            //Må legge til published variabel
            
             prepStatement.setString(1, "default");
             prepStatement.setString(2, modulnr);
             prepStatement.setString(3, modulnavn);
             prepStatement.setString(4, beskrivelse);
             //prepStatement.setBoolean(5, published);
            
            return prepStatement.execute(sql);
            
        }
        catch(SQLException e)   {
            out.println("Ugyldig SQL query");
            out.println("Feilmelding: " + e);
            
        }
       
        return false;
     }
      
}