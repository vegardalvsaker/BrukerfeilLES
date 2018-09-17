
/*
 *
 */
package Database;

import java.sql.*; 
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 * Database is a class that handles connection to a database, specified in the context.xml.
 *
 * @author Vegard Alvsaker
 */
public class Database {
    
    private DataSource ds;
    
    // @Resource DataSource LocalhostDS;
    /**
     * This method is to be called only once for each Database object. This method sets the DataSource ds variable to
     * the right Datasource in this project. 
     */
    public void init(){
        try {
         // Step 1: Allocate a database 'Connection' object
         Context cont = new InitialContext();
         ds = (DataSource)cont.lookup("java:comp/env/jdbc/localhostDS");
    }
        catch (NamingException nex) {
            System.out.println("Not correct naming" + nex);
        }
    }
/**
 * 
 * @return the Connection of the DataSource
 */
    protected Connection getConnection() {
        try{
            return ds.getConnection();  
        }
        
        catch (SQLException ex ) {
            System.out.println("Not connected to database " +ex);
        }
        return null;
    }
    /**
     * 
     * @param conn is the Connection the Statement will come from
     * @return the Statement object of Connection
     */
    public Statement getStatement(Connection conn) {
        try {
            return conn.createStatement();
        }
        catch (SQLException ex){
            System.out.println("Error communicating with the database, :" + ex);
        }
        return null;
    }         
}