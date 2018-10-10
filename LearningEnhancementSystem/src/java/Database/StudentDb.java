/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Classes.Student;
import java.sql.*;
/**
 *
 * @author Vegard
 */
public class StudentDb extends Database {
    private static final String SLCT_ONESTUDENT = "select * from Student where student_id = ?";
    
    public StudentDb(){
        init();
    }
    
    public Student getStudent(String student_id) {
        try(
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(SLCT_ONESTUDENT);
            ){
            ps.setString(1, student_id);
            try (ResultSet rs = ps.executeQuery();) {
                if (rs.last()) {
                    Student stud = new Student();
                    stud.setStudentId(student_id);
                    stud.setStudentName(rs.getString("student_name"));
                    stud.setStudentEmail(rs.getString("student_email"));
                    
                    return stud;
                }
            }
        }
        catch (SQLException e) {
            
        }
    return null;
    }
}
