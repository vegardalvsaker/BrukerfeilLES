/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import java.sql.*;
import Classes.Comment;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marius
 */
public class CommentDb extends Database{
    private static final String ADD_COMMENT = "insert into Comments values (default, ?, ?, default,?)";
    private static final String ALL_COMMENTS = "select c.module_id, c.comment_id, c.comment_text, u.user_name from Comments c inner join Users u on c.user_id = u.user_id where c.module_id = ? order by c.comment_timestamp";
    private static final String DEL_COMMENT = "delete from Comments where comment_id = ?";
    
    public List<Comment> getComments(String moduleId){
        List<Comment> comments = new ArrayList<>();
    
        try (
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(ALL_COMMENTS);){
            ps.setString(1, moduleId);
            try (ResultSet commentSet = ps.executeQuery();){
              while(commentSet.next()){
                  Comment com = new Comment();
                  com.setCommentId(commentSet.getString("comment_id"));
                  com.setModuleId(commentSet.getString("module_id"));
                  com.setCommentText(commentSet.getString("comment_text"));
                  com.setUserName(commentSet.getString("user_name"));
                  comments.add(com);
              }
              return comments;
        }
        }
        catch (SQLException ex) {
            System.out.println("Query error:" + ex);
        }
        return null;
    }
    

 public void addComment(String module_id,String user_id, String comment_text){
        
            try (
                    Connection conn = getConnection();
                    PreparedStatement ps = conn.prepareStatement(ADD_COMMENT);
                    ) {
                         ps.setString(1, module_id);
                         ps.setString(2, user_id);
                         ps.setString(3,comment_text);
                         ps.executeUpdate();
                       } 
            catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    public void deleteComment(String Commentid){
            try (
               Connection conn = getConnection();
               PreparedStatement ps = conn.prepareStatement(DEL_COMMENT)) {
               
               ps.setString(1, Commentid);
               ps.executeUpdate();
 
       } catch (SQLException ex){
           System.out.println(ex);
       }
   }
}    