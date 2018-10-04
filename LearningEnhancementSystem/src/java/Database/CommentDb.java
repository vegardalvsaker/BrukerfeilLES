/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import java.sql.*;
import Classes.Comment;
import java.io.PrintWriter;

/**
 *
 * @author Filip
 */
public class CommentDb extends Database{
    private static final String ADD_COMMENT = "insert into Comments values (default, ?, ?, ?, default)";
    private static final String PRINT_COMMENT = "select user_id, comment_id, comments_text from Comments where module_id = ?";
     public CommentDb(){
        init();
    }

 public void addCOMMENT(String module_id,String user_id, String comment_text){
        
            try (
                    Connection conn = getConnection();
                    PreparedStatement ps = conn.prepareStatement(ADD_COMMENT);
                    ) {

                ps.setString(1, module_id);
                ps.setString(2, user_id);
                ps.setString(3,comment_text);
                ps.executeUpdate();
               

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
 public Comment getComments(int moduleId) {
         try (
                Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(PRINT_COMMENT);       
                ) {
                
            ps.setInt(1, moduleId);
            try(ResultSet rs = ps.executeQuery()){
                rs.first();
                Comment com = new Comment();
                com.setModuleid(Module, moduleId);
            }
            ps.executeUpdate();
            out.println("<h5>Learning goals:</h5>");
            while(rs.next()){
                //rs.getString("comment_text");
            out.println("<h3>" + rs.getString("comment_text")+ "</h3>");
          //  System.out.println("user_id");
            }
          //  rs.Close();
            
           // return rs.getString("evaluation_id");
            
    } catch (SQLException ex) {
        System.out.println("Some error with the database" + ex);
    }
       
    }
   
    }

