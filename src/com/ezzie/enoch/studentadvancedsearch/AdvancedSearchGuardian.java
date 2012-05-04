package com.ezzie.enoch.studentadvancedsearch;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class AdvancedSearchGuardian extends LoggedInUserTest {

	private String parentWindow = null;
	private String firstName = "first_name6";
	private String xPath = "(//input[@id='parent_detail_first_name'])[2]";
	private String guardian = "Guardian";
	private String update = "update_parents";
	private String  addGuardian= "#add-more-parents > img.first-child.last-child";
	private String xpathRelation = "(//select[@id='relation'])[2]";
	private String xpathButton = "//button[@type='button']";

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
		findElementLinkText(guardian);
		alphabetsEmpty(firstName);
		findElementById(update);
		verifyEnterFirstName();
	}
	
	@Test
	public void firstNameAlphabets() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(guardian);
		alphabetsMinLength(firstName);
		new Select(driver.findElement(By.id("relation6"))).selectByVisibleText("Father");
		Thread.sleep(500);
		findElementById(update);
		guardianSuccessfullyUpdated();
	}
	
	@Test
	public void firstNameAlphanumerics() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(guardian);
		alphanumericsMinLength(firstName);
		findElementById(update);
		verifyOnlyCharsFirstName();
	}
	
	@Test
	public void firstNameSpecialChars() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(guardian);
		specialCharMinLength(firstName);
		findElementById(update);
		verifyOnlyCharsFirstName();
	}
	
	@Test
	public void firstNameMaxLength() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(guardian);
		alphabetMaxLength(firstName);
		findElementById(update);
		verifyMaxLength();
	}


	@Test
	public void addOneMoreGuardian() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(guardian);
		findElementCSSSelector(addGuardian);
		alphabetsMinLinkText(xPath);
		new Select(driver.findElement(By.xpath("(//select[@id='relation'])[2]"))).selectByVisibleText("Mother");
}


	@Test
	public void newGuardianFirstNameEmpty() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(guardian);
		findElementCSSSelector(addGuardian);
		alphabetsEmptyLinkText(xPath);
		findElementXPath(xpathButton);
		verifyEnterFirstName();
	}
	
	@Test
	public void newGuardianFirstNameAlphanumerics() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(guardian);
		findElementCSSSelector(addGuardian);
		alphanumericsLinkText(xPath);
		findElementXPath(xpathButton);
		verifyEnterCharactersFirstName();
	}
	
	@Test
	public void newGuardianFirstNameSpecialChars() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(guardian);
		findElementCSSSelector(addGuardian);
		specialCharsLinkText(xPath);
		findElementXPath(xpathButton);
		verifyEnterCharactersFirstName();
	}
	
	@Test
	public void newGuardianFirstNameMaxLength() throws Exception {
		switchToStudentUpdateWithAdvancedSearch();
		findElementLinkText(guardian);
		findElementCSSSelector(addGuardian);
		maxLengthLinkText(xPath);
		findElementXPath(xpathButton);
		verifyMaxLength();
	}
}
