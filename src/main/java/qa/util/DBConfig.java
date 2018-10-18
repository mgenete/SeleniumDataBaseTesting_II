package qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;



public class DBConfig {
	
	static Logger log = Logger.getLogger(DBConfig.class.getName());
	static Properties prop = new Properties();
	static FileInputStream fis;
	static File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\config\\config.properties");
	static Connection con = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	static ResultSetMetaData rsmd = null;
	static PreparedStatement preparedStatement = null;
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	public static String username;
	public static String password;
	
	
	
	public static String getDriver() {
		
		try {
			fis = new FileInputStream(file);
			prop.load(fis);
			return prop.getProperty("driver");
			
		} catch (Exception e){
			e.printStackTrace();
			
		}
		
		finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	
	public static String getConnectionString() {
		
		try {
			fis = new FileInputStream(file);
			prop.load(fis);
			return prop.getProperty("connectionString");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	
	public static Connection getDBConnection() throws IOException {
		
		try {
			
			Class.forName(getDriver());
			con = DriverManager.getConnection(getConnectionString());
			System.out.println("Connected to database");
			
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("Failed to connect to DB: "+ex);
		}
		return con;
	}
	
	
	public static void dbDisconnect() throws SQLException  {
		
		if(con != null && !con.isClosed()) {
			con.commit();
			con.close();
			System.out.println("Disconnected from database");
		}
	}
	
	
	public static Statement getStatement() {
		 
		try {
			
			stmt = con.createStatement();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return stmt;
	}
	
	
	
	public static void updateDB(String sqlQuery) {
		
		try {
			
			preparedStatement = con.prepareStatement(sqlQuery);
			int row = preparedStatement.executeUpdate();
			System.out.println(row+" rows updated successfully into database");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void insertDB(String sqlQuery) {
		
		try {
			
			preparedStatement = con.prepareStatement(sqlQuery);
			 int row = preparedStatement.executeUpdate();
			System.out.println(row+ " rows inserted successfully into database");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static ResultSet getTestFromDB(String sqlQuery) {
		
		try {
			
			rs = getStatement().executeQuery(sqlQuery);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	
	public static void deleteFromDB(String sqlQuery) {
		
		try {
			
			preparedStatement = con.prepareStatement(sqlQuery);
			int row = preparedStatement.executeUpdate();
			System.out.println(row+" rows deleted successfully from database");
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void showDB(String sqlQuery) {
		
		try {
			
			System.out.println("******** showing records **********");
			rs = getStatement().executeQuery(sqlQuery);
			rsmd = rs.getMetaData();
			int colNumb = rsmd.getColumnCount();
			while(rs.next()) {
				for(int i=1; i<=colNumb; i++) {
					System.out.println(rs.getString(i) +"\t");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static List<String> returnDataAsArrayList(String sqlQuery) throws IOException, SQLException {
		
		List<String> list = new ArrayList<String>();
		
		try {
			
			Class.forName(getDriver());
			con = DriverManager.getConnection(getConnectionString());
			System.out.println("Connected to database");
			
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("Failed to connect to DB: "+ex);
		}
		stmt = con.createStatement();
		rs = stmt.executeQuery(sqlQuery);
		rsmd = rs.getMetaData();
		int colNumb = rsmd.getColumnCount();
		while(rs.next()) {
			list.add(rs.getString(7)); //username column
			list.add(rs.getString(8)); //password column
			
		}
		return list;
	}
	
	
	public static String[] returnDataAsArray(String sqlQuery) throws IOException, SQLException {
		
		try {
			
			Class.forName(getDriver());
			con = DriverManager.getConnection(getConnectionString());
			System.out.println("Connected to database");
			
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("Failed to connect to DB: "+ex);
		}
		stmt = con.createStatement();
		rs = stmt.executeQuery(sqlQuery);
		rsmd = rs.getMetaData();
		int colNumb = rsmd.getColumnCount();
		while(rs.next()) {
			 username = rs.getString(7);
			 password = rs.getString(8);
			
		}
		return new String[] {username, password};
	}
	
	

	
	
	
	
	
	
	
	
	

}
