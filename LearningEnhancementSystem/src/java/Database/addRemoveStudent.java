package Database;

import java.io.PrintWriter;
import java.sql.*; 


public class addRemoveStudent extends Database {
  
    
    public boolean addStudent(PrintWriter out, String navn, String email, boolean teacher)   {
        
            init();
        
      //  String add = "insert into Student values (default, '" + navn + "')";
        String add = "insert into Users" 
                + " values (?, ?, ?, ?)";
        
        try(
             Connection connection = getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(add);)  {
             
           prepStatement.setString(1, "default");
           prepStatement.setString(2, navn);
           prepStatement.setString(3, email);
           prepStatement.setBoolean(4, teacher);
            
            
        return prepStatement.execute(add);
   
        }
        
        catch(SQLException e)   {
            
            out.println("SQL exception: in addStudent" + e);
            
        }
        return false;
      }
        
    
   
    public boolean removeStudent(PrintWriter out, String studnavn) {
        
        
        
        String remove = "delete from Users"
                + " where user_name = ?";
  
        try(
             Connection connection = getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(remove);)  {
             
            prepStatement.setString(1, studnavn);
            
        
            return prepStatement.execute(remove);
   
        }

        catch(SQLException ex)   {
            
                out.println("SQL exception: in removeStudent" + ex);
        }
        


      return false;
}

    public void getStudentList(PrintWriter out)    {
        
        String list = ("select * from Users");
        
        try(
                Connection connection = getConnection();
                PreparedStatement prepStatement = connection.prepareStatement(list);
                ResultSet rset = prepStatement.executeQuery(list);) {
            

            prepStatement.setString(1, "user_ID");
            prepStatement.setString(2, "user_name");
            prepStatement.setString(3, "user_email");
            prepStatement.setString(4, "user_isTeacher");
            
            
            out.println("<h1>Studentliste:</h1>");
            
            while(rset.next())  {
                String user_ID = rset.getString("user_ID");
                String user_name = rset.getString("user_name");
                String user_email = rset.getString("user_email");
                String user_isTeacher = rset.getString("user_isTeacher");
                out.println("Student ID: ?");
                out.println("Navn: ?");
                out.println("Email: ?");
                out.println("Lærer: ?");
            }
            
        }
        
        catch(SQLException liste) {
            
            out.println("SQL exception: in getStudentList" + liste);
        }
        

    }

}