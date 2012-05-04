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
	private String guardianFirstName = "guardian_first_name";
	private String saveGuardian = "save_guardian";

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
		findElementById(saveGuardian);
		Thread.sleep(500);
		verifyEnterFirstName();
	}

	@Test
	public void firstNameAlphabets() throws Exception {
		switchTOStudentWizard2();
		switchToStudentWizard3();
		switchToStudentWizard5BlankImage();
		alphabetsMinLength(guardianFirstName);
		Thread.sleep(500);
		findElementById(saveGuardian);
	}

	@Test
	public void firstNameAlphanumerics() throws Exception {
		switchTOStudentWizard2();
		switchToStudentWizard3();
		switchToStudentWizard5BlankImage();
		alphanumericsMinLength(guardianFirstName);
		Thread.sleep(5000);
		findElementById(saveGuardian);
		verifyGuardianFirstName();
	}

	@Test
	public void firstNameSpecialCharacters() throws Exception {
		switchTOStudentWizard2();
		switchToStudentWizard3();
		switchToStudentWizard5BlankImage();
		specialCharMinLength(guardianFirstName);
		Thread.sleep(500);
		findElementById(saveGuardian);
		verifyGuardianFirstName();
	}

	@Test
	public void firstNameMaxLength() throws Exception {
		switchTOStudentWizard2();
		switchToStudentWizard3();
		switchToStudentWizard5BlankImage();
		alphabetMaxLength(guardianFirstName);
		findElementById(saveGuardian);
		Thread.sleep(1000);
		verifyMaxLength();
	}

	@Test
	public void firstNameEmptyWithImage() throws Exception {
		switchTOStudentWizard2();
		switchToStudentWizard3();
		switchToStudentWizard5WithImage();
		alphabetsEmpty(guardianFirstName);
		Thread.sleep(200);
		findElementById(saveGuardian);
		Thread.sleep(1000);
		verifyEnterFirstName();
	}

	@Test
	public void firstNameAlphabetsWithImage() throws Exception {
		switchTOStudentWizard2();
		switchToStudentWizard3();
		switchToStudentWizard5WithImage();
		alphabetsMinLength(guardianFirstName);
		Thread.sleep(200);
		findElementById(saveGuardian);
	}

	@Test
	public void realtionshipDropdown() throws Exception {
		switchTOStudentWizard2();
		switchToStudentWizard3();
		switchToStudentWizard5BlankImage();
		alphabetsMinLength(guardianFirstName);
		Thread.sleep(200);
		Select select = new Select(driver.findElement(By.id("relation")));
		select.selectByVisibleText("Mother");
		Thread.sleep(200);
	}

	@Test
	public void realtionshipDropdownWithImage() throws Exception {
		switchTOStudentWizard2();
		switchToStudentWizard3();
		switchToStudentWizard5WithImage();
		alphabetsMinLength(guardianFirstName);
		Thread.sleep(1000);
		Select select = new Select(driver.findElement(By.id("relation")));
		select.selectByVisibleText("Mother");
		Thread.sleep(2000);
	}
}
