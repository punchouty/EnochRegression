package com.ezzie.enoch.studentadvancedsearch;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class AdvancedSearchContacts extends LoggedInUserTest {

	private String parentWindow = null;
	private String studentAddress= "student_address_line1";
	private String studentCity = "student_city";
	private String studentPhone = "student_phone2";
	private String contact = "Contacts";
	private String update = "update_contact";

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
	public void contactsUpdateLine1Empty() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(contact);
		alphabetsEmpty(studentAddress);
		findElementById(update);
		verifyEnterAddressLine();
	}
	

	@Test
	public void contactsUpdateLine1Aplhanumerics() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(contact);
		alphabetsMinLength(studentAddress);
		findElementById(update);
		verifySuccessfullyUpdated();
	}
	

	@Test
	public void contactsUpdateLine1SpecialChars() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(contact);
		specialCharMinLength(studentAddress);
		findElementById(update);
		verifySpecialChars();
	}
	
	@Test
	public void contactsUpdateLine1MaxLength() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(contact);
		alphabetMaxLength(studentAddress);
		findElementById(update);
		verifyMaxLength();
	}
	
	@Test
	public void contactsUpdateCityEmpty() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(contact);
		alphabetsEmpty(studentCity);
		findElementById(update);
		verifyEnterCity();
	}
	
	@Test
	public void contactsUpdateCityAlphabets() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(contact);
		alphabetsMinLength(studentAddress);
		alphabetsMinLength(studentCity);
		findElementById(update);
		verifySuccessfullyUpdated();
	}
	
	@Test
	public void contactsUpdateCityAlphanumerics() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(contact);
		alphanumericsMinLength(studentAddress);
		alphanumericsMinLength(studentCity);
		findElementById(update);
		verifyEnterCharactersCity();
	}
	
	@Test
	public void contactsUpdateCitySpecialChars() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(contact);
		alphanumericsMinLength(studentAddress);
		specialCharMinLength(studentCity);
		findElementById(update);
		verifyEnterCharactersCity();
	}
	
	@Test
	public void contactsUpdateMobileEmpty() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(contact);
		numberMinLength(studentPhone);
		findElementById(update);
		verifyEnterMobileNo();
	}
	
	@Test
	public void contactsUpdateMobileNumerics() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(contact);
		numberMaxLength(studentPhone);
		findElementById(update);
		verifySuccessfullyUpdated();
	}
	

	@Test
	public void contactsUpdateMobileAlphabets() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(contact);
		alphabetsMinLength(studentPhone);
		verifyEnterNumericValue();
	}
	
	@Test
	public void contactsUpdateMobileAlphanumerics() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(contact);
		alphanumericsMinLength(studentPhone);
		verifyEnterNumericValue();
	}
	
	@Test
	public void contactsUpdateMobileSpecialChars() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(contact);
		specialCharMinLength(studentPhone);
		verifyEnterNumericValue();
	}
	
	@Test
	public void contactsUpdateMobileMaxLength() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(contact);
		number11Length(studentPhone);
		findElementById(update);
		mobileNoTenDigit();
	}
}