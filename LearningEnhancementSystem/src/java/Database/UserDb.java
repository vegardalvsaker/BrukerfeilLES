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
    static final String USER_EXIST = "select  count(*) from Users where user_name = ?";
    static final String SLCT_USER = "select * from Users where user_name = ?";
    
    /**
     * Checks if there is a user in the database with the same name
     * @param email
     * @return 
     */
    public boolean checkUserExist(String username) {
        try 
            (
                Connection conn = getConnection();
                PreparedStatement chckUsr = conn.prepareStatement(USER_EXIST);
            )
            {
                chckUsr.setString(1, username);
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
    
    public User getUser(String id) {
        try (
            Connection conn = getConnection();
            PreparedStatement chckUsr = conn.prepareStatement(SLCT_USER)
        ) {
            chckUsr.setString(1, id);
            try (ResultSet rset = chckUsr.executeQuery();) {
                User user = new User();
                rset.next();
                user.setUserEmail(rset.getString("user_email"));
                user.setUserName(rset.getString("user_name"));
                user.setUserID(rset.getString("user_id"));
                user.setUserIsTeacher(rset.getBoolean("user_isTeacher"));
            
                return user;
            }
            
          }
        catch (SQLException ex) {
        System.out.println(ex);
        }
        return null;
    }
}
