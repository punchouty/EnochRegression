package com.ezzie.enoch.student;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class StudentWizardStep5 extends LoggedInUserTest {

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
	public void firstNameEmpty() throws Exception {
		switchTOStudentWizard2();
		switchToStudentWizard3();
		switchToStudentWizard5BlankImage();
		driver.findElement(By.id("guardian_first_name")).clear();
		driver.findElement(By.id("guardian_first_name")).sendKeys(createString(0));
		Thread.sleep(3000);
		driver.findElement(By.id("save_guardian")).click();
		String message = driver.findElement(
				By.cssSelector("ul.message.warning  > li")).getText();
		try {
			assertEquals("Please enter first name".toUpperCase(), driver
					.findElement(By.cssSelector("ul.message.warning  > li"))
					.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void firstNameAlphabets() throws Exception {
		switchTOStudentWizard2();
		switchToStudentWizard3();
		switchToStudentWizard5BlankImage();
		driver.findElement(By.id("guardian_first_name")).clear();
		driver.findElement(By.id("guardian_first_name")).sendKeys(createString(2));
		Thread.sleep(3000);
		driver.findElement(By.id("save_guardian")).click();
	}

	@Test
	public void firstNameAlphanumerics() throws Exception {
		switchTOStudentWizard2();
		switchToStudentWizard3();
		switchToStudentWizard5BlankImage();
		driver.findElement(By.id("guardian_first_name")).clear();
		driver.findElement(By.id("guardian_first_name")).sendKeys(createAlphaNum(2));
		Thread.sleep(3000);
		driver.findElement(By.id("save_guardian")).click();
		try {
			assertEquals(
					"Please enter characters for guardian first name"
							.toUpperCase(),
					driver.findElement(
							By.cssSelector("ul.message.warning > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void firstNameSpecialCharacters() throws Exception {
		switchTOStudentWizard2();
		switchToStudentWizard3();
		switchToStudentWizard5BlankImage();
		driver.findElement(By.id("guardian_first_name")).clear();
		driver.findElement(By.id("guardian_first_name")).sendKeys(createSpecialChars(2));
		Thread.sleep(3000);
		driver.findElement(By.id("save_guardian")).click();
		try {
			assertEquals(
					"Please enter characters for guardian first name"
							.toUpperCase(),
					driver.findElement(
							By.cssSelector("ul.message.warning > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void firstNameMaxLength() throws Exception {
		switchTOStudentWizard2();
		switchToStudentWizard3();
		switchToStudentWizard5BlankImage();
		driver.findElement(By.id("guardian_first_name")).clear();
		driver.findElement(By.id("guardian_first_name")).sendKeys(
				createString(51));
		driver.findElement(By.id("save_guardian")).click();
		Thread.sleep(3000);
		try {
			assertEquals(
					"You can not enter more than 50 character in field"
							.toUpperCase(),
					driver.findElement(
							By.cssSelector("ul.message.warning > li"))
							.getText());
			Thread.sleep(3000);
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void firstNameEmptyWithImage() throws Exception {
		switchTOStudentWizard2();
		switchToStudentWizard3();
		switchToStudentWizard5WithImage();
		driver.findElement(By.id("guardian_first_name")).clear();
		driver.findElement(By.id("guardian_first_name")).sendKeys(createString(0));
		Thread.sleep(3000);
		driver.findElement(By.id("save_guardian")).click();
		String message = driver.findElement(
				By.cssSelector("ul.message.warning  > li")).getText();
		try {
			assertEquals("Please enter first name".toUpperCase(), driver
					.findElement(By.cssSelector("ul.message.warning  > li"))
					.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void firstNameAlphabetsWithImage() throws Exception {
		switchTOStudentWizard2();
		switchToStudentWizard3();
		switchToStudentWizard5WithImage();
		driver.findElement(By.id("guardian_first_name")).clear();
		driver.findElement(By.id("guardian_first_name")).sendKeys(createString(2));
		Thread.sleep(3000);
		driver.findElement(By.id("save_guardian")).click();
	}

	@Test
	public void realtionshipDropdown() throws Exception {
		switchTOStudentWizard2();
		switchToStudentWizard3();
		switchToStudentWizard5BlankImage();
		driver.findElement(By.id("guardian_first_name")).clear();
		driver.findElement(By.id("guardian_first_name")).sendKeys(createString(2));
		Thread.sleep(2000);
		Select select = new Select(driver.findElement(By.id("relation")));
		select.selectByVisibleText("Mother");
		Thread.sleep(2000);
	}

	@Test
	public void realtionshipDropdownWithImage() throws Exception {
		switchTOStudentWizard2();
		switchToStudentWizard3();
		switchToStudentWizard5WithImage();
		driver.findElement(By.id("guardian_first_name")).clear();
		driver.findElement(By.id("guardian_first_name")).sendKeys(createString(2));
		Thread.sleep(2000);
		Select select = new Select(driver.findElement(By.id("relation")));
		select.selectByVisibleText("Mother");
		Thread.sleep(2000);
	}
}
