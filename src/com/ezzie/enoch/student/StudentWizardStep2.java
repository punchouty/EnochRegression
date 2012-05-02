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
		driver.findElement(By.id("student_address_line1")).clear();
		driver.findElement(By.id("student_address_line1")).sendKeys(createString(2));
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
		driver.findElement(By.id("student_address_line1")).clear();
		driver.findElement(By.id("student_address_line1")).sendKeys(createAlphaNum(2));
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
		driver.findElement(By.id("student_address_line1")).clear();
		driver.findElement(By.id("student_address_line1")).sendKeys(createSpecialChars(2));
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
		driver.findElement(By.id("student_address_line1")).clear();
		driver.findElement(By.id("student_address_line1")).sendKeys(
				createString(51));
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
		driver.findElement(By.id("student_address_line1")).clear();
		driver.findElement(By.id("student_address_line1")).sendKeys(createString(2));
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
		driver.findElement(By.id("student_address_line1")).clear();
		driver.findElement(By.id("student_address_line1")).sendKeys(createString(2));
		driver.findElement(By.id("student_city")).clear();
		driver.findElement(By.id("student_city")).sendKeys(createString(2));
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
		driver.findElement(By.id("student_address_line1")).clear();
		driver.findElement(By.id("student_address_line1")).sendKeys(createString(2));
		driver.findElement(By.id("student_city")).clear();
		driver.findElement(By.id("student_city")).sendKeys(createAlphaNum(2));
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
		driver.findElement(By.id("student_address_line1")).clear();
		driver.findElement(By.id("student_address_line1")).sendKeys(createString(2));
		driver.findElement(By.id("student_city")).clear();
		driver.findElement(By.id("student_city")).sendKeys(createSpecialChars(2));
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
		driver.findElement(By.id("student_address_line1")).clear();
		driver.findElement(By.id("student_address_line1")).sendKeys(
				createString(2));
		driver.findElement(By.id("student_city")).clear();
		driver.findElement(By.id("student_city")).sendKeys(createString(51));
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
		driver.findElement(By.id("student_address_line1")).clear();
		driver.findElement(By.id("student_address_line1")).sendKeys(createString(2));
		driver.findElement(By.id("student_city")).clear();
		driver.findElement(By.id("student_city")).sendKeys(createString(2));
		driver.findElement(By.id("student_phone2")).clear();
		driver.findElement(By.id("student_phone2")).sendKeys(createNumber(0));
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
		driver.findElement(By.id("student_address_line1")).clear();
		driver.findElement(By.id("student_address_line1")).sendKeys(createString(2));
		driver.findElement(By.id("student_city")).clear();
		driver.findElement(By.id("student_city")).sendKeys(createString(2));
		driver.findElement(By.id("student_phone2")).clear();
		driver.findElement(By.id("student_phone2")).sendKeys(createNumber(10));
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
		driver.findElement(By.id("student_address_line1")).clear();
		driver.findElement(By.id("student_address_line1")).sendKeys(createString(2));
		driver.findElement(By.id("student_city")).clear();
		driver.findElement(By.id("student_city")).sendKeys(createString(2));
		driver.findElement(By.id("student_phone2")).clear();
		driver.findElement(By.id("student_phone2")).sendKeys(createString(2));
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
		driver.findElement(By.id("student_address_line1")).clear();
		driver.findElement(By.id("student_address_line1")).sendKeys(createString(2));
		driver.findElement(By.id("student_city")).clear();
		driver.findElement(By.id("student_city")).sendKeys(createString(2));
		driver.findElement(By.id("student_phone2")).clear();
		driver.findElement(By.id("student_phone2")).sendKeys(createAlphaNum(2));
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
		driver.findElement(By.id("student_address_line1")).clear();
		driver.findElement(By.id("student_address_line1")).sendKeys(createString(2));
		driver.findElement(By.id("student_city")).clear();
		driver.findElement(By.id("student_city")).sendKeys(createString(2));
		driver.findElement(By.id("student_phone2")).clear();
		driver.findElement(By.id("student_phone2")).sendKeys(createSpecialChars(2));
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
		driver.findElement(By.id("student_address_line1")).clear();
		driver.findElement(By.id("student_address_line1")).sendKeys(createString(2));
		driver.findElement(By.id("student_city")).clear();
		driver.findElement(By.id("student_city")).sendKeys(createString(2));
		driver.findElement(By.id("student_phone2")).clear();
		driver.findElement(By.id("student_phone2")).sendKeys(createNumber(11));
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
