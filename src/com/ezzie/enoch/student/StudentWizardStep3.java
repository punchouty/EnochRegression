package com.ezzie.enoch.student;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class StudentWizardStep3 extends LoggedInUserTest {

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
	public void uploadImage() throws Exception {
		switchTOStudentWizard2();
		switchToStudentWizard3();
		driver.findElement(By.id("upload_image"))
				.sendKeys(
						"C:\\Users\\Public\\Pictures\\Sample Pictures\\Android_Wallpaper_by_clondike7.png");
		driver.findElement(By.id("wizard_next_button")).click();
		try {
			assertEquals(
					"Student succesfully updated".toUpperCase(),
					driver.findElement(
							By.cssSelector("ul.message.warning  > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

	}
	
	@Test
	public void testBlankImage() throws Exception {
		switchTOStudentWizard2();
		switchToStudentWizard3();
		driver.findElement(By.id("wizard_next_button")).click();
		try {
			assertTrue(isElementPresent(By.cssSelector("span.status-ok")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		String message = driver.findElement(By.cssSelector("ul.message.warning  > li")).getText();
		try {
			assertEquals("Student successfully created".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning  > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

	}
	
	@Test
	public void testCropImage() throws Exception {
		switchTOStudentWizard2();
		switchToStudentWizard3();
		//driver.findElement(By.id("upload_image")).clear();
		driver.findElement(By.id("upload_image")).sendKeys("C:\\Users\\Public\\Pictures\\Sample Pictures\\andesk.png");
		driver.findElement(By.id("wizard_next_button")).click();
		try {
			assertEquals("Student succesfully updated".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning  > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertTrue(isElementPresent(By.id("previews")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.id("wizard_next_button")).click();
		try {
			assertEquals("Successfully updated user.".toUpperCase(), driver.findElement(By.cssSelector("ul.message.warning  > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
}
}
