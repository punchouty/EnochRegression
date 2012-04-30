package com.ezzie.enoch.user;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.internal.seleniumemulation.SelectWindow;
import org.openqa.selenium.internal.seleniumemulation.WaitForCondition;
import org.openqa.selenium.internal.seleniumemulation.WaitForPageToLoad;
import org.openqa.selenium.internal.seleniumemulation.WaitForPopup;

import com.ezzie.enoch.infrastructure.SeleniumBaseTest;
import com.sun.org.apache.bcel.internal.generic.Select;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import com.thoughtworks.selenium.Wait;

public class AdminDashboardScreen extends SeleniumBaseTest {

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void testNewAdmission() throws Exception {
		driver.get(baseUrl + "/sessions/new");
		driver.findElement(By.id("session_username")).clear();
		driver.findElement(By.id("session_username")).sendKeys("E0001");
		driver.findElement(By.id("session_password")).clear();
		driver.findElement(By.id("session_password")).sendKeys("password");
		driver.findElement(By.name("commit")).click();
		driver.findElement(By.cssSelector("h4.last-child")).click();
		//driver.wait(3000);
		driver.switchTo().window("Student Admission");
		driver.findElement(By.id("student_first_name")).sendKeys("Vishal");
		//driver.wait(3000);
		
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

}
