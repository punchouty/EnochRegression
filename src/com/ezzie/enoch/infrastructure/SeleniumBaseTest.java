package com.ezzie.enoch.infrastructure;

import static org.junit.Assert.assertEquals;
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
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class SeleniumBaseTest {

	protected WebDriver driver;
	protected String baseUrl;
	protected StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass
	public static void loadConfiguration() {
		Properties prop = new Properties();
		try {
			InputStream in = new FileInputStream(new File(
					"conf/test.properties"));
			prop.load(in);
			Set<String> keys = prop.stringPropertyNames();
			for (String key : keys) {
				String value = System.getProperty(key);
				if (value == null)
					System.setProperty(key, prop.getProperty(key));
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void setUp() throws Exception {
		if (System.getProperty("browser", "Firefox")
				.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		} else if (System.getProperty("browser").equalsIgnoreCase("IE")) {
			DesiredCapabilities ieCapabilities = DesiredCapabilities
					.internetExplorer();
			ieCapabilities
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
			driver = new InternetExplorerDriver(ieCapabilities);

		} else if (System.getProperty("browser").equalsIgnoreCase("IE")) {
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
	
	protected String createAlphaNum(int length) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			builder.append("a7");
		}
		return builder.toString();
	}
	
	protected String createSpecialChars(int length) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			builder.append("a$");
		}
		return builder.toString();
	}

	protected String createNumber(int length) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			builder.append('9');
		}
		return builder.toString();
	}

	public void switchTOStudentWizard2() throws Exception {
		driver.findElement(By.cssSelector("#new_student > img.first-child"))
				.click();
		// ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp]]
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow]]
		driver.switchTo().window("Student Admission");
		driver.findElement(By.id("student_first_name")).clear();
		driver.findElement(By.id("student_first_name")).sendKeys("Vishal");
		driver.findElement(By.id("student_last_name")).clear();
		driver.findElement(By.id("student_last_name")).sendKeys("Handa");
		Select course = new Select(driver.findElement(By
				.id("adv_search_course_id")));
		course.selectByVisibleText("Nursery");
		Thread.sleep(3000);
		Select batch = new Select(driver.findElement(By.id("student_batch_id")));
		batch.selectByVisibleText("Nursery - A-2012");
		Thread.sleep(3000);
		driver.findElement(By.id("next_button")).click();
		// Thread.sleep(2000);
	}

	public void switchToStudentWizard3() throws Exception {
		driver.findElement(By.id("student_address_line1")).clear();
		driver.findElement(By.id("student_address_line1")).sendKeys("abcd");
		driver.findElement(By.id("student_city")).clear();
		driver.findElement(By.id("student_city")).sendKeys("abcd");
		driver.findElement(By.id("student_phone2")).clear();
		driver.findElement(By.id("student_phone2")).sendKeys("9999999999");
		driver.findElement(By.id("wizard_next_button")).click();
		try {
			assertTrue(isElementPresent(By.cssSelector("span.status-ok")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	

	public void switchToStudentWizard5BlankImage() throws Exception {
		driver.findElement(By.id("wizard_next_button")).click();
		try {
			assertTrue(isElementPresent(By.cssSelector("span.status-ok")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		String message = driver.findElement(By.cssSelector("ul.message.warning  > li")).getText();
		try {
			assertEquals("Student successfully created".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning  > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}


	public void switchToStudentWizard5WithImage() throws Exception {
		driver.findElement(By.id("upload_image")).sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\andesk.png");
		driver.findElement(By.id("wizard_next_button")).click();
		try {
			assertEquals("Student succesfully updated".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning  > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertTrue(isElementPresent(By.id("previews")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.id("wizard_next_button")).click();
		try {
			assertEquals("Successfully updated user.".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning  > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	public void switchToStudentUpdateUnderStudentSearch() throws Exception {
		driver.findElement(By.linkText("Student Search")).click();
		driver.findElement(By.id("target")).clear();
		driver.findElement(By.id("target")).sendKeys("elia");
		driver.findElement(By.cssSelector("img.with-tip")).click();
	}
}
