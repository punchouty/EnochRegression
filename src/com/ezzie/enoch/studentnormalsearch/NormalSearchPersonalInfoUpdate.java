package com.ezzie.enoch.studentnormalsearch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class NormalSearchPersonalInfoUpdate extends LoggedInUserTest {

	private String parentWindow = null;
	private String studentFirstName = "student_first_name";
	private String studentLastName = "student_last_name";
	private String update = "update_student";
	private String xpathButton = "(//button[@type='button'])[2]";
	private String studentImage = "student_image";
	private String updateId = "student_update_id";
	private String cropImage = "Crop_image";

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
	public void normalSearchByLinkText() throws Exception {
		driver.findElement(By.linkText("Student Search")).click();
		driver.findElement(By.id("target")).clear();
		driver.findElement(By.id("target")).sendKeys("elia");
		driver.findElement(By.linkText("Amelia")).click();
		try {
			assertEquals(
					"Course :",
					driver.findElement(
							By.xpath("//div[@id='complex_form']/div[2]/div/p/b[2]"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void normalSearchByEditIcon() throws Exception {
		driver.findElement(By.linkText("Student Search")).click();
		driver.findElement(By.id("target")).clear();
		driver.findElement(By.id("target")).sendKeys("elia");
		driver.findElement(By.cssSelector("img.with-tip")).click();
		try {
			assertTrue(isElementPresent(By.id("changeStudentName")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals(
					"Course :",
					driver.findElement(
							By.xpath("//div[@id='complex_form']/div[2]/div/p/b[2]"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void clickReportIcon() throws Exception {
		driver.findElement(By.linkText("Student Search")).click();
		driver.findElement(By.id("target")).clear();
		driver.findElement(By.id("target")).sendKeys("elia");
		try {
			assertTrue(isElementPresent(By
					.xpath("//img[@alt='Application-blog']")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.xpath("//img[@alt='Application-blog']")).click();
		try {
			assertTrue(isElementPresent(By
					.xpath("//section[@id='main_block']/div/div/h1")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals(
					"Academic",
					driver.findElement(
							By.xpath("//section[@id='main_block']/div/div/section/div/div/h1"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals(
					"Attendance",
					driver.findElement(
							By.xpath("//section[@id='main_block']/div/div/section[2]/div/div/h1"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void personalInfoFirstNameUpdateEmpty() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		alphabetsEmpty(studentFirstName);
		findElementById(update);
		try {
			assertEquals("Please enter First name".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		
	}
	
	@Test
	public void personalInfoLastNameUpdateEmpty() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		alphabetsMinLength(studentFirstName);
		alphabetsEmpty(studentLastName);
		findElementById(update);
		try {
			assertEquals("Please enter Last name".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		
	}

	@Test
	public void personalInfoNameUpdateAlphabets() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		alphabetsMinLength(studentFirstName);
		alphabetsMinLength(studentLastName);
		findElementById(update);
		try {
			assertEquals(
					"Amelia Amelia is sucessfully updated."
							.toUpperCase(),
					driver.findElement(
							By.cssSelector("ul.message.success > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void personalInfoFirstNameUpdateAlphanumerics() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		alphanumericsMinLength(studentFirstName);
		findElementById(update);
		try {
			assertEquals(
					"Please enter characters for First Name".toUpperCase(),
					driver.findElement(
							By.cssSelector("ul.message.warning > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void personalInfoLastNameUpdateAlphanumerics() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		alphabetsMinLength(studentFirstName);
		alphanumericsMinLength(studentLastName);
		findElementById(update);
		try {
			assertEquals(
					"Please enter characters for Last Name".toUpperCase(),
					driver.findElement(
							By.cssSelector("ul.message.warning > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void personalInfoFirstNameUpdateSpecialChars() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		specialCharMinLength(studentFirstName);
		findElementById(update);
		try {
			assertEquals(
					"The text field has special characters. These are not allowed."
							.toUpperCase(),
					driver.findElement(
							By.cssSelector("ul.message.warning > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void personalInfoLastNameUpdateSpecialChars() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		alphabetsMinLength(studentFirstName);
		specialCharMinLength(studentLastName);
		findElementById(update);
		try {
			assertEquals(
					"The text field has special characters. These are not allowed."
							.toUpperCase(),
					driver.findElement(
							By.cssSelector("ul.message.warning > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void personalInfoFirstNameUpdateMaxLength() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		alphabetMaxLength(studentFirstName);
		findElementById(update);
		try {
			assertEquals(
					"You can not enter more than 50 character in field"
							.toUpperCase(),
					driver.findElement(
							By.cssSelector("ul.message.warning > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void personalInfoLastNameUpdateMaxLength() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		alphabetsMinLength(studentFirstName);
		alphabetMaxLength(studentLastName);
		findElementById(update);
		try {
			assertEquals(
					"You can not enter more than 50 character in field"
							.toUpperCase(),
					driver.findElement(
							By.cssSelector("ul.message.warning > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void personalInfoChangeImmediateContact() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		findElementById(updateId);
		Thread.sleep(500);
		findElementXPath(xpathButton);
		}

	@Test
	public void personalInfoUpdateDropdown() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		Select category1 = new Select(driver.findElement(By
				.id("student_student_category_id")));
		category1.selectByVisibleText("General");
		Thread.sleep(500);
		Select category2 = new Select(driver.findElement(By
				.id("student_religion")));
		category2.selectByVisibleText("Other");
		Thread.sleep(500);
		findElementById(update);
		try {
			assertEquals(
					"Amelia Amelia is sucessfully updated.".toUpperCase(),
					driver.findElement(
							By.cssSelector("ul.message.success > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	@Test
	public void personalInfoUpdateImage() throws Exception {
		switchToStudentUpdateUnderStudentSearch();
		findElementById(studentImage);
		driver.switchTo().window("Change Image");
		driver.findElement(By.id("student_student_photo"))
				.sendKeys(
						"C:\\Users\\Public\\Pictures\\Sample Pictures\\Android_Wallpaper_by_clondike7.png");
		driver.findElement(By.name("commit")).click();
		findElementById(cropImage);
		driver.switchTo().window(parentWindow);
		findElementById(update);
		try {
			assertEquals(
					"Amelia Amelia is sucessfully updated."
							.toUpperCase(),
					driver.findElement(
							By.cssSelector("ul.message.success > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
}