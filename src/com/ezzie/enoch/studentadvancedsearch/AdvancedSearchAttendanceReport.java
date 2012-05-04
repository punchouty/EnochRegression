package com.ezzie.enoch.studentadvancedsearch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class AdvancedSearchAttendanceReport extends LoggedInUserTest {
		
	private String parentWindow = null;
	private String attendanceReport = "Attendance Report";
	private String reportButton = "stdent_atten_report";
	
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		parentWindow = driver.getWindowHandle();
	}

	@After
	public void tearDown() throws Exception {
		// driver.findElement(By.id("Cancel_Student_wizard")).click();
		driver.switchTo().window(parentWindow);
		super.tearDown();
	}
	

	@Test
	public void attendanceReportMonthly() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(attendanceReport);
		new Select(driver.findElement(By.id("mode_id"))).selectByVisibleText("Monthly");
		new Select(driver.findElement(By.id("month_id"))).selectByVisibleText("Apr");
		new Select(driver.findElement(By.id("advance_search_year"))).selectByVisibleText("2012");
		findElementById(reportButton);
		try {
			assertTrue(isElementPresent(By.cssSelector("div.att_detail")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	

	@Test
	public void attendanceReportOverall() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(attendanceReport);
		new Select(driver.findElement(By.id("mode_id"))).selectByVisibleText("Overall");
		findElementById(reportButton);
		try {
			assertTrue(isElementPresent(By.cssSelector("div.att_detail")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		
		}
	}

