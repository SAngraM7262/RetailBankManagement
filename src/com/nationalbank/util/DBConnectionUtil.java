/**
 * 
 */
package com.nationalbank.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author SAngraM
 * 
 */
public class DBConnectionUtil {
	
//private static final String URL = "jdbc:mysql://node42204-env-6949795.cloudjiffy.net/customerdirectory";
private static final String URL = "jdbc:mysql://localhost:3306/customerDirectory";
	
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	
	private static final String USERNAME = "root";
	
	private static final String PASSWORD = "root";
	//private static final String PASSWORD = "NW3OxS4S54";
	
	private static Connection connection = null;
	
	public static Connection getConnection() {
		if (connection != null)
            return connection;
        else {
            try {
                Class.forName(DRIVER);
                //System.out.println("Connecting to " + URL);
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                //System.out.println("Connected");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } 
            return connection;
        }
	}
	

}
