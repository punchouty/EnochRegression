package com.ezzie.enoch.employee;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class EmployeeWizardStep3 extends LoggedInUserTest {

	private String parentWindow = null;
	private String nextButton = "next_button";

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
	public void allFieldsEmpty() throws Exception {
		switchToEmployeeWizard3();
		findElementById(nextButton);
	}
}
