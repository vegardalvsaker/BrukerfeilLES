package Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Classes.User;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Vegard & Gorm & Fosse
 */
public class UserDb extends Database {
    static final String SLCT_USER= "select * from Users where user_email = ?";
    
  // Fosse
    //Stating arraylists here to be able to split methods into get and print
    ArrayList<User> onlyStudents = new ArrayList<>();
    ArrayList<User> onlyTeachers = new ArrayList<>();
    ArrayList<User> profileList = new ArrayList<>();
    
    
    public boolean checkUser(String email) {
        PreparedStatement chckUsr = null;
        Connection conn = null;
        ResultSet rset = null;
        try {
            conn = getConnection();
            chckUsr = conn.prepareStatement(SLCT_USER);
            chckUsr.setString(1, email);
            rset = chckUsr.executeQuery(); 
            
          }
        catch (SQLException ex) {
            System.out.println("User does not exist:" + ex);
            return false;
        } finally {
            try {
               if (rset != null) {rset.close();}
               if (chckUsr != null) {chckUsr.close();}
               if (conn != null) {conn.close();} 
            }
           catch (SQLException ex){
               System.out.println(ex);
           }
        }
        return true;
    }
    
    public User getUser(String email) {
        try (
            Connection conn = getConnection();
            PreparedStatement chckUsr = conn.prepareStatement(SLCT_USER)
        ) {
            chckUsr.setString(1, email);
            ResultSet user = chckUsr.executeQuery();
            
            user.last();
            String id = user.getString("user_id");
            String name = user.getString("user_name");
            boolean isTeacher = user.getBoolean("isTeacher");
            
            User userObj = new User(id, name, email, isTeacher);
            return userObj;
          }
        catch (SQLException ex) {
        System.out.println(ex);
        }
        return null;
    }
    
    public boolean addStudent(PrintWriter out, String navn, String email, boolean isTeacher)   {
        
           
        
        String add = "insert into Users values (default, ?, ?, ?)";
        
        try(
             Connection connection = getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(add);)  {
             
            
           prepStatement.setString(1, navn);
           prepStatement.setString(2, email);
           prepStatement.setBoolean(3, isTeacher);
            
            
            prepStatement.executeUpdate();
            return true;
   
        }
        
        catch(SQLException e)   {
            
            out.println("SQL exception: in addStudent" + e);
            
        }
        return false;
      }
        

    public boolean removeStudent(PrintWriter out, String studnavn) {
        
        String remove = "delete from Users where user_name = ?";
  
        try(
             Connection connection = getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(remove);)  {
             
            prepStatement.setString(1, studnavn);
            
        
            prepStatement.executeUpdate();
            return true;
   
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
                ResultSet rset = prepStatement.executeQuery();) {
            
               out.println("<h1>Studentliste:</h1>");
               
               ArrayList<User> users = new ArrayList<>();
               
               while(rset.next())   {
                   
                   String userID = rset.getString("user_ID");
                   String userName = rset.getString("user_name");
                   String userEmail = rset.getString("user_email");
                   boolean isTeacher = rset.getBoolean("user_isTeacher");
                   
                   User user = new User(userID, userName, userEmail, isTeacher);
                   users.add(user);
               }  
   
               for (User user : users)   {
                   out.println(user + "<br>");
               }
               
                prepStatement.executeUpdate();
 
        }
        
        catch(SQLException liste) {
            
            out.println("SQL exception: in getStudentList" + liste);
           }
           
        } 

    //
    //     FOSSE SITT
    //    | | | | | | |
    //    V V V V V V V 
    //
    public void getProfile(PrintWriter out, String id) {
        String oneProfile = ("select * from Users where user_id = ?");
        try(
            Connection connection = getConnection();
            PreparedStatement prepStatement = connection.prepareStatement(oneProfile);
            ResultSet rset = prepStatement.executeQuery();) {

            while(rset.next())   {
                   
                String userID = rset.getString("user_ID");
                String userName = rset.getString("user_name");
                String userEmail = rset.getString("user_email");
                boolean isTeacher = rset.getBoolean("user_isTeacher");
                   
                User user = new User(userID, userName, userEmail, isTeacher);
                profileList.add(user);
            }  
        }
        catch(SQLException liste) {
            out.println("SQL exception: in getOnlyStudent" + liste);
           }  
    } 
    
    public void printProfile(PrintWriter out) {

        for (User user : profileList) {
                out.println("<h1>"+"Information about"+ user.getUserName() + "</h1>");
                out.println("User ID: " + user.getUserID());
                out.println(" Name: " + user.getUserName());
                out.println(" Email: " + user.getUserEmail());
                out.println("<br>");
            }
    }
   
    
    public void getOnlyStudent(PrintWriter out) {
        String studentList = ("select * from Users where user_isTeacher = 0");
        
        try(
            Connection connection = getConnection();
            PreparedStatement prepStatement = connection.prepareStatement(studentList);
            ResultSet rset = prepStatement.executeQuery();) {

            while(rset.next())   {
                   
                String userID = rset.getString("user_ID");
                String userName = rset.getString("user_name");
                String userEmail = rset.getString("user_email");
                boolean isTeacher = rset.getBoolean("user_isTeacher");
                   
                User user = new User(userID, userName, userEmail, isTeacher);
                onlyStudents.add(user);
            }  
        }
        catch(SQLException liste) {
            out.println("SQL exception: in getOnlyStudent" + liste);
           }  
    } 
    
    public void printOnlyStudent(PrintWriter out) {
        out.println("<h1>List of all students:</h1>");
        for (User user : onlyStudents) {
                String id = user.getUserID();
                out.println("User ID: " + user.getUserID());
                out.println(" Name: " + user.getUserName());
                out.println(" Email: " + user.getUserEmail());
                out.println("<a href=\"Profile?id="+ id +" \"a class=\"btn btn-primary\">View Profile</button></a>");
                out.println("<br>");
            }
    }
    
    public void getOnlyTeacher(PrintWriter out) {
        String teacherList = ("select * from Users where user_isTeacher = 1");
       
        try(
            Connection connection = getConnection();
            PreparedStatement prepStatement = connection.prepareStatement(teacherList);
            ResultSet rset = prepStatement.executeQuery();) {
            
            while(rset.next())   {
                String userID = rset.getString("user_ID");
                String userName = rset.getString("user_name");
                String userEmail = rset.getString("user_email");
                boolean isTeacher = rset.getBoolean("user_isTeacher");
                   
                User user = new User(userID, userName, userEmail, isTeacher);
                onlyTeachers.add(user);
            }  
        }
        catch(SQLException liste) {
            out.println("SQL exception: in getOnlyTeacher" + liste);
           }  
    } 
    
    public void printOnlyTeacher(PrintWriter out) {
        out.println("<h1>List of all teachers:</h1>");
        for (User user : onlyTeachers) {
                out.println("User ID: " + user.getUserID());
                out.println(" Name: " + user.getUserName());
                out.println(" Email: " + user.getUserEmail());
                out.println("<br>");
            }
    }

//
//    ^ ^ ^ ^ ^ ^ ^ 
//    | | | | | | | 
//     FOSSE SITT 
//
}