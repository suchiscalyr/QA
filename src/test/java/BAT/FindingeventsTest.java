package test.java.BAT;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test
public class FindingeventsTest {
	
	public static WebDriver d;
	
	@BeforeTest
	public void beforeTest() {	  
		System.setProperty("webdriver.chrome.driver", "/Users/suchi/Downloads/chromedriver");
		d = new ChromeDriver();

	}		
	
	@AfterTest
	public void afterTest() {
		d.quit();			
	}	

	public void f() {
		Utilities.login(d);
		
		String url = "https://qatesting.scalyr.com/events";
		Utilities.gotoPage(d,  url);
		
		
		//Find number of events displayed
	    List<WebElement> elements = d.findElements(By.className("log-line"));
	    System.out.println("found log lines: " + elements.size());
		
	}
	
	
}
 