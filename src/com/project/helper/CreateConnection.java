package com.project.helper;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
// this is a  helper class for creating database connection
public final class CreateConnection {
private static  Connection connection =null ;

public static Connection getCon() throws SQLException
{ 
	
	Properties p= null;
	FileInputStream fis;
	try {
		fis = new FileInputStream("Connection.properties");
		p = new Properties();
		
		p.load(fis);

	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
		
	String driver = p.getProperty("dn");
	String url = p.getProperty("url");
	String user = p.getProperty("user");
	String pwd = p.getProperty("pwd");
	
	// making sure that only one connection object exist 
	// create object only when connection is closed or does not exist. 
	if(connection==null || connection.isClosed()== true){
		try {
	// Registering or loading  oracle driver		
			Class.forName(driver);
	// calling for database Connection object
			connection=DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException | SQLException e) {
	// handling exception 
			e.printStackTrace();
		}	
	}
	// returning database connection object
	return connection;
}

}
