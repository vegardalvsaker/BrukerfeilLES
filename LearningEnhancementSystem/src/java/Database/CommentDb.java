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
 * @author Marius
 */
public class CommentDb extends Database{
    private static final String ADD_COMMENT = "insert into Comments values (default, ?, ?, default,?)";
    private static final String PRINT_COMMENT = "select c.comment_id, c.comment_text, u.user_name from Comments c inner join Users u on c.user_id = u.user_id where c.module_id = ? order by c.comment_timestamp;";
    private static final String DEL_COMMENT = "delete from Comments where comment_id = ?";
    
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
                       } 
            catch (SQLException ex) {
                System.out.println(ex);
            }
        }
 public void printComments(int moduleId, PrintWriter out) {
         
     try (
          Connection conn = getConnection();
          PreparedStatement ps = conn.prepareStatement(PRINT_COMMENT);
           ){
           CommentReplyDb crdb = new CommentReplyDb();
           crdb.init();
           ps.setInt(1, moduleId);
           ResultSet rs = ps.executeQuery();
           out.println("<div class=\"jumbotron\">");
           out.println("<div class=\"container\">");
           out.println("<h1 class=\"display-4\">Kommentarer</h1>");  
           out.println("<hr class=\"my-4\">");
            while (rs.next()){
                 String commenttext = rs.getString("comment_text");
                 String author = rs.getString("user_name");
                 String commentid = rs.getString("comment_id");
                 int commentId = Integer.parseInt(commentid);
                 out.println("<h3>" + commenttext + "</h3>");
                 out.println("<h5>" + author + "</h5>");
                 crdb.printReplys(commentId,out);
                 out.println("<form action=\"OneModule?id="+ moduleId+"\" method=\"POST\">");
                 out.println("<input type=\"text\" name=\"delete\" value=\"TRUE\"style=\"visibility:hidden;\">");
                 out.println("<input type=\"text\" name=\"comment_id\" value=\""+ rs.getString("comment_id") +"\"style=\"visibility:hidden;\"/>");
                 out.println("<input type=\"submit\" class=\"btn btn-outline-danger\" value=\"Delete comment\">");
                 out.println("</form>");
                 out.println("<hr class=\"my-4\">");
                }
            } catch (SQLException ex) {
        System.out.println("Some error with the database" + ex);
        }     
    }
 
   public void deleteComment(int Commentid){
            try (
               Connection conn = getConnection();
               PreparedStatement ps = conn.prepareStatement(DEL_COMMENT)) {
               
               ps.setInt(1, Commentid);
               ps.executeUpdate();
 
       } catch (SQLException ex){
           System.out.println(ex);
       }
   }
}
 
     /*          
   private void deleteUI(HttpServletRequest request, int Commentid){           
           try (
               Connection conn = getConnection();
               PreparedStatement ps = conn.prepareStatement(DEL_COMMENT)) { 
       
       
       if (request.getMethod().equals("DELETE"))  {
                String comid = request.getParameter("comment_id");
                int commId = Integer.parseInt(comid);
                deleteComment(commId);
            }
       ps.setInt(1, Commentid);
               ps.executeUpdate();
       } catch (SQLException ex){
           System.out.println(ex);
       }          
   }*/
            



