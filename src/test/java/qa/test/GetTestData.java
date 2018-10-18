package qa.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import org.testng.annotations.Test;

import qa.util.DBConfig;

public class GetTestData {
	
	DBConfig config;
	protected ResultSet rs= null;
	protected Statement stmt= null;
	String sqlQuery1 = "select * from customer.customers where customer_id = 102";
	String query2= "SELECT COUNT(*) AS COUNT FROM customer.customers";
	
	@Test
	public void getData() throws SQLException, IOException {
		config = new DBConfig();
		Connection con = DBConfig.getDBConnection();
		stmt = con.createStatement();
		rs = stmt.executeQuery(sqlQuery1);
		while (rs.next()) {
			System.out.println(rs.getString("customer_id")+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)
			+"\t"+rs.getString(5)+"\t"+rs.getString(7)+"\t"+rs.getString(9));
			
			System.out.println("The current row :"+rs.getRow());
		}
		DBConfig.dbDisconnect();
		
		
	}

}
