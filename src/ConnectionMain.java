import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;

public class ConnectionMain {

	final static String DB_URL = "jdbc:oracle:thin:@localhost:1521/XE";
	final static String DB_USER = "sys as sysdba";
	final static String DB_PASSWORD = "password";

	public static void main(String[] args) throws SQLException {
		
		Properties info = new Properties();     
	    info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
	    info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PASSWORD);
	    
	    OracleDataSource ods = new OracleDataSource();
	    ods.setURL(DB_URL);    
	    ods.setConnectionProperties(info);
	    
	    try (OracleConnection connection = (OracleConnection) ods.getConnection()) {
	        // Get the JDBC driver name and version 
	        DatabaseMetaData dbmd = connection.getMetaData();       
	        System.out.println("Driver Name: " + dbmd.getDriverName());
	        System.out.println("Driver Version: " + dbmd.getDriverVersion());
	        System.out.println();
	        // Perform a database operation 
	        SimpleQuery simpleQuery = new SimpleQuery();
	        simpleQuery.printEmployees(connection);
	      }  
		System.out.println("Hello World");

	}
	
	

}
