package test.java.BAT;
import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test
public class LoginTest {
	
	public static WebDriver d;
	
	public static void setupChromeDriver() {
		   // OS type
		   if (System.getProperty("os.name").contains("Mac")) {
		      File cDriver = new File(LoginTest.class.getResource("/resources/mac/driver/chromedriver").getFile());
		 
		      // Is it executable
		      if (!cDriver.canExecute()) {
		         cDriver.setExecutable(true);
		      }
		      System.setProperty("webdriver.chrome.driver", LoginTest.class.getResource("/chromedriver").getFile());
		 
		      // Now checking for existence of Chrome executable.'
		      if (!new File("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome").exists()) {
		         throw new RuntimeException("errorMessage");
		      }
		   }
		 
		   ChromeOptions options = new ChromeOptions();
		   options.addArguments("--start-maximized");
		   options.addArguments("--ignore-certificate-errors");
		   d = new ChromeDriver(options);
	}
	
	@BeforeTest
	public void beforeTest() {	  
		//System.setProperty("webdriver.chrome.driver", "/Users/suchi/Downloads/chromedriver");
		//LoginTest.setupChromeDriver();
		d = new ChromeDriver();
	}		
	
	@AfterTest
	public void afterTest() {
		d.quit();			
	}	

	@Test
	public void f() {
		System.setProperty("webdriver.chrome.driver", "/Users/suchi/Downloads/chromedriver");
		WebDriver d = new ChromeDriver();
		Utilities.login(d);
  }
}
