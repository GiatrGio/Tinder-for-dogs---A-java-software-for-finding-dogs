package database;

import java.sql.*;

/**
* Generates a connection to the DogTinder database.
* @author Team D
* @see WriteBack and DataRetrieval, which use this connection.
*/
public final class OpenCloseConnection {
	//Singleton pattern
	private static OpenCloseConnection INSTANCE = null ;//new DataRetrieval();

	public static OpenCloseConnection getInstance() {  //this is our name for getInstance 
		if (INSTANCE == null) {
			INSTANCE = new OpenCloseConnection();
		}
		return INSTANCE;
	}
//	Open Connection to DB	
	public Connection connect (Connection con) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String connectionUrl = "jdbc:sqlserver://dogtinder.database.windows.net:1433;" +
				"database=DogTinder;" +
                "user=dogmaster;" +
                "password=W00fw00f";
		try {
			con = DriverManager.getConnection(connectionUrl);
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}	

//	Close Connectoin to DB	
	public void close (ResultSet rs, PreparedStatement ps, Connection con){
		try { rs.close(); } catch (Exception e) { /* ignored */ }
	    try { ps.close(); } catch (Exception e) { /* ignored */ }
	    try { con.close(); } catch (Exception e) { /* ignored */ }
	}
}
