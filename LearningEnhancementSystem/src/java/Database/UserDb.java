/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Classes.User;

/**
 *
 * @author Vegard
 */
public class UserDb extends Database {
    static final String SLCT_USER= "select * from Users where user_id = ?";
    
    public boolean checkUser(String email) {
        PreparedStatement chckUsr = null;
        Connection conn = null;
        ResultSet rset = null;
        try {
            conn = getConnection();
            chckUsr = conn.prepareStatement(SLCT_USER);
            chckUsr.setString(1, email);
            rset = chckUsr.executeQuery(); 
            
          }
        catch (SQLException ex) {
            System.out.println("User does not exist:" + ex);
            return false;
        } finally {
            try {
               if (rset != null) {rset.close();}
               if (chckUsr != null) {chckUsr.close();}
               if (conn != null) {conn.close();} 
            }
           catch (SQLException ex){
               System.out.println(ex);
           }
        }
        return true;
    }
    
    public User getUser(String id) {
        try (
            Connection conn = getConnection();
            PreparedStatement chckUsr = conn.prepareStatement(SLCT_USER)
        ) {
            chckUsr.setString(1, id);
            ResultSet user = chckUsr.executeQuery();
            
            user.last();
            String email = user.getString("user_email");
            String name = user.getString("user_name");
            
            User userObj = new User(id, name, email);
            return userObj;
          }
        catch (SQLException ex) {
        System.out.println(ex);
        }
        return null;
    }
}
