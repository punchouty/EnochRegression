package com.ezzie.enoch.studentadvancedsearch;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.ezzie.enoch.infrastructure.LoggedInUserTest;
import com.ezzie.enoch.infrastructure.SeleniumBaseTest.ReadCSV;

public class AdvancedSearchPersonalInfo extends LoggedInUserTest {

	private String parentWindow = null;
	private String studentFirstName = "student_first_name";
	private String update = "update_student";
	private String studentLastName = "student_last_name";
	private String updateId = "student_update_id";
	private String xpathButton = "(//button[@type='button'])[2]";
	private String studentImage = "student_image";
	private String cropImage = "Crop_image";
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
	public void firstNameEmpty() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		alphabetsEmpty(studentFirstName);
		findElementById(update);
		verify = rc.getValue("FirstName", "Message", fileName);
		verifyText(verify);
	}

	@Test
	public void lastNameEmpty() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		alphabetsMinLength(studentFirstName);
		alphabetsEmpty(studentLastName);
		findElementById(update);
		verify = rc.getValue("LastName", "Message", fileName);
		verifyText(verify);
	}

	@Test
	public void firstNameAlphabets() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		alphabetsMinLength(studentFirstName);
		alphabetsMinLength(studentLastName);
		findElementById(update);
		verify = rc.getValue("SuccessfullyUpdated", "Message", fileName);
		verifyTextSuccess(verify);
	}

	@Test
	public void firstNameAlphanumerics() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		alphanumericsMinLength(studentFirstName);
		findElementById(update);
		verify = rc.getValue("CharsFirstName", "Message", fileName);
		verifyText(verify);
	}

	@Test
	public void lastNameAlphanumerics() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		alphabetsMinLength(studentFirstName);
		alphanumericsMinLength(studentLastName);
		findElementById(update);
		verify = rc.getValue("CharsLastName", "Message", fileName);
		verifyText(verify);
	}

	@Test
	public void firstNameSpecialChars() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		specialCharMinLength(studentFirstName);
		findElementById(update);
		verify = rc.getValue("VerifySpecialChars", "Message", fileName);
		verifyText(verify);
	}

	@Test
	public void lastNameSpecialChars() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		alphabetsMinLength(studentFirstName);
		specialCharMinLength(studentLastName);
		findElementById(update);
		verify = rc.getValue("VerifySpecialChars", "Message", fileName);
		verifyText(verify);
	}

	@Test
	public void firstNameMaxLength() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		alphabetMaxLength(studentFirstName);
		findElementById(update);
		verify = rc.getValue("MaxLength", "Message", fileName);
		verifyText(verify);
	}

	@Test
	public void lastNameMaxLength() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		alphabetsMinLength(studentFirstName);
		alphabetMaxLength(studentLastName);
		findElementById(update);
		verify = rc.getValue("MaxLength", "Message", fileName);
		verifyText(verify);
	}

	@Test
	public void personalInfoChangeImmediateContact() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementById(updateId);
		Thread.sleep(500);
		findElementXPath(xpathButton);
	}

	@Test
	public void personalInfoUpdateDropdown() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		Select category1 = new Select(driver.findElement(By
				.id("student_student_category_id")));
		category1.selectByVisibleText("General");
		Thread.sleep(500);
		Select category2 = new Select(driver.findElement(By
				.id("student_religion")));
		category2.selectByVisibleText("Other");
		Thread.sleep(500);
		findElementById(update);
	}

	@Test
	public void personalInfoUpdateImage() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementById(studentImage);
		driver.switchTo().window("Change Image");
		driver.findElement(By.id("student_student_photo"))
				.sendKeys(
						"C:\\Users\\Public\\Pictures\\Sample Pictures\\Android_Wallpaper_by_clondike7.png");
		driver.findElement(By.name("commit")).click();
		findElementById(cropImage);
		driver.switchTo().window(parentWindow);
		findElementById(update);
		verify = rc.getValue("SuccessfullyUpdated", "Message", fileName);
		verifyTextSuccess(verify);
	}
}
