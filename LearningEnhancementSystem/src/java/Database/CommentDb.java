/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import java.sql.*;
import Classes.Comment;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Filip
 */
public class CommentDb extends Database{
    private static final String ADD_COMMENT = "insert into Comments values (default, ?, ?, default,?)";
    private static final String PRINT_COMMENT = "select c.comment_text, u.user_name from Comments c inner join Users u on c.user_id = u.user_id where c.module_id = ? order by c.comment_timestamp;";
    
    public List<Comment> getComments(){
        List<Comment> comments = new ArrayList<>();
    
        try (
             Connection conn = getConnection();
             Statement stmt = getStatement(conn);
             ResultSet commentSet = stmt.executeQuery(PRINT_COMMENT);
            ){ 
              while(commentSet.next()){
                  Comment com = new Comment();
                  com.setCommentId(commentSet.getInt("comment_id"));
                  com.setCommentTime(commentSet.getTimestamp("comment_timestamp"));
                  com.setCommentText(commentSet.getString("comment_text"));
                  comments.add(com);
              }
              return comments;
        }
        catch (SQLException ex) {
            System.out.println("Query error:" + ex);
        }
        return null;
    }
    

 public void addComment(int module_id,String user_id, String comment_text){
        
            try (
                    Connection conn = getConnection();
                    PreparedStatement ps = conn.prepareStatement(ADD_COMMENT);
                    ) {

                ps.setInt(1, module_id);
                ps.setString(2, user_id);
                ps.setString(3,comment_text);
                ps.executeUpdate();
               

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
 public void printComments(int moduleId, PrintWriter out) {
         
     try (
          Connection conn = getConnection();
          PreparedStatement ps = conn.prepareStatement(PRINT_COMMENT);
           ){
           ps.setInt(1, moduleId);
           ResultSet rs = ps.executeQuery();
           out.println("<h2>Kommentarer</h2>");
           while (rs.next()){
           String commenttext = rs.getString("comment_text");
           String author = rs.getString("user_name");

           out.println("<h3>" + commenttext + "</h3>");
           out.println("<h5>" + author + "</h5>");
           out.println("<hr class=\"my-4\">");
           }
   
    } catch (SQLException ex) {
        System.out.println("Some error with the database" + ex);
    }
       
 }
   
}

