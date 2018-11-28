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
    private static final String SELECT_USER_NAME = "select user_name from Users where user_id = ?";
     
    
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
    
    public String getUserName(String userId) {
        try (   Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(SELECT_USER_NAME)) {
            ps.setString(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getString("user_name");
            }
            
        } catch (SQLException ex) {
            System.out.println("method getUserName(), error : " + ex);
            return null;
        }
    }
    
    public ArrayList<String> getAllUserIds() {
        ArrayList<String> ids = new ArrayList();
        try (ResultSet rs = getConnection().prepareStatement(SELECT_ALL_USER_ID).executeQuery();) {
            while(rs.next()) {
                ids.add(rs.getString("user_id"));
            }
            return ids;
        } catch (SQLException ex) {
            System.out.println("method getAllUserIds(), error " + ex);
            return ids;
        }
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
    
    
      // Fosse
    //Stating arraylists here to be able to split methods into get and print
    ArrayList<User> onlyStudents = new ArrayList<>();
    ArrayList<User> onlyTeachers = new ArrayList<>();
    ArrayList<User> profileList = new ArrayList<>();
  //STOP 
    

    public User getOneProfile(String id) {
        String oneProfile = ("select * from Users where user_id = ?");
        
        try(    Connection connection = getConnection();
                PreparedStatement prepStatement = connection.prepareStatement(oneProfile);
                ){
            
            prepStatement.setString(1, id);
            
            try(ResultSet rset = prepStatement.executeQuery(); ){
                User usr = new User();
                
                while(rset.next())   {
                    usr.setUserid(rset.getString("user_id"));
                    usr.setUserName(rset.getString("user_name"));
                    usr.setUserEmail(rset.getString("user_email"));
                    usr.setUserIsTeacher(rset.getBoolean("user_isTeacher"));
                }
                return usr;
            }
        }
        catch(SQLException liste) {
            System.out.println("SQL exception: in getOneProfile" + liste);
           } 
        return null;
    }
 
    public ArrayList getArrayOfStudents(PrintWriter out) {
        String allStudents = ("select * from Users where user_isTeacher = 0");
        
        try(    Connection connection = getConnection();
                PreparedStatement prepStatement = connection.prepareStatement(allStudents);
                ){
   
            try(ResultSet rset = prepStatement.executeQuery(); ){
                ArrayList <User> studentList = new ArrayList<>();
                
                while(rset.next())   {
                    User usr = new User();
                    usr.setUserid(rset.getString("user_id"));
                    usr.setUserName(rset.getString("user_name"));
                    usr.setUserEmail(rset.getString("user_email"));
                    usr.setUserIsTeacher(rset.getBoolean("user_isTeacher"));
                    studentList.add(usr);
                }
                return studentList;
            }
        }
        catch(SQLException liste) {
            out.println("SQL exception: in getArrayOfStudents" + liste);
           } 
        return null;
    }
  
    public ArrayList getArrayOfTeachers(PrintWriter out) {
        String allTeachers = ("select * from Users where user_isTeacher = 1");
        
        try(    Connection connection = getConnection();
                PreparedStatement prepStatement = connection.prepareStatement(allTeachers);
                ){
   
            try(ResultSet rset = prepStatement.executeQuery(); ){
                ArrayList <User> TeacherList = new ArrayList<>();
                
                while(rset.next())   {
                    User usr = new User();
                    usr.setUserid(rset.getString("user_id"));
                    usr.setUserName(rset.getString("user_name"));
                    usr.setUserEmail(rset.getString("user_email"));
                    usr.setUserIsTeacher(rset.getBoolean("user_isTeacher"));
                    TeacherList.add(usr);
                }
                return TeacherList;
            }
        }
        catch(SQLException liste) {
            out.println("SQL exception: in getArrayOfTeachers" + liste);
           } 
        return null;
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