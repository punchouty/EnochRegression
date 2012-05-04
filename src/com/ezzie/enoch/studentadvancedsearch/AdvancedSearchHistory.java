package com.ezzie.enoch.studentadvancedsearch;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class AdvancedSearchHistory extends LoggedInUserTest {

	private String parentWindow = null;
	private String history = "History";
	private String updateStudent = "update_student_previous_detail";
	private String previousInst = "student_previous_details_institution";

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
	public void instituitionNameEmpty() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(history);
		alphabetsEmpty(previousInst);
		findElementById(updateStudent);
		errorsProcessingRequest();
	}
	
	@Test
	public void instituitionNameAlphabets() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(history);
		alphabetsMinLength(previousInst);
		findElementById(updateStudent);
		previousSuccessfullyUpdated();
	}
	

	@Test
	public void instituitionNameAlphanumerics() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(history);
		alphanumericsMinLength(previousInst);
		findElementById(updateStudent);
		verifyCharsInstitution();
	}
	

	@Test
	public void instituitionNameSpecialchars() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(history);
		specialCharMinLength(previousInst);
		findElementById(updateStudent);
		verifyCharsInstitution();
	}
	

	@Test
	public void instituitionNameMaxLength() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(history);
		alphabetMaxLength(previousInst);
		findElementById(updateStudent);
		verifyMaxLength();
	}
}
