package com.ezzie.enoch.studentadvancedsearch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class AdvancedSearchAward extends LoggedInUserTest {

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
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(studentAward);
		findElementById(awardButton);
	}
	
	@Test
	public void titleAlphabets() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(studentAward);
		alphabetsMinLength(awardTitle);
		findElementById(awardButton);
		verifyEnterDescription();
	}
	
	@Test
	public void titleAlphanumerics() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(studentAward);
		alphanumericsMinLength(awardTitle);
		findElementById(awardButton);
		verifyEnterCharsAwardTitle();
	}
	
	@Test
	public void titleSpecialChars() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(studentAward);
		specialCharMinLength(awardTitle);
		findElementById(awardButton);
		verifyEnterCharsAwardTitle();
	}
	
	@Test
	public void titleMaxLength() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(studentAward);
		alphabetMaxLength(awardTitle);
		alphabetsMinLength(awardDescription);
		findElementById(awardButton);
		verifyMaxLengthFifty();
	}
	
	@Test
	public void descriptionEmpty() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(studentAward);
		alphabetsMinLength(awardTitle);
		findElementById(awardButton);
		verifyEnterDescription();
	}
	
	@Test
	public void descriptionAlphabets() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(studentAward);
		alphabetsMinLength(awardTitle);
		alphabetsMinLength(awardDescription);
		findElementById(awardButton);
		verifyAwardSuccessCreated();
	}
	@Test
	public void descriptionAlphanumerics() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(studentAward);
		alphabetsMinLength(awardTitle);
		alphanumericsMinLength(awardDescription);
		findElementById(awardButton);
		verifyEnterCharsAwardDescription();
	}
	
	@Test
	public void descriptionSpecialChars() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(studentAward);
		alphabetsMinLength(awardTitle);
		specialCharMinLength(awardDescription);
		findElementById(awardButton);
		verifyEnterCharsAwardTitle();
	}
	
	@Test
	public void descriptionMaxLength() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(studentAward);
		alphabetsMinLength(awardTitle);
		alphabetMaxLength(awardDescription);
		findElementById(awardButton);
		verifyMaxLengthFifty();
	}
}
