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
    private static final String USER_EXIST = "select  count(*) from Users where user_email = ?";
    private static final String SLCT_USER = "select * from Users where user_email = ?";
    private static final String SELECT_USERID = "select user_id from Users where user_email = ?";
    private static final String SELECT_ALL_USER_ID = "select user_id from Users";
    

  // Fosse
    //Stating arraylists here to be able to split methods into get and print
    ArrayList<User> onlyStudents = new ArrayList<>();
    ArrayList<User> onlyTeachers = new ArrayList<>();
    ArrayList<User> profileList = new ArrayList<>();
  //STOP  
    
    public UserDb() {
        init();
    }
    /**
     * Checks if there is a user in the database with the same name
     * @param email
     * @return 
     */
    public boolean checkUserExist(String email) {
        try 
            (
                Connection conn = getConnection();
                PreparedStatement chckUsr = conn.prepareStatement(USER_EXIST);
            )
            {
                chckUsr.setString(1, email);
            try (
            ResultSet rset = chckUsr.executeQuery();
                    ) {
                rset.next();
                if (rset.getInt("count(*)") == 1) {
                    return true;
                }
            }
          }
        catch (SQLException ex) {
            System.out.println("Method: checkUserExist() in Database.UserDb. Error: " + ex);
            return false;
        } 
         
        return false;
    }
    
    public String getUserId(String email) {
        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(SELECT_USERID);
                ) {
                    ps.setString(1, email);
                    try (ResultSet rs = ps.executeQuery();) {
                        if(rs.next()) {
                        return rs.getString("user_id");
                    }
                    }
                    
                } 
                    catch (SQLException ex) {
                        System.out.println("Method: getUserId(), Error: "+ ex);
                        return null;
                        }
        return null;
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
            boolean isTeacher = user.getBoolean("user_isTeacher");
            
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

    public void getUserList(PrintWriter out)    {
        
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
                   out.println(user.getUserName() + "<br>");
               }
               
                //prepStatement.executeUpdate();
 
        }
        
        catch(SQLException liste) {
            
            out.println("SQL exception: in getStudentList " + liste);
           }
        
           
 
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


    //    V V V V V V V 
    
    public void getProfile(PrintWriter out, String id) {
        String oneProfile = ("select * from Users where user_id = ?");
        
        try(    Connection connection = getConnection();
                PreparedStatement prepStatement = connection.prepareStatement(oneProfile);
                ){
            
            prepStatement.setString(1, id);
            
            try(ResultSet rset = prepStatement.executeQuery(); ){
                while(rset.next())   {
                    String userID = rset.getString("user_ID");
                    String userName = rset.getString("user_name");
                    String userEmail = rset.getString("user_email");
                    boolean isTeacher = rset.getBoolean("user_isTeacher");
                   
                    User user = new User(userID, userName, userEmail, isTeacher);
                    profileList.add(user);
                }  
            }
        }
        catch(SQLException liste) {
            out.println("SQL exception: in getProfile" + liste);
           }  
    } 
    
    public void printProfile(PrintWriter out) {

        for (User user : profileList) {
            out.println("<h1>"+"Information about "+ user.getUserName() + "</h1>");
            out.println("User ID: " + user.getUserId() + "<br>");
            out.println(" Name: " + user.getUserName() + "<br>");
            out.println(" Email: " + user.getUserEmail() + "<br>");
        }
    }
   
    public void printProfileLimited(PrintWriter out) {

        for (User user : profileList) {
            out.println("<h1>"+"Information about "+ user.getUserName() + "</h1>");
            out.println(" Name: " + user.getUserName() + "<br>");
            out.println(" Email: " + user.getUserEmail() + "<br>");
        }
    }
    
    public void getOnlyStudent(PrintWriter out) {
        String studentList = ("select * from Users where user_isTeacher = 0");
        
        try(    Connection connection = getConnection();
                PreparedStatement prepStatement = connection.prepareStatement(studentList);
                ResultSet rset = prepStatement.executeQuery();
                ){

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
            String id = user.getUserId();
            out.println(" Name: " + user.getUserName() + "<br>");
            out.println(" Email: " + user.getUserEmail() + "<br>");
            out.println("<a href=\"Profile?id="+ id +" \"a class=\"btn btn-info\">View Profile</button></a>");
            out.println("<br>" + "<br>");
            }
    }
    
    public void getOnlyTeacher(PrintWriter out) {
        String teacherList = ("select * from Users where user_isTeacher = 1");
       
        try(    Connection connection = getConnection();
                PreparedStatement prepStatement = connection.prepareStatement(teacherList);
                ResultSet rset = prepStatement.executeQuery();
                ){
            
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
            String id = user.getUserId();
            out.println(" Name: " + user.getUserName() + "<br>");
            out.println(" Email: " + user.getUserEmail() + "<br>");
            out.println("<a href=\"Profile?id="+ id +" \"a class=\"btn btn-info\">View Profile</button></a>");
            out.println("<br>" + "<br>");
            }
    }
    
    public int getStudentCount(PrintWriter out) {
        String modules = ("select * from Users where user_isTeacher = 0");
        int studentCount = 0;
        
        try(    Connection connection = getConnection();
                PreparedStatement prepStatement = connection.prepareStatement(modules);
                ResultSet rset = prepStatement.executeQuery();
                ){

            while(rset.next())   {
                studentCount++;
            }  
            return studentCount;
        }
        catch(SQLException liste) {
            out.println("SQL exception: in getStudentCount" + liste);
        }  
        return studentCount;
    }
//    ^ ^ ^ ^ ^ ^ ^ 

}