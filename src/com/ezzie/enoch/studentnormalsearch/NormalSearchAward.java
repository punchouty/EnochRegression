package com.ezzie.enoch.studentnormalsearch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class NormalSearchAward extends LoggedInUserTest {

	private String parentWindow = null;
	private String studentAward = "Student Award";
	private String awardButton = "create_student_award";
	private String awardTitle = "award_title";
	private String awardDescription = "award_description";
	
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
	public void titleEmpty() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		findElementLinkText(studentAward);
		findElementById(awardButton);
		try {
			assertEquals("Please enter the Title".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void titleAlphabets() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		findElementLinkText(studentAward);
		alphabetsMinLength(awardTitle);
		findElementById(awardButton);
		try {
			assertEquals("Please enter the Description".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void titleAlphanumerics() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		findElementLinkText(studentAward);
		alphanumericsMinLength(awardTitle);
		findElementById(awardButton);
		try {
			assertEquals("Please enter characters for Award Title".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void titleSpecialChars() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		findElementLinkText(studentAward);
		specialCharMinLength(awardTitle);
		findElementById(awardButton);
		try {
			assertEquals("Please enter characters for Award Title".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void titleMaxLength() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		findElementLinkText(studentAward);
		alphabetMaxLength(awardTitle);
		alphabetsMinLength(awardDescription);
		findElementById(awardButton);
		try {
			assertEquals("Max length is 50 characters".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void descriptionEmpty() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		findElementLinkText(studentAward);
		alphabetsMinLength(awardTitle);
		findElementById(awardButton);
		try {
			assertEquals("Please enter the Description".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void descriptionAlphabets() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		findElementLinkText(studentAward);
		alphabetsMinLength(awardTitle);
		alphabetsMinLength(awardDescription);
		findElementById(awardButton);
		try {
			assertEquals("Student award is successfully created.".toUpperCase(), driver.findElement(By.cssSelector("ul.message.success > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	@Test
	public void descriptionAlphanumerics() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		findElementLinkText(studentAward);
		alphabetsMinLength(awardTitle);
		alphanumericsMinLength(awardDescription);
		findElementById(awardButton);
		try {
			assertEquals("Please enter characters for Award description".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void descriptionSpecialChars() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		findElementLinkText(studentAward);
		alphabetsMinLength(awardTitle);
		specialCharMinLength(awardDescription);
		findElementById(awardButton);
		try {
			assertEquals("Please enter characters for Award Title".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void descriptionMaxLength() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		findElementLinkText(studentAward);
		alphabetsMinLength(awardTitle);
		alphabetMaxLength(awardDescription);
		findElementById(awardButton);
		try {
			assertEquals("Max length is 50 characters".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
}
