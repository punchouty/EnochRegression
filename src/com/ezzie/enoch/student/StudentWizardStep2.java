package com.ezzie.enoch.student;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class StudentWizardStep2 extends LoggedInUserTest {

	private String parentWindow = null;
	private String studentAddress = "student_address_line1";
	private String city = "student_city";
	private String studentPhone = "student_phone2";

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
	public void addressLine1Empty() throws Exception {
		switchTOStudentWizard2();
		driver.findElement(By.id("wizard_next_button")).click();
		try {
			assertEquals("Please enter Address".toUpperCase(), driver
					.findElement(By.cssSelector("ul.message.warning > li"))
					.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

	}

	@Test
	public void addressLine1Alphabets() throws Exception {
		switchTOStudentWizard2();
		alphabetsMinLength(studentAddress);
		driver.findElement(By.id("wizard_next_button")).click();
		try {
			assertEquals(
					"Please enter City".toUpperCase(),
					driver.findElement(
							By.cssSelector("ul.message.warning > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

	}

	@Test
	public void addressLine1Alphanumerics() throws Exception {
		switchTOStudentWizard2();
		alphanumericsMinLength(studentAddress);
		driver.findElement(By.id("wizard_next_button")).click();
		try {
			assertEquals(
					"Please enter City".toUpperCase(),
					driver.findElement(
							By.cssSelector("ul.message.warning > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

	}

	@Test
	public void addressLine1SpecialCharacters() throws Exception {
		switchTOStudentWizard2();
		specialCharMinLength(studentAddress);
		driver.findElement(By.id("wizard_next_button")).click();
		try {
			assertEquals(
					"The text field has special characters. These are not allowed."
							.toUpperCase(),
					driver.findElement(
							By.cssSelector("ul.message.warning > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

	}

	@Test
	public void addressLine1MaxLength() throws Exception {
		switchTOStudentWizard2();
		alphabetMaxLength(studentAddress);
		driver.findElement(By.id("wizard_next_button")).click();
		try {
			assertEquals(
					"You can not enter more than 50 character in field"
							.toUpperCase(),
					driver.findElement(
							By.cssSelector("ul.message.warning > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

	}

	@Test
	public void cityEmpty() throws Exception {
		switchTOStudentWizard2();
		alphabetsMinLength(studentAddress);
		driver.findElement(By.id("wizard_next_button")).click();
		try {
			assertEquals(
					"Please enter City".toUpperCase(),
					driver.findElement(
							By.cssSelector("ul.message.warning > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

	}

	@Test
	public void cityAlphabets() throws Exception {
		switchTOStudentWizard2();
		alphabetsMinLength(studentAddress);
		alphabetsMinLength(city);
		driver.findElement(By.id("wizard_next_button")).click();
		try {
			assertEquals("Please enter Mobile no.".toUpperCase(), driver
					.findElement(By.cssSelector("ul.message.warning > li"))
					.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

	}

	@Test
	public void cityAlphanumerics() throws Exception {
		switchTOStudentWizard2();
		alphabetsMinLength(studentAddress);
		alphanumericsMinLength(city);
		driver.findElement(By.id("wizard_next_button")).click();
		try {
			assertEquals(
					"Please enter only characters for City".toUpperCase(),
					driver.findElement(
							By.cssSelector("ul.message.warning > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

	}

	@Test
	public void citySpecialCharacters() throws Exception {
		switchTOStudentWizard2();
		alphabetsMinLength(studentAddress);
		specialCharMinLength(city);
		driver.findElement(By.id("wizard_next_button")).click();
		try {
			assertEquals(
					"Please enter only characters for City".toUpperCase(),
					driver.findElement(
							By.cssSelector("ul.message.warning > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

	}

	@Test
	public void cityMaxLength() throws Exception {
		switchTOStudentWizard2();
		alphabetsMinLength(studentAddress);
		alphabetMaxLength(city);
		driver.findElement(By.id("wizard_next_button")).click();
		try {
			assertEquals(
					"You can not enter more than 50 character in field"
							.toUpperCase(),
					driver.findElement(
							By.cssSelector("ul.message.warning > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

	}
	
	@Test
	public void mobileEmpty() throws Exception {
		switchTOStudentWizard2();
		alphabetsMinLength(studentAddress);
		alphabetsMinLength(city);
		numberMinLength(studentPhone);
		driver.findElement(By.id("wizard_next_button")).click();
		try {
			assertEquals(
					"Please enter mobile no.".toUpperCase(),
					driver.findElement(
							By.cssSelector("ul.message.warning > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void mobileNumerics() throws Exception {
		switchTOStudentWizard2();
		alphabetsMinLength(studentAddress);
		alphabetsMinLength(city);
		numberMaxLength(studentPhone);
		driver.findElement(By.id("wizard_next_button")).click();
		try {
			assertTrue(isElementPresent(By.cssSelector("span.status-ok")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void mobileAlphabets() throws Exception {
		switchTOStudentWizard2();
		alphabetsMinLength(studentAddress);
		alphabetsMinLength(city);
		alphabetsMinLength(studentPhone);
		driver.findElement(By.id("wizard_next_button")).click();
		try {
			assertEquals(
					"Mobile Number must be 10 Digit long".toUpperCase(),
					driver.findElement(
							By.cssSelector("ul.message.warning > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void mobileAlphanumerics() throws Exception {
		switchTOStudentWizard2();
		alphabetsMinLength(studentAddress);
		alphabetsMinLength(city);
		alphanumericsMinLength(studentPhone);
		driver.findElement(By.id("wizard_next_button")).click();
		try {
			assertEquals(
					"Mobile Number must be 10 Digit long".toUpperCase(),
					driver.findElement(
							By.cssSelector("ul.message.warning > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	

	@Test
	public void mobileSpecialCharacters() throws Exception {
		switchTOStudentWizard2();
		alphabetsMinLength(studentAddress);
		alphabetsMinLength(city);
		specialCharMinLength(studentPhone);
		driver.findElement(By.id("wizard_next_button")).click();
		try {
			assertEquals(
					"Mobile Number must be 10 Digit long".toUpperCase(),
					driver.findElement(
							By.cssSelector("ul.message.warning > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void mobileMaxLength() throws Exception {
		switchTOStudentWizard2();
		alphabetsMinLength(studentAddress);
		alphabetsMinLength(city);
		number11Length(studentPhone);
		driver.findElement(By.id("wizard_next_button")).click();
		try {
			assertEquals(
					"Mobile Number must be 10 Digit long".toUpperCase(),
					driver.findElement(
							By.cssSelector("ul.message.warning > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

}
