/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import java.sql.*;
import Classes.CommentReply;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Marius
 */
public class CommentReplyDb extends Database{
    private static final String ADD_REPLY = "insert into CommentReply values (default, ?, ?, default,?)";
    private static final String ALL_REPLYS = "select r.reply_id, r.comment_id, r.reply_text, u.user_name from CommentReply r inner join Users u on r.user_id = u.user_id where r.comment_id = ? order by r.reply_timestamp;";
    private static final String DEL_SREPLY = "delete from CommentReply where reply_id = ?";
    private static final String DEL_AREPLY = "delete from CommentReply where comment_id = ?";
    
    public CommentReplyDb() {
        init();
    }
    
    public List<CommentReply> getCommentReplys(String commentId){
        List<CommentReply> replys = new ArrayList<>();
    
        try (
             Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(ALL_REPLYS);)
             
            { 
             ps.setString(1, commentId);
             try (ResultSet replySet = ps.executeQuery();){
              while(replySet.next()){
                  CommentReply rep = new CommentReply();
                  rep.setReplyId(replySet.getString("reply_id"));
                  rep.setCommentId(replySet.getString("comment_id"));
                  rep.setReplyText(replySet.getString("reply_text"));
                  rep.setUserName(replySet.getString("user_name"));
                  replys.add(rep);
              }
              return replys;
        }
            }
        catch (SQLException ex) {
            System.out.println("Query error:" + ex);
        }
        return null;
    }
public void addReply(String comment_id,String user_id, String reply_text){
        
            try (
                    Connection conn = getConnection();
                    PreparedStatement ps = conn.prepareStatement(ADD_REPLY);
                    ) {
                         ps.setString(1, comment_id);
                         ps.setString(2, user_id);
                         ps.setString(3,reply_text);
                         ps.executeUpdate();
                       } 
            catch (SQLException ex) {
                System.out.println(ex);
            }
        }    
public void deleteSingle(String Replyid){
            try (
               Connection conn = getConnection();
               PreparedStatement ps = conn.prepareStatement(DEL_SREPLY)) {
               
               ps.setString(1, Replyid);
               ps.executeUpdate();
 
       } catch (SQLException ex){
           System.out.println(ex);
       }
   }
public void deleteAll(String Commentid){
            try (
               Connection conn = getConnection();
               PreparedStatement ps = conn.prepareStatement(DEL_AREPLY)) {
               
               ps.setString(1, Commentid);
               ps.executeUpdate();
 
       } catch (SQLException ex){
           System.out.println(ex);
       }
   }
}