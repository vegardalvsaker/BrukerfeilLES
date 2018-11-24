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
    private static final String UDATE_MESSAGE_READ = "update Message set msg_read = true where msg_id = ?";
    private static final String INSERT_MESSAGE = "insert into Message values (default, ?, ?, ?, ?, default, default)";
    
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
    
    public void sendMessage(String[] messageInfo) {
        UserDb uDb = new UserDb();
        uDb.init();
        String recipientId = uDb.getUserId(messageInfo[1]);
        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(INSERT_MESSAGE);
                ) {
            ps.setString(1, messageInfo[0]);
            ps.setString(2, recipientId);
            ps.setString(3, messageInfo[2]);
            ps.setString(4, messageInfo[3]);
            ps.executeUpdate();
                                                
        } catch (SQLException ex) {
            System.out.println("Method: sendMessage(), Error: " + ex);
        }
    }
    
    public void updateMessageIsRead(String msgId) {
        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(UDATE_MESSAGE_READ);) {
            ps.setString(1, msgId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Method: updateMessageIsRead(), error: " + ex);
        }
    }
}
