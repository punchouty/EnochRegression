package com.ezzie.enoch.employee;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.ezzie.enoch.infrastructure.LoggedInUserTest;
import com.ezzie.enoch.infrastructure.SeleniumBaseTest.ReadCSV;

public class EmployeeWizardStep1 extends LoggedInUserTest {

	private String parentWindow = null;
	private String newEmployee = "#new_employee > img.first-child";
	private String employeeNumber = "employee_number";
	private String nextButton = "next_button";
	private String firstName = "first_name";
	private String lastName = "employee_last_name";
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
		driver.findElement(By.id("Cancel_Employee_wizard")).click();
		driver.switchTo().window(parentWindow);
		super.tearDown();
	}
	
	@Test
	public void employeeNoEmpty() throws Exception {
		findElementCSSSelector(newEmployee);
		driver.switchTo().window("mywindow");
		alphabetsEmpty(employeeNumber);
		findElementById(nextButton);
		verify = rc.getValue("ValidEmployeeNumber", "Message", fileName);
		verifyText(verify);
	}
	
	@Test
	public void firstNameEmpty() throws Exception {
		findElementCSSSelector(newEmployee);
		driver.switchTo().window("mywindow");
		Thread.sleep(500);
		findElementById(nextButton);
		verify = rc.getValue("FirstName", "Message", fileName);
		verifyText(verify);
	}
	
	@Test
	public void firstNameAlphabets() throws Exception {
		findElementCSSSelector(newEmployee);
		driver.switchTo().window("mywindow");
		alphabetsMinLength(firstName);
		Thread.sleep(500);
		findElementById(nextButton);
		verify = rc.getValue("LastName", "Message", fileName);
		verifyText(verify);
	}
	
	@Test
	public void firstNameAlphanumerics() throws Exception {
		findElementCSSSelector(newEmployee);
		driver.switchTo().window("mywindow");
		alphanumericsMinLength(firstName);
		alphabetsMinLength(lastName);
		findElementById(nextButton);
		verify = rc.getValue("CharsFirstName", "Message", fileName);
		verifyText(verify);
	}
	
	@Test
	public void firstNameSpecialChars() throws Exception {
		findElementCSSSelector(newEmployee);
		driver.switchTo().window("mywindow");
		specialCharMinLength(firstName);
		alphabetsMinLength(lastName);
		findElementById(nextButton);
		verify = rc.getValue("VerifySpecialChars", "Message", fileName);
		verifyText(verify);
	}
	
	@Test
	public void firstNameMaxLength() throws Exception {
		findElementCSSSelector(newEmployee);
		driver.switchTo().window("mywindow");
		alphabetMaxLength(firstName);
		findElementById(nextButton);
		Thread.sleep(5000);
		verify = rc.getValue("MaxLengthFirstName", "Message", fileName);
		verifyText(verify);
	}
	

	@Test
	public void lastNameEmpty() throws Exception {
		findElementCSSSelector(newEmployee);
		driver.switchTo().window("mywindow");
		alphabetsMinLength(firstName);
		Thread.sleep(500);
		findElementById(nextButton);
		verify = rc.getValue("LastName", "Message", fileName);
		verifyText(verify);
	}
	
	@Test
	public void lastNameAlphabets() throws Exception {
		findElementCSSSelector(newEmployee);
		driver.switchTo().window("mywindow");
		alphabetsMinLength(firstName);
		alphabetsMinLength(lastName);
		Thread.sleep(500);
		findElementById(nextButton);
	}
	
	@Test
	public void lastNameAlphanumerics() throws Exception {
		findElementCSSSelector(newEmployee);
		driver.switchTo().window("mywindow");
		alphabetsMinLength(firstName);
		alphanumericsMinLength(lastName);
		findElementById(nextButton);
		verify = rc.getValue("CharsLastName", "Message", fileName);
		verifyText(verify);
	}
	

	@Test
	public void lastNameSpecialChars() throws Exception {
		driver.switchTo().window("mywindow");
		alphabetsMinLength(firstName);
		specialCharMinLength(lastName);
		findElementById(nextButton);
		verify = rc.getValue("VerifySpecialChars", "Message", fileName);
		verifyText(verify);
	}
	
	@Test
	public void lastNameMaxLength() throws Exception {
		findElementCSSSelector(newEmployee);
		driver.switchTo().window("mywindow");
		alphabetsMinLength(firstName);
		alphabetMaxLength(lastName);
		findElementById(nextButton);
		verify = rc.getValue("MaxLengthLastName", "Message", fileName);
		verifyText(verify);
	}
}
