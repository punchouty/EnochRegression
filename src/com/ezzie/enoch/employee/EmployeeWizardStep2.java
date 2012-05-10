package com.ezzie.enoch.employee;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.ezzie.enoch.infrastructure.LoggedInUserTest;
import com.ezzie.enoch.infrastructure.SeleniumBaseTest.ReadCSV;

public class EmployeeWizardStep2 extends LoggedInUserTest {

	private String parentWindow = null;
	private String nextButton = "next_button";
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
	public void departmentEmpty() throws Exception {
		switchToEmployeeWizard2();
		findElementById(nextButton);
		verify = rc.getValue("DepartmentAssigned", "Message", fileName);
		verifyText(verify);
	}

	@Test
	public void categoryEmpty() throws Exception {
		switchToEmployeeWizard2();
		new Select(driver.findElement(By.id("employee_employee_department_id")))
				.selectByVisibleText("Mathematics");
		findElementById(nextButton);
		verify = rc.getValue("CategoryAssigned", "Message", fileName);
		verifyText(verify);
	}

	@Test
	public void positionEmpty() throws Exception {
		switchToEmployeeWizard2();
		new Select(driver.findElement(By.id("employee_employee_department_id")))
				.selectByVisibleText("Mathematics");
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("employee_employee_category_id")))
				.selectByVisibleText("Teaching");
		findElementById(nextButton);
		verify = rc.getValue("PositionAssigned", "Message", fileName);
		verifyText(verify);
	}

}
