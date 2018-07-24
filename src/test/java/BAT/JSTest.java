package test.java.BAT;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class JSTest {
	
	public static WebDriver d;
	
	@BeforeTest
	public void beforeTest() {	  
		String target = System.getProperty("target");
		System.out.println("TARGET SITE: "+ target);
		
		System.setProperty("webdriver.chrome.driver", "/Users/suchi/Downloads/chromedriver");
		d = new ChromeDriver();

	}		
	
	@AfterTest
	public void afterTest() {
		d.quit();			
	}	
	
	@Test(priority=1)
	public void testIfLoggedIn() {
		Utilities.login(d);
		
		boolean exists = d.findElements( By.className("navbar-user")).size() != 0;
		Assert.assertEquals(true, exists);
	}
    @Test(priority=2)
	public void testForConsoleErrors() {
		Set<String> pages = Utilities.getPages();		
		
		for (String url : pages) {
			boolean isConsoleError = Utilities.gotoPageAndCheckJSErrors(d, url);
			
			Assert.assertEquals(false, isConsoleError);			
		}
	}


}
	
	

	
	
	


