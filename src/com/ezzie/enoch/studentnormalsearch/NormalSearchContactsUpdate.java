package com.ezzie.enoch.studentnormalsearch;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class NormalSearchContactsUpdate extends LoggedInUserTest {

	private String parentWindow = null;
	private String studentAddress= "student_address_line1";
	private String studentCity = "student_city";
	private String studentPhone = "student_phone2";

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
		switchToStudentUpdateUnderStudentSearch();
		driver.findElement(By.linkText("Contacts")).click();
		alphabetsEmpty(studentAddress);
		driver.findElement(By.id("update_contact")).click();
		try {
			assertEquals("Please enter Address Line 1".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	

	@Test
	public void contactsUpdateLine1Aplhanumerics() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		driver.findElement(By.linkText("Contacts")).click();
		alphabetsMinLength(studentAddress);
		driver.findElement(By.id("update_contact")).click();
		try {
			assertEquals("Amelia Jackson is sucessfully updated.".toUpperCase(), driver.findElement(By.cssSelector("ul.message.success > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	

	@Test
	public void contactsUpdateLine1SpecialChars() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		driver.findElement(By.linkText("Contacts")).click();
		specialCharMinLength(studentAddress);
		driver.findElement(By.id("update_contact")).click();
		try {
			assertEquals("The text field has special characters. These are not allowed.".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void contactsUpdateLine1MaxLength() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		driver.findElement(By.linkText("Contacts")).click();
		alphabetMaxLength(studentAddress);
		driver.findElement(By.id("update_contact")).click();
		try {
			assertEquals("You can not enter more than 50 character in field".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void contactsUpdateCityEmpty() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		driver.findElement(By.linkText("Contacts")).click();
		alphabetsEmpty(studentCity);
		driver.findElement(By.id("update_contact")).click();
		try {
			assertEquals("Please enter City".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void contactsUpdateCityAlphabets() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		driver.findElement(By.linkText("Contacts")).click();
		alphabetsMinLength(studentAddress);
		alphabetsMinLength(studentCity);
		driver.findElement(By.id("update_contact")).click();
		try {
			assertEquals("Amelia Jackson is sucessfully updated.".toUpperCase(), driver.findElement(By.cssSelector("ul.message.success > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void contactsUpdateCityAlphanumerics() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		driver.findElement(By.linkText("Contacts")).click();
		alphanumericsMinLength(studentAddress);
		alphanumericsMinLength(studentCity);
		driver.findElement(By.id("update_contact")).click();
		try {
			assertEquals("Please enter characters for City".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void contactsUpdateCitySpecialChars() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		driver.findElement(By.linkText("Contacts")).click();
		alphanumericsMinLength(studentAddress);
		specialCharMinLength(studentCity);
		driver.findElement(By.id("update_contact")).click();
		try {
			assertEquals("Please enter characters for City".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void contactsUpdateMobileEmpty() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		driver.findElement(By.linkText("Contacts")).click();
		numberMinLength(studentPhone);
		driver.findElement(By.id("update_contact")).click();
		try {
			assertEquals("Please enter Mobile Number".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void contactsUpdateMobileNumerics() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		driver.findElement(By.linkText("Contacts")).click();
		numberMaxLength(studentPhone);
		driver.findElement(By.id("update_contact")).click();
		try {
			assertEquals("Amelia Jackson is sucessfully updated.".toUpperCase(), driver.findElement(By.cssSelector("ul.message.success > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	

	@Test
	public void contactsUpdateMobileAlphabets() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		driver.findElement(By.linkText("Contacts")).click();
		alphabetsMinLength(studentPhone);
		try {
			assertEquals("Please enter a numeric value".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void contactsUpdateMobileAlphanumerics() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		driver.findElement(By.linkText("Contacts")).click();
		alphanumericsMinLength(studentPhone);
		try {
			assertEquals("Please enter a numeric value".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void contactsUpdateMobileSpecialChars() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		driver.findElement(By.linkText("Contacts")).click();
		specialCharMinLength(studentPhone);
		try {
			assertEquals("Please enter a numeric value".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void contactsUpdateMobileMaxLength() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		driver.findElement(By.linkText("Contacts")).click();
		number11Length(studentPhone);
		driver.findElement(By.id("update_contact")).click();
		try {
			assertEquals("Mobile Number must be 10 Digit long".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
}
