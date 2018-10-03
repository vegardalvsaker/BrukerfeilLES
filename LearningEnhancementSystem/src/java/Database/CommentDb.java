/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import java.sql.*;
import Classes.Comment;

/**
 *
 * @author Filip
 */
public class CommentDb extends Database{
    private static final String ADD_COMMENT = "insert into Comments values (default, ?, ?, ?, default)";
    private static final String PRINT_COMMENT = "select user_id, comment_id from Comments where module_id = ?";
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
   
    }

