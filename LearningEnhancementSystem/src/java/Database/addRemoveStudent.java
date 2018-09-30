package Database;

import java.io.PrintWriter;
import java.sql.*; 


public class addRemoveStudent extends Database {
  
   
   
    public boolean addStudent(PrintWriter out, String navn)   {
        

        String add = "insert into Student values (default, '" + navn + "')";
         
        try(
             Connection connection = getConnection();
             Statement statement = connection.createStatement();)  {
             
        return statement.execute(add);
   
        }
        
        catch(SQLException e)   {
            
            out.println("SQL exception: in addStudent" + e);
            
        }
        return false;
      }
        
    
   
    public boolean removeStudent(PrintWriter out, String studnavn) {
        
        
        
        String remove = ("delete from Student where student_name = '" + studnavn + "'");
  
        try(
             Connection connection = getConnection();
             Statement statement = connection.createStatement();)  {
             
        return statement.execute(remove);
   
        }

        catch(SQLException ex)   {
            
                out.println("SQL exception: in removeStudent" + ex);
        }
        


      return false;
}

    public void getStudentList(PrintWriter out)    {
        
        String list = ("select * from Student");
        
        try(
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet rset = statement.executeQuery(list);) {
            

            out.println("<h1>Studentliste:</h1>");
            
            while(rset.next())  {
                String student_ID = rset.getString("student_ID");
                String student_name = rset.getString("student_name");
                
                out.println("Student ID: " + "'" + student_ID +  "', " + "<br>" + "Navn: " + "'" + student_name + "')" + "<br>");
            }
            
        }
        
        catch(SQLException liste) {
            
            out.println("SQL exception: in getStudentList" + liste);
        }
        

    }

}