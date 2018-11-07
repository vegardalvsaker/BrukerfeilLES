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
    private static final String ALL_COMMENTS = "select c.module_id, c.comment_id, c.comment_text, u.user_name from Comments c inner join Users u on c.user_id = u.user_id order by c.comment_timestamp";
    private static final String PRINT_COMMENT = "select c.comment_id, c.comment_text, u.user_name from Comments c inner join Users u on c.user_id = u.user_id where c.module_id = ? order by c.comment_timestamp;";
    private static final String DEL_COMMENT = "delete from Comments where comment_id = ?";
    
    public List<Comment> getComments(){
        List<Comment> comments = new ArrayList<>();
    
        try (
             Connection conn = getConnection();
             Statement stmt = getStatement(conn);
             ResultSet commentSet = stmt.executeQuery(ALL_COMMENTS);
            ){ 
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
                 crdb.printReplys(commentId,moduleId,out);
                 crdb.addReply(commentId, author, commenttext);
                 crdb.addReplyForm(out,moduleId,commentId);
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
   public void addCommentForm(PrintWriter out, int moduleId){
            out.println("<div>");
            out.println("<form action=\"OneModule?id="+ moduleId +"\" method=\"POST\">");
            out.println("<input type=\"hidden\" name=\"delete\" value=\"FALSE\"");
            out.println("<h3>Legg til kommentar</h3><br>");
            out.println("<input type =\"text\" name=\"comment\"><br>");           
            out.println("<br>");
            out.println("<input type=\"submit\" value=\"Legg til\"><br>");        
            out.println("<br>");
            out.println("</form>");
            out.println("</div>");
    }
}    
/*
<div id="accordion">
  <div class="card">
    <div class="card-header" id="headingOne">
      <h5 class="mb-0">
        <button class="btn btn-link" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
          Collapsible Group Item #1
        </button>
      </h5>
    </div>

    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
      <div class="card-body">
        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
      </div>
    </div>
  </div>
</div>*/