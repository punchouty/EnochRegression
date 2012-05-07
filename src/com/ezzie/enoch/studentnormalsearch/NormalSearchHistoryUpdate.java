package com.ezzie.enoch.studentnormalsearch;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.ezzie.enoch.infrastructure.LoggedInUserTest;
import com.ezzie.enoch.infrastructure.SeleniumBaseTest.ReadCSV;

public class NormalSearchHistoryUpdate extends LoggedInUserTest {

	private String parentWindow = null;
	private String history = "History";
	private String updateStudent = "update_student_previous_detail";
	private String previousInst = "student_previous_details_institution";
	private String fileName = "C:/Users/VHANDA/Desktop/data.csv";
	ReadCSV rc = new ReadCSV();
	Object verify = new Object();

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
		switchToStudentUpdateUnderStudentSearch();
		findElementLinkText(history);
		alphabetsEmpty(previousInst);
		findElementById(updateStudent);
		verify = rc.getValue("ErrorsProcessingRequest", "Message", fileName);
		verifyText(verify);
	}
	
	@Test
	public void instituitionNameAlphabets() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		findElementLinkText(history);
		alphabetsMinLength(previousInst);
		findElementById(updateStudent);
		verify = rc.getValue("PreviousSuccessfullyUpdated", "Message", fileName);
		verifyTextSuccess(verify);
	}
	
	@Test
	public void instituitionNameAlphanumerics() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		findElementLinkText(history);
		alphanumericsMinLength(previousInst);
		findElementById(updateStudent);
		verify = rc.getValue("CharsInstitution", "Message", fileName);
		verifyText(verify);
	}
	

	@Test
	public void instituitionNameSpecialchars() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		findElementLinkText(history);
		specialCharMinLength(previousInst);
		findElementById(updateStudent);
		verify = rc.getValue("CharsInstitution", "Message", fileName);
		verifyText(verify);
	}
	

	@Test
	public void instituitionNameMaxLength() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		findElementLinkText(history);
		alphabetMaxLength(previousInst);
		findElementById(updateStudent);
		verify = rc.getValue("MaxLength", "Message", fileName);
		verifyText(verify);
	}
}
