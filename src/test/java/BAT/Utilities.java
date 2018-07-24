package test.java.BAT;

import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

public class Utilities {

	public static boolean login(WebDriver driver) {
		String url = "https://qatesting.scalyr.com/login";
		driver.get(url);
		return true;

	}

	public static boolean injectJSConsoleErrors(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// add some test messages to make sure we are reading from console
		js.executeScript("console.error('QA-AUTOMATION: ERROR')");
		js.executeScript("console.warn('QA-AUTOMATION: WARN')");
		js.executeScript("console.log('QA-AUTOMATION: LOG')");
		js.executeScript("console.info('QA-AUTOMATION: INFO')");

		return false;
	}

	public static boolean isThereJSErrorOnThePage(WebDriver driver) {
		Set<String> errorStrings = new HashSet<>();
		errorStrings.add("SyntaxError");
		errorStrings.add("EvalError");
		errorStrings.add("ReferenceError");
		errorStrings.add("RangeError");
		errorStrings.add("TypeError");
		errorStrings.add("URIError");

		LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);

		for (LogEntry logEntry : logEntries) {
			System.out.println(logEntry);

			String message = logEntry.getMessage();
			if (message.contains("QA-AUTOMATION")) {
				System.out.println("Ignoring QA test message: " + message);
			}

			for (String errorString : errorStrings) {
				if (message.contains(errorString)) {
					System.out.println("Found JS error, quitting (" + message + ")");
					return true;
				}
			}
		}

		return false;
	}

	public static void gotoPage(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
		System.out.println("In page: " + pageUrl);
	}

	public static boolean gotoPageAndCheckJSErrors(WebDriver d, String pageUrl) {
		// TODO Auto-generated method stub
		Utilities.gotoPage(d, pageUrl);

		Utilities.injectJSConsoleErrors(d);

		return Utilities.isThereJSErrorOnThePage(d);
	}

	public static Set<String> getPages() {
		Set<String> pages = new HashSet<>();
		pages.add("https://qatesting.scalyr.com/alerts");
		pages.add("https://qatesting.scalyr.com/events");
		pages.add("https://qatesting.scalyr.com/dash");
		pages.add("https://qatesting.scalyr.com/help");
		pages.add("https://qatesting.scalyr.com/logStart");

		return pages;
	}

}
