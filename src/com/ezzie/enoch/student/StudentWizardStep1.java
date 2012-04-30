package com.ezzie.enoch.student;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class StudentWizardStep1 extends LoggedInUserTest{
	
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
	public void admissionNoEmpty() throws Exception {
		driver.findElement(By.cssSelector("#new_student > img.first-child")).click();
		// ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp]]
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow]]
		driver.switchTo().window("Student Admission");
		driver.findElement(By.id("student_admission_no")).clear();
		driver.findElement(By.id("student_admission_no")).sendKeys("");
		driver.findElement(By.id("next_button")).click();
		try {
			assertEquals("PLEASE ENTER FIRST NAME", driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	
	@Test
	public void firstNameEmpty() throws Exception {
		driver.findElement(By.cssSelector("#new_student > img.first-child")).click();
		// ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp]]
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow]]
		driver.switchTo().window("Student Admission");
		driver.findElement(By.id("student_first_name")).clear();
		driver.findElement(By.id("student_first_name")).sendKeys("");
		driver.findElement(By.id("next_button")).click();
		try {
			assertEquals("PLEASE ENTER FIRST NAME", driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		}
	
	@Test
	public void firstNameAlphabets() throws Exception {
		driver.findElement(By.cssSelector("#new_student > img.first-child")).click();
		// ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp]]
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow]]
		driver.switchTo().window("Student Admission");
		driver.findElement(By.id("student_first_name")).clear();
		driver.findElement(By.id("student_first_name")).sendKeys("Vishal");
		driver.findElement(By.id("next_button")).click();
		try {
			assertEquals("PLEASE ENTER LAST NAME", driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void firstNameAlphanumerics() throws Exception {
		driver.findElement(By.cssSelector("#new_student > img.first-child")).click();
		// ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp]]
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow]]
		driver.switchTo().window("Student Admission");
		driver.findElement(By.id("student_first_name")).clear();
		driver.findElement(By.id("student_first_name")).sendKeys("Vishal123");
		driver.findElement(By.id("next_button")).click();
		try {
			assertEquals("PLEASE ENTER ONLY CHARACTERS FOR FIRST NAME", driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void firstNameSpecialCharacters() throws Exception {
		driver.findElement(By.cssSelector("#new_student > img.first-child")).click();
		// ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp]]
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow]]
		driver.switchTo().window("Student Admission");
		driver.findElement(By.id("student_first_name")).clear();
		driver.findElement(By.id("student_first_name")).sendKeys("Vishal123$");
		driver.findElement(By.id("next_button")).click();
		try {
			assertEquals("Special Charcter are not allowed in First name and Admission No".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void firstNameFieldMaxLength() throws Exception {
		driver.findElement(By.cssSelector("#new_student > img.first-child")).click();
		// ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp]]
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow]]
		driver.switchTo().window("Student Admission");
		driver.findElement(By.id("student_first_name")).clear();
		driver.findElement(By.id("student_first_name")).sendKeys(createString(51));
		driver.findElement(By.id("student_last_name")).clear();
		driver.findElement(By.id("student_last_name")).sendKeys("Handa");
		Select course = new Select(driver.findElement(By.id("adv_search_course_id")));
		course.selectByVisibleText("Nursery");
		Thread.sleep(3000);
		Select batch = new Select(driver.findElement(By.id("student_batch_id")));
		batch.selectByVisibleText("Nursery - A-2012");
		Thread.sleep(3000);
		driver.findElement(By.id("next_button")).click();
		try {
			assertEquals("You can not enter more than 50 character in field".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	

	@Test
	public void lastNameEmpty() throws Exception {
		driver.findElement(By.cssSelector("#new_student > img.first-child")).click();
		// ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp]]
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow]]
		driver.switchTo().window("Student Admission");
		driver.findElement(By.id("student_first_name")).clear();
		driver.findElement(By.id("student_first_name")).sendKeys("Vishal");
		driver.findElement(By.id("student_last_name")).clear();
		driver.findElement(By.id("student_last_name")).sendKeys("");
		driver.findElement(By.id("next_button")).click();
		try {
			assertEquals("Please Enter Last Name".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		}

	@Test
	public void lastNameAlphabets() throws Exception {
		driver.findElement(By.cssSelector("#new_student > img.first-child")).click();
		// ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp]]
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow]]
		driver.switchTo().window("Student Admission");
		driver.findElement(By.id("student_first_name")).clear();
		driver.findElement(By.id("student_first_name")).sendKeys("Vishal");
		driver.findElement(By.id("student_last_name")).clear();
		driver.findElement(By.id("student_last_name")).sendKeys("Handa");
		driver.findElement(By.id("next_button")).click();
		
		try {
			assertEquals("PLEASE SELECT BATCH", driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	

	@Test
	public void lastNameAlphanumerics() throws Exception {
		driver.findElement(By.cssSelector("#new_student > img.first-child")).click();
		// ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp]]
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow]]
		driver.switchTo().window("Student Admission");
		driver.findElement(By.id("student_first_name")).clear();
		driver.findElement(By.id("student_first_name")).sendKeys("Vishal");
		driver.findElement(By.id("student_last_name")).clear();
		driver.findElement(By.id("student_last_name")).sendKeys("Handa123");
		driver.findElement(By.id("next_button")).click();
		try {
			assertEquals("PLEASE ENTER ONLY CHARACTERS FOR LAST NAME", driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	@Test
	public void lastNameSpecialCharacters() throws Exception {
		driver.findElement(By.cssSelector("#new_student > img.first-child")).click();
		// ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp]]
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow]]
		driver.switchTo().window("Student Admission");
		driver.findElement(By.id("student_first_name")).clear();
		driver.findElement(By.id("student_first_name")).sendKeys("Vishal");
		driver.findElement(By.id("student_last_name")).clear();
		driver.findElement(By.id("student_last_name")).sendKeys("Handa123$");
		driver.findElement(By.id("next_button")).click();
		try {
			assertEquals("Please enter only characters for last name".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	@Test
	public void lastNameFieldMaxLength() throws Exception {
		driver.findElement(By.cssSelector("#new_student > img.first-child")).click();
		// ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp]]
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow]]
		driver.switchTo().window("Student Admission");
		driver.findElement(By.id("student_first_name")).clear();
		driver.findElement(By.id("student_first_name")).sendKeys("Vishal");
		driver.findElement(By.id("student_last_name")).clear();
		driver.findElement(By.id("student_last_name")).sendKeys(createString(51));
		Select course = new Select(driver.findElement(By.id("adv_search_course_id")));
		course.selectByVisibleText("Nursery");
		Thread.sleep(3000);
		Select batch = new Select(driver.findElement(By.id("student_batch_id")));
		batch.selectByVisibleText("Nursery - A-2012");
		Thread.sleep(3000);
		driver.findElement(By.id("next_button")).click();
		try {
			assertEquals("You can not enter more than 50 character in field".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
}
