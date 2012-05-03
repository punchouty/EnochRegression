package com.ezzie.enoch.student;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class StudentWizardStep1 extends LoggedInUserTest {

	private String parentWindow = null;
	private String studentFirstName = "student_first_name";
	private String studentLastName = "student_last_name";
	private String firstChild = "#new_student > img.first-child";
	private String nextButton = "next_button";
	private String admissionNo = "student_admission_no";
	
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
	public void admissionNoEmpty() throws Exception {
		findElementCSSSelector(firstChild);
		driver.switchTo().window("Student Admission");
		alphabetsEmpty(admissionNo);
		findElementById(nextButton);
		try {
			assertEquals(
					"PLEASE ENTER FIRST NAME",
					driver.findElement(
							By.cssSelector("ul.message.warning > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void firstNameEmpty() throws Exception {
		findElementCSSSelector(firstChild);
		driver.switchTo().window("Student Admission");
		alphabetsEmpty(studentFirstName);
		findElementById(nextButton);
		try {
			assertEquals(
					"PLEASE ENTER FIRST NAME",
					driver.findElement(
							By.cssSelector("ul.message.warning > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void firstNameAlphabets() throws Exception {
		findElementCSSSelector(firstChild);
		driver.switchTo().window("Student Admission");
		alphabetsMinLength(studentFirstName);
		findElementById(nextButton);
		try {
			assertEquals(
					"PLEASE ENTER LAST NAME",
					driver.findElement(
							By.cssSelector("ul.message.warning > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void firstNameAlphanumerics() throws Exception {
		findElementCSSSelector(firstChild);
		driver.switchTo().window("Student Admission");
		alphanumericsMinLength(studentFirstName);
		findElementById(nextButton);
		try {
			assertEquals("PLEASE ENTER ONLY CHARACTERS FOR FIRST NAME", driver
					.findElement(By.cssSelector("ul.message.warning > li"))
					.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void firstNameSpecialCharacters() throws Exception {
		findElementCSSSelector(firstChild);
		driver.switchTo().window("Student Admission");
		specialCharMinLength(studentFirstName);
		findElementById(nextButton);
		try {
			assertEquals(
					"Special Charcter are not allowed in First name and Admission No"
							.toUpperCase(),
					driver.findElement(
							By.cssSelector("ul.message.warning > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void firstNameFieldMaxLength() throws Exception {
		findElementCSSSelector(firstChild);
		driver.switchTo().window("Student Admission");
		alphabetMaxLength(studentFirstName);
		alphabetsMinLength(studentLastName);
		Select course = new Select(driver.findElement(By
				.id("adv_search_course_id")));
		course.selectByVisibleText("Nursery");
		Thread.sleep(2000);
		Select batch = new Select(driver.findElement(By.id("student_batch_id")));
		batch.selectByVisibleText("Nursery - A");
		Thread.sleep(2000);
		findElementById(nextButton);
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
	public void lastNameEmpty() throws Exception {
		findElementCSSSelector(firstChild);
		driver.switchTo().window("Student Admission");
		alphabetsMinLength(studentFirstName);
		alphabetsEmpty(studentLastName);
		findElementById(nextButton);
		try {
			assertEquals("Please Enter Last Name".toUpperCase(), driver
					.findElement(By.cssSelector("ul.message.warning > li"))
					.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void lastNameAlphabets() throws Exception {
		findElementCSSSelector(firstChild);
		driver.switchTo().window("Student Admission");
		alphabetsMinLength(studentFirstName);
		alphabetsMinLength(studentLastName);
		findElementById(nextButton);
		try {
			assertEquals(
					"PLEASE SELECT BATCH",
					driver.findElement(
							By.cssSelector("ul.message.warning > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void lastNameAlphanumerics() throws Exception {
		findElementCSSSelector(firstChild);
		driver.switchTo().window("Student Admission");
		alphabetsMinLength(studentFirstName);
		alphanumericsMinLength(studentLastName);
		findElementById(nextButton);
		try {
			assertEquals("PLEASE ENTER ONLY CHARACTERS FOR LAST NAME", driver
					.findElement(By.cssSelector("ul.message.warning > li"))
					.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void lastNameSpecialCharacters() throws Exception {
		findElementCSSSelector(firstChild);
		driver.switchTo().window("Student Admission");
		alphabetsMinLength(studentFirstName);
		specialCharMinLength(studentLastName);
		findElementById(nextButton);
		try {
			assertEquals(
					"Please enter only characters for last name".toUpperCase(),
					driver.findElement(
							By.cssSelector("ul.message.warning > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void lastNameFieldMaxLength() throws Exception {
		findElementCSSSelector(firstChild);
		driver.switchTo().window("Student Admission");
		alphabetsMinLength(studentFirstName);
		alphabetMaxLength(studentLastName);
		Select course = new Select(driver.findElement(By
				.id("adv_search_course_id")));
		course.selectByVisibleText("Nursery");
		Thread.sleep(2000);
		Select batch = new Select(driver.findElement(By.id("student_batch_id")));
		batch.selectByVisibleText("Nursery - A");
		Thread.sleep(2000);
		findElementById(nextButton);
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
}
