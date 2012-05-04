package com.ezzie.enoch.studentnormalsearch;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class NormalSearchHistoryUpdate extends LoggedInUserTest {

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
		switchToStudentUpdateUnderStudentSearch();
		findElementLinkText(history);
		findElementById(updateStudent);
		try {
			assertEquals(
					"There are errors while processing your request\ninstitution : can't be blank"
							.toUpperCase(),
					driver.findElement(By.cssSelector("ul.message.error > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void instituitionNameAlphabets() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		findElementLinkText(history);
		alphabetsMinLength(previousInst);
		findElementById(updateStudent);
		try {
			assertEquals("Previous data is successfully updated!".toUpperCase(), driver.findElement(By.cssSelector("ul.message.success > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		findElementById(updateStudent);
		
	}
	

	@Test
	public void instituitionNameAlphanumerics() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		findElementLinkText(history);
		alphanumericsMinLength(previousInst);
		findElementById(updateStudent);
		try {
			assertEquals("Please enter characters for Institution".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	

	@Test
	public void instituitionNameSpecialchars() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		findElementLinkText(history);
		specialCharMinLength(previousInst);
		findElementById(updateStudent);
		try {
			assertEquals("Please enter characters for Institution".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	

	@Test
	public void instituitionNameMaxLength() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		findElementLinkText(history);
		alphabetMaxLength(previousInst);
		findElementById(updateStudent);
		try {
			assertEquals("You can not enter more than 50 character in field".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
}
