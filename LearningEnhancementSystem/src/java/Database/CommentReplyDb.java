/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import java.sql.*;
import Classes.CommentReply;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Marius
 */
public class CommentReplyDb extends Database{
    private static final String ADD_REPLY = "insert into CommentReply values (default, ?, ?, default,?)";
    private static final String PRINT_REPLY = "select r.reply_id, r.reply_text, u.user_name from CommentReply r inner join Users u on r.user_id = u.user_id where r.comment_id = ? order by r.reply_timestamp;";
    private static final String DEL_SREPLY = "delete from CommentReply where reply_id = ?";
    private static final String DEL_AREPLY = "delete from CommentReply where comment_id = ?";

    public List<CommentReply> getCommentReplys(){
        List<CommentReply> replys = new ArrayList<>();
    
        try (
             Connection conn = getConnection();
             Statement stmt = getStatement(conn);
             ResultSet replySet = stmt.executeQuery(PRINT_REPLY);
            ){ 
              while(replySet.next()){
                  CommentReply rep = new CommentReply();
                  rep.setReplyid(replySet.getInt("reply_id"));
                  rep.setReplyTimestamp(replySet.getTimestamp("reply_timestamp"));
                  rep.setReplyText(replySet.getString("reply_text"));
                  replys.add(rep);
              }
              return replys;
        }
        catch (SQLException ex) {
            System.out.println("Query error:" + ex);
        }
        return null;
    }
public void addReply(int comment_id,String user_id, String reply_text){
        
            try (
                    Connection conn = getConnection();
                    PreparedStatement ps = conn.prepareStatement(ADD_REPLY);
                    ) {
                         ps.setInt(1, comment_id);
                         ps.setString(2, user_id);
                         ps.setString(3,reply_text);
                         ps.executeUpdate();
                       } 
            catch (SQLException ex) {
                System.out.println(ex);
            }
        } 
public void printReplys(int commentId, int moduleId, PrintWriter out) {
         
     try (
          Connection conn = getConnection();
          PreparedStatement ps = conn.prepareStatement(PRINT_REPLY);
           ){
           ps.setInt(1, commentId);
           ResultSet rs = ps.executeQuery();
           
            while (rs.next()){
                int replyid = Integer.parseInt(rs.getString("reply_id"));
                String replytext = rs.getString("reply_text");
                String author = rs.getString("user_name");
                out.println("<p style=\"margin-left:2.5em;\">" + replyid +" "+ replytext + "</p>");
                out.println("<p style=\"margin-left:2.5em;\">" + author + "</p>");
                out.println("<form action=\"OneModule?id="+ moduleId+"\" method=\"POST\">");
                out.println("<input type=\"text\" name=\"deleteR\" value=\"TRUE\"style=\"visibility:hidden;\">");
                out.println("<input type=\"text\" name=\"reply_id\" value=\""+ replyid +"\"style=\"visibility:hidden;\"/>");
                out.println("<input type=\"submit\" class=\"btn btn-outline-danger\" value=\"Delete reply\">");
                out.println("</form>");
            }

            } catch (SQLException ex) {
                    System.out.println("Some error with the database" + ex);
            } 
        }   
public void deleteSingle(int Replyid){
            try (
               Connection conn = getConnection();
               PreparedStatement ps = conn.prepareStatement(DEL_SREPLY)) {
               
               ps.setInt(1, Replyid);
               ps.executeUpdate();
 
       } catch (SQLException ex){
           System.out.println(ex);
       }
   }
public void deleteAll(int Commentid){
            try (
               Connection conn = getConnection();
               PreparedStatement ps = conn.prepareStatement(DEL_AREPLY)) {
               
               ps.setInt(1, Commentid);
               ps.executeUpdate();
 
       } catch (SQLException ex){
           System.out.println(ex);
       }
   }
}