package com.ezzie.enoch.student;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.ezzie.enoch.infrastructure.LoggedInUserTest;
import com.ezzie.enoch.infrastructure.SeleniumBaseTest.ReadCSV;

public class StudentWizardStep3 extends LoggedInUserTest {

	private String parentWindow = null;
	private String nextButton = "wizard_next_button";
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
		driver.findElement(By.id("Cancel_Student_wizard")).click();
		driver.switchTo().window(parentWindow);
		super.tearDown();
	}

	
	@Test
	public void testBlankImage() throws Exception {
		switchTOStudentWizard2();
		switchToStudentWizard3();
		findElementById(nextButton);
		try {
			assertTrue(isElementPresent(By.cssSelector("span.status-ok")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		verify = rc.getValue("StudentSuccessfullyCreated", "Message", fileName);
		verifyText(verify);
	}

	@Test
	public void testCropImage() throws Exception {
		switchTOStudentWizard2();
		switchToStudentWizard3();
		driver.findElement(By.id("upload_image")).sendKeys(
				"C:\\Users\\Public\\Pictures\\Sample Pictures\\andesk.png");
		driver.findElement(By.id("wizard_next_button")).click();
		verifyStudentSuccessfullyUpdated();
		try {
			assertTrue(isElementPresent(By.id("previews")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		findElementById(nextButton);
		verify = rc.getValue("SuccessfullyUpdatedUser", "Message", fileName);
		verifyText(verify);
	}
}
