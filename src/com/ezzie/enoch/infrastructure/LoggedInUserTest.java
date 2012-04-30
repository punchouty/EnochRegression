package com.ezzie.enoch.infrastructure;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;


public class LoggedInUserTest extends SeleniumBaseTest {
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		driver.get(baseUrl + "/signin");
		driver.findElement(By.id("session_username")).clear();
		driver.findElement(By.id("session_username")).sendKeys("E0001");
		driver.findElement(By.id("session_password")).clear();
		driver.findElement(By.id("session_password")).sendKeys("password");
		driver.findElement(By.name("commit")).click();
	}
	
	@After
	public void tearDown() throws Exception {
		driver.findElement(By.linkText("LOGOUT")).click();	
		super.tearDown();
	}

}
