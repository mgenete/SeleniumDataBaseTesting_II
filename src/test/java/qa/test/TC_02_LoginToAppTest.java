package qa.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import qa.util.DBConfig;



public class TC_02_LoginToAppTest {
	
	
	public static WebDriver driver;
	static Logger log = Logger.getLogger(TC_02_LoginToAppTest.class.getName());
	
	@Test
	public void validateLogin() throws IOException, SQLException {
	
	
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.get("https://www.freecrm.com/index.html");
			
			List<String> testdata = DBConfig.returnDataAsArrayList("select * from customer.customers");
//			log.info("Data from DB: username "+testdata.get(0));
//			log.info("Data from DB: password "+testdata.get(1));
//			log.info("Data from DB: username "+testdata.get(2));
//			log.info("Data from DB: password "+testdata.get(3));
			log.info("Data from DB: username "+testdata.get(4));
			log.info("Data from DB: password "+testdata.get(5));
			
			driver.findElement(By.xpath("//input[@name='username']")).sendKeys(testdata.get(4));
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(testdata.get(5));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@type='submit']")));
			
			DBConfig.dbDisconnect();
			
	}

}
