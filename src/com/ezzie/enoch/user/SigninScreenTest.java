package com.ezzie.enoch.user;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.ezzie.enoch.infrastructure.SeleniumBaseTest;

public class SigninScreenTest extends SeleniumBaseTest {
		
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void testEmptyUserAndEmptyPassword() {
		driver.get(baseUrl + "/signin");
		driver.findElement(By.id("session_username")).clear();
		driver.findElement(By.id("session_username")).sendKeys("");
		driver.findElement(By.id("session_password")).clear();
		driver.findElement(By.id("session_password")).sendKeys("");
		driver.findElement(By.name("commit")).click();
		String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		verifyTextPresent(warning, "PLEASE ENTER YOUR USER NAME");
	}	


	@Test
	public void testEmptyUser() {
		driver.get(baseUrl + "/signin");
		driver.findElement(By.id("session_username")).clear();
		driver.findElement(By.id("session_username")).sendKeys("");
		driver.findElement(By.id("session_password")).clear();
		driver.findElement(By.id("session_password")).sendKeys("xyz");
		driver.findElement(By.name("commit")).click();
		String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		verifyTextPresent(warning, "PLEASE ENTER YOUR USER NAME");
	}

	@Test
	public void testEmptyPassword() {
		driver.get(baseUrl + "/signin");
		driver.findElement(By.id("session_username")).clear();
		driver.findElement(By.id("session_username")).sendKeys("E0001");
		driver.findElement(By.id("session_password")).clear();
		driver.findElement(By.id("session_password")).sendKeys("");
		driver.findElement(By.name("commit")).click();
		String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		verifyTextPresent(warning, "PLEASE ENTER YOUR PASSWORD");
	}	


	@Test
	public void testWrongUserPasswordCombination() {
		driver.get(baseUrl + "/signin");
		driver.findElement(By.id("session_username")).clear();
		driver.findElement(By.id("session_username")).sendKeys("E0001");
		driver.findElement(By.id("session_password")).clear();
		driver.findElement(By.id("session_password")).sendKeys("abcdef");
		driver.findElement(By.name("commit")).click();
		String warning = driver.findElement(By.cssSelector("ul.error")).getText();
		verifyTextPresent(warning, "INVALID");
	}	


	@Test
	public void testSuccessfulLogin() {
		driver.get(baseUrl + "/signin");
		driver.findElement(By.id("session_username")).clear();
		driver.findElement(By.id("session_username")).sendKeys("E0001");
		driver.findElement(By.id("session_password")).clear();
		driver.findElement(By.id("session_password")).sendKeys("password");
		driver.findElement(By.name("commit")).click();
		String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		System.out.println(warning);
		verifyTextPresent(warning, "WELCOME");
		driver.get(baseUrl + "/signout");
	}
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

}
