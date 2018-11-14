/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.util.ArrayList;
import Classes.Message;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author Vegard
 */
public class InboxDb extends Database {
    
    private static final String SELECT_ALL_MESSAGES = "select * from Message where msg_sender = ? or msg_receiver = ?";
    
    public InboxDb() {
        init();
    }
    
    public ArrayList<Message> getUsersMessages(String userId) {
        ArrayList<Message> messages = new ArrayList<>();
        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(SELECT_ALL_MESSAGES);)
                 {
            ps.setString(1, userId);
            ps.setString(2, userId);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    Message message = new Message();
                    message.setMsgId(rs.getString("msg_id"));
                    message.setSubject(rs.getString("msg_subject"));
                    message.setSender(rs.getString("msg_sender"));
                    message.setReceiver(rs.getString("msg_receiver"));
                    message.setText(rs.getString("msg_text"));
                    message.setTimestamp(rs.getTimestamp("msg_timestamp"));
                    message.setRead(rs.getBoolean("msg_read"));
                    messages.add(message);
                }
                return messages;
            }
        } catch (SQLException ex) {
                System.out.println("Method: getUsersMessages(), Error: " + ex);
                return null;
                }  
    }
}
