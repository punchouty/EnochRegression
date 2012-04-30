package com.ezzie.enoch.infrastructure;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SeleniumBaseTest {
	
	protected WebDriver driver;
	protected String baseUrl;
	protected StringBuffer verificationErrors = new StringBuffer();
	
	@BeforeClass
	public static void loadConfiguration(){
		Properties prop = new Properties();
		try {
			InputStream in = new FileInputStream(new File("conf/test.properties"));
			prop.load(in);
			Set<String> keys = prop.stringPropertyNames();
			for (String key : keys) {
				String value = System.getProperty(key);
				if(value == null) System.setProperty(key, prop.getProperty(key));
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public void setUp() throws Exception {
		if(System.getProperty("browser", "Firefox").equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		}
		else if(System.getProperty("browser").equalsIgnoreCase("IE")) {
			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();  
			ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			driver = new InternetExplorerDriver(ieCapabilities);
			
		}
		else if(System.getProperty("browser").equalsIgnoreCase("IE")) {
			driver = new ChromeDriver();
		}
		baseUrl = System.getProperty("baseUrl");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		System.out.println(verificationErrorString);
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	protected boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	protected void verifyTextPresent(String source, String expected) {
		assertTrue(source.indexOf(expected) >= 0);
	}
	
	protected String createString(int length) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			builder.append('a');
		}
		return builder.toString();
	}
	

}
