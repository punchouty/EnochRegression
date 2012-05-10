package com.ezzie.enoch.employee;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.ezzie.enoch.infrastructure.LoggedInUserTest;
import com.ezzie.enoch.infrastructure.SeleniumBaseTest.ReadCSV;

public class EmployeeWizardStep4 extends LoggedInUserTest {

	private String parentWindow = null;
	private String nextButton = "next_button";
	private String employeeMobilePhone = "employee_mobile_phone";
	private String fileName = "C:/Users/VHANDA/Desktop/data.csv";
	private String employeeEmail = "employee_email";
	private String employeeHomeAddress = "employee_home_address_line1";
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
	public void mobileNoEmpty() throws Exception {
		switchToEmployeeWizard4();
		findElementById(nextButton);
		verify = rc.getValue("TenDigitMobile", "Message", fileName);
		verifyText(verify);
	}

	@Test
	public void mobileNoNumerics() throws Exception {
		switchToEmployeeWizard4();
		numberMaxLength(employeeMobilePhone);
		findElementById(nextButton);
		verify = rc.getValue("EnterValidEmail", "Message", fileName);
		verifyText(verify);
	}

	@Test
	public void mobileNoAlphabets() throws Exception {
		switchToEmployeeWizard4();
		alphabetsMinLength(employeeMobilePhone);
		findElementById(nextButton);
		verify = rc.getValue("TenDigitMobile", "Message", fileName);
		verifyText(verify);
	}

	@Test
	public void mobileNoAlphanumerics() throws Exception {
		switchToEmployeeWizard4();
		alphanumericsMinLength(employeeMobilePhone);
		findElementById(nextButton);
		verify = rc.getValue("TenDigitMobile", "Message", fileName);
		verifyText(verify);
	}

	@Test
	public void mobileNoSpecialChars() throws Exception {
		switchToEmployeeWizard4();
		specialCharMinLength(employeeMobilePhone);
		findElementById(nextButton);
		verify = rc.getValue("VerifySpecialChars", "Message", fileName);
		verifyText(verify);
	}

	@Test
	public void mobileNoMaxLength() throws Exception {
		switchToEmployeeWizard4();
		number11Length(employeeMobilePhone);
		findElementById(nextButton);
		verify = rc.getValue("TenDigitMobile", "Message", fileName);
		verifyText(verify);
	}
	
	@Test
	public void emailOnlyAlphabets() throws Exception {
		switchToEmployeeWizard4();
		numberMaxLength(employeeMobilePhone);
		alphabetsMinLength(employeeEmail);
		findElementById(nextButton);
		verify = rc.getValue("EmailValidFormat", "Message", fileName);
		verifyText(verify);
	}
	
	@Test
	public void emailDesiredFormat() throws Exception {
		switchToEmployeeWizard4();
		numberMaxLength(employeeMobilePhone);
		driver.findElement(By.id("employee_email")).clear();
		driver.findElement(By.id("employee_email")).sendKeys("abc@gmail.com");
		findElementById(nextButton);
		verify = rc.getValue("HomeAddressLine1", "Message", fileName);
		verifyText(verify);
	}
	
	@Test
	public void homeAddressLine1Alphabets() throws Exception {
		switchToEmployeeWizard4();
		numberMaxLength(employeeMobilePhone);
		driver.findElement(By.id("employee_email")).clear();
		driver.findElement(By.id("employee_email")).sendKeys("abc@gmail.com");
		alphabetsMinLength(employeeHomeAddress);
		findElementById(nextButton);
		verify = rc.getValue("EmployeeHomeCity", "Message", fileName);
		verifyText(verify);
	}
	
	@Test
	public void homeAddressLine1Alphanuumerics() throws Exception {
		switchToEmployeeWizard4();
		numberMaxLength(employeeMobilePhone);
		driver.findElement(By.id("employee_email")).clear();
		driver.findElement(By.id("employee_email")).sendKeys("abc@gmail.com");
		alphanumericsMinLength(employeeHomeAddress);
		findElementById(nextButton);
		verify = rc.getValue("EmployeeHomeCity", "Message", fileName);
		verifyText(verify);
	}
	
	@Test
	public void homeAddressLine1SpecialChars() throws Exception {
		switchToEmployeeWizard4();
		numberMaxLength(employeeMobilePhone);
		driver.findElement(By.id("employee_email")).clear();
		driver.findElement(By.id("employee_email")).sendKeys("abc@gmail.com");
		specialCharMinLength(employeeHomeAddress);
		findElementById(nextButton);
		verify = rc.getValue("VerifySpecialChars", "Message", fileName);
		verifyText(verify);
	}
	
	@Test
	public void homeAddressLine1MaxLength() throws Exception {
		switchToEmployeeWizard4();
		numberMaxLength(employeeMobilePhone);
		driver.findElement(By.id("employee_email")).clear();
		driver.findElement(By.id("employee_email")).sendKeys("abc@gmail.com");
		alphabetMaxLength(employeeHomeAddress);
		findElementById(nextButton);
		Thread.sleep(10000);
		verify = rc.getValue("HomeAddressMaxLength", "Message", fileName);
		verifyText(verify);
	}
	
}
