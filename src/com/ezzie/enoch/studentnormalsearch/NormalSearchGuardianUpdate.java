package com.ezzie.enoch.studentnormalsearch;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class NormalSearchGuardianUpdate extends LoggedInUserTest {

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
		switchToStudentUpdateUnderStudentSearch();
		findElementLinkText(guardian);
		alphabetsEmpty(firstName);
		findElementById(update);
		try {
			assertEquals("Please enter First name".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void firstNameAlphabets() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		findElementLinkText(guardian);
		alphabetsMinLength(firstName);
		new Select(driver.findElement(By.id("relation6"))).selectByVisibleText("Father");
		Thread.sleep(500);
		findElementById(update);
		try {
			assertEquals("Guardian is successfully Updated.".toUpperCase(), driver.findElement(By.cssSelector("ul.message.success > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void firstNameAlphanumerics() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		findElementLinkText(guardian);
		alphanumericsMinLength(firstName);
		findElementById(update);
		try {
			assertEquals("Please enter only characters for First Name".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void firstNameSpecialChars() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		findElementLinkText(guardian);
		specialCharMinLength(firstName);
		findElementById(update);
		try {
			assertEquals("Please enter only characters for First Name".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void firstNameMaxLength() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		findElementLinkText(guardian);
		alphabetMaxLength(firstName);
		findElementById(update);
		try {
			assertEquals("You can not enter more than 50 character in field".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}


	@Test
	public void addOneMoreGuardian() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		findElementLinkText(guardian);
		findElementCSSSelector(addGuardian);
		alphabetsMinLinkText(xPath);
		new Select(driver.findElement(By.xpath("(//select[@id='relation'])[2]"))).selectByVisibleText("Mother");
}


	@Test
	public void newGuardianFirstNameEmpty() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		findElementLinkText(guardian);
		findElementCSSSelector(addGuardian);
		alphabetsEmptyLinkText(xPath);
		findElementXPath(xpathButton);
		try {
			assertEquals("Please Enter First Name".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void newGuardianFirstNameAlphanumerics() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		findElementLinkText(guardian);
		findElementCSSSelector(addGuardian);
		alphanumericsLinkText(xPath);
		findElementXPath(xpathButton);
		try {
			assertEquals("Please enter characters for First name".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void newGuardianFirstNameSpecialChars() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		findElementLinkText(guardian);
		findElementCSSSelector(addGuardian);
		specialCharsLinkText(xPath);
		findElementXPath(xpathButton);
		try {
			assertEquals("Please enter characters for First name".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void newGuardianFirstNameMaxLength() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		findElementLinkText(guardian);
		findElementCSSSelector(addGuardian);
		maxLengthLinkText(xPath);
		findElementXPath(xpathButton);
		try {
			assertEquals("You can not enter more than 50 character in field".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
}
