package qa.test;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;



import qa.util.DBConfig;

public class TC_01_DataBaseTest {
	
	@Test
	public void validateGetData() throws IOException, SQLException {
		
		
		DBConfig.getDBConnection();
		ResultSet rs = DBConfig.getTestFromDB(" select * from customer.customers");
		ResultSetMetaData rsmd = rs.getMetaData();
		int colNumb = rsmd.getColumnCount();
		while(rs.next()) {
			for(int i=1; i<=colNumb; i++) {
				System.out.print(" "+rsmd.getColumnName(i));
				System.out.println(" -> "+rs.getString(i));
			}
			System.out.println();
		}
		DBConfig.dbDisconnect();
		
	}
	
	
	@Test
	public void validateShowDataBase() throws IOException, SQLException {
		
		DBConfig.getDBConnection();
		DBConfig.showDB("select * from customer.customers");
		DBConfig.dbDisconnect();
	}
	
	
	@Test
	public void validateInsertIntoDataBase() throws IOException, SQLException {
		
		DBConfig.getDBConnection();
		DBConfig.showDB("select * from customer.customers");
		System.out.println("\n\n\n\n");
		
		DBConfig.insertDB("insert into customer.customers values (110, 'Subsciption', 'Nelson', 'Mandela', 'nel@gmail.com', 'nel@gmail.com', 'tom1234', 'qw12345', 'qw12345')");
		DBConfig.showDB("select * from customer.customers");
		DBConfig.dbDisconnect();
	}
	
	
	@Test
	public void insertIntoDB() throws IOException, SQLException {
		
		String customer_id ="200";
		String edition ="Free Edition";
		String first_name ="Michael";
		String last_name ="Jackson";
		String email ="mhanc@gmail.com";
		String confirm_email ="mhanc@gmail.com";
		String username ="mj123";
		String password ="aq123";
		String confirm_password ="aq123";
		
		
		DBConfig.getDBConnection();
		DBConfig.showDB("select * from customer.customers");
		System.out.println("\n\n\n\n");
		
		DBConfig.insertDB("insert into customer.customers values ('"+customer_id+"', '"+edition+"', '"+first_name+"', '"+last_name+"', '"+email+"', '"+confirm_email+"', '"+username+"', '"+password+"', '"+confirm_password+"')");
		
		ResultSet rs = DBConfig.getTestFromDB("select * from customer.customers where customer_id=200");
		
		try {
			System.out.println("Asserting values with database");
			Assert.assertEquals(rs.getString("customer_id"), customer_id);
			Assert.assertEquals(rs.getString("edition"), edition);
			Assert.assertEquals(rs.getString("first_name"), first_name);
			Assert.assertEquals(rs.getString("last_name"), last_name);
			Assert.assertEquals(rs.getString("email"), email);
			Assert.assertEquals(rs.getString("6"), confirm_email);
			Assert.assertEquals(rs.getString("username"), username);
			Assert.assertEquals(rs.getString("password"), password);
			Assert.assertEquals(rs.getString("9"), confirm_password);
		} catch(Exception e) {
			e.getMessage();
		}
		finally {
			DBConfig.showDB("select * from customer.customers");
			System.out.println("DB verification completed");
		}
		DBConfig.dbDisconnect();
	}
	
	
	
	@Test
	public void validateDeleteDataBase() throws IOException, SQLException {
		
		DBConfig.getDBConnection();
		DBConfig.showDB("select * from customer.customers");
		DBConfig.deleteFromDB("delete from customer.customers where customer_id=200");
		
		System.out.println("\n\n\n\n");
		DBConfig.showDB("select * from customer.customers");
		DBConfig.dbDisconnect();
		
	}
	
	
	@Test
	public void validateUpdateDB() throws SQLException, IOException {
		
		DBConfig.getDBConnection();
		DBConfig.showDB("select * from customer.customers");
		DBConfig.updateDB(" update customer.customers set edition = 'Unkown' where customer_id=101");
		
		System.out.println("\n\n\n\n");
		DBConfig.showDB("select * from customer.customers");
		DBConfig.dbDisconnect();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
