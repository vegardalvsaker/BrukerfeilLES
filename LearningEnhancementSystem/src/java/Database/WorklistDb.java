package Database;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Espen
 */
public class WorklistDb extends Database {
    
    //private static final String uses the SQL database to collect the data we need and converts it to Stings so that we can use it later;
    private static final String GET_WORKLIST_ID =                   "select worklist_id from Worklist where teacher_id = ?";
    
    public WorklistDb(){
        init();
    }
        
    public String getWorklistId(String userId) {
        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(GET_WORKLIST_ID)) {
            ps.setString(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                return rs.getString("worklist_id");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Method: getWorklistId, error:" + ex);
            return null;
        }
        return null;
    }
    
}
