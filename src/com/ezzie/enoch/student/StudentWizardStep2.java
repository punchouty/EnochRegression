package com.ezzie.enoch.student;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.ezzie.enoch.infrastructure.LoggedInUserTest;
import com.ezzie.enoch.infrastructure.SeleniumBaseTest.ReadCSV;

public class StudentWizardStep2 extends LoggedInUserTest {

	private String parentWindow = null;
	private String studentAddress = "student_address_line1";
	private String city = "student_city";
	private String studentPhone = "student_phone2";
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
	public void addressLine1Empty() throws Exception {
		switchTOStudentWizard2();
		findElementById(nextButton);
		verify = rc.getValue("EnterAddress", "Message", fileName);
		verifyText(verify);
	}

	@Test
	public void addressLine1Alphabets() throws Exception {
		switchTOStudentWizard2();
		alphabetsMinLength(studentAddress);
		findElementById(nextButton);
		verify = rc.getValue("EnterCity", "Message", fileName);
		verifyText(verify);
	}

	@Test
	public void addressLine1Alphanumerics() throws Exception {
		switchTOStudentWizard2();
		alphanumericsMinLength(studentAddress);
		findElementById(nextButton);
		verify = rc.getValue("EnterCity", "Message", fileName);
		verifyText(verify);
	}

	@Test
	public void addressLine1SpecialCharacters() throws Exception {
		switchTOStudentWizard2();
		specialCharMinLength(studentAddress);
		findElementById(nextButton);
		verify = rc.getValue("VerifySpecialChars", "Message", fileName);
		verifyText(verify);
	}

	@Test
	public void addressLine1MaxLength() throws Exception {
		switchTOStudentWizard2();
		alphabetMaxLength(studentAddress);
		findElementById(nextButton);
		verify = rc.getValue("MaxLength", "Message", fileName);
		verifyText(verify);
	}

	@Test
	public void cityEmpty() throws Exception {
		switchTOStudentWizard2();
		alphabetsMinLength(studentAddress);
		findElementById(nextButton);
		verify = rc.getValue("EnterCity", "Message", fileName);
		verifyText(verify);
	}

	@Test
	public void cityAlphabets() throws Exception {
		switchTOStudentWizard2();
		alphabetsMinLength(studentAddress);
		alphabetsMinLength(city);
		findElementById(nextButton);
		verify = rc.getValue("MobileNoDot", "Message", fileName);
		verifyText(verify);
	}

	@Test
	public void cityAlphanumerics() throws Exception {
		switchTOStudentWizard2();
		alphabetsMinLength(studentAddress);
		alphanumericsMinLength(city);
		findElementById(nextButton);
		verify = rc.getValue("OnlyCharsCity", "Message", fileName);
		verifyText(verify);
	}

	@Test
	public void citySpecialCharacters() throws Exception {
		switchTOStudentWizard2();
		alphabetsMinLength(studentAddress);
		specialCharMinLength(city);
		findElementById(nextButton);
		verify = rc.getValue("OnlyCharsCity", "Message", fileName);
		verifyText(verify);
	}

	@Test
	public void cityMaxLength() throws Exception {
		switchTOStudentWizard2();
		alphabetsMinLength(studentAddress);
		alphabetMaxLength(city);
		findElementById(nextButton);
		verify = rc.getValue("MaxLength", "Message", fileName);
		verifyText(verify);
	}

	@Test
	public void mobileEmpty() throws Exception {
		switchTOStudentWizard2();
		alphabetsMinLength(studentAddress);
		alphabetsMinLength(city);
		numberMinLength(studentPhone);
		findElementById(nextButton);
		verify = rc.getValue("MobileNoDot", "Message", fileName);
		verifyText(verify);
	}

	@Test
	public void mobileNumerics() throws Exception {
		switchTOStudentWizard2();
		alphabetsMinLength(studentAddress);
		alphabetsMinLength(city);
		numberMaxLength(studentPhone);
		findElementById(nextButton);
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
		findElementById(nextButton);
		verify = rc.getValue("MobileNoTenDigit", "Message", fileName);
		verifyText(verify);
	}

	@Test
	public void mobileAlphanumerics() throws Exception {
		switchTOStudentWizard2();
		alphabetsMinLength(studentAddress);
		alphabetsMinLength(city);
		alphanumericsMinLength(studentPhone);
		findElementById(nextButton);
		verify = rc.getValue("MobileNoTenDigit", "Message", fileName);
		verifyText(verify);
	}

	@Test
	public void mobileSpecialCharacters() throws Exception {
		switchTOStudentWizard2();
		alphabetsMinLength(studentAddress);
		alphabetsMinLength(city);
		specialCharMinLength(studentPhone);
		findElementById(nextButton);
		verify = rc.getValue("MobileNoTenDigit", "Message", fileName);
		verifyText(verify);
	}

	@Test
	public void mobileMaxLength() throws Exception {
		switchTOStudentWizard2();
		alphabetsMinLength(studentAddress);
		alphabetsMinLength(city);
		number11Length(studentPhone);
		findElementById(nextButton);
		verify = rc.getValue("MobileNoTenDigit", "Message", fileName);
		verifyText(verify);
	}
}
