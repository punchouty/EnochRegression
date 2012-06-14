package com.ezzie.enoch.examination;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class ExamWithGrades extends LoggedInUserTest{
	RandomString randomstring;
		@Before
		public void setUp() throws Exception{
			super.setUp();
		}
		@After
		public void tearDown() throws Exception{
//			super.tearDown();
		}
		
		@Test
		public void clickNextButton() throws Exception{
		driver.findElement(By.linkText("Exams")).click();
		Select comboBox = new Select(driver.findElement(By.id("courses_name")));
		comboBox.selectByVisibleText("Nursery");
		Thread.sleep(2000);
		Select combBox = new Select(driver.findElement(By.id("exams_batch_id")));
		combBox.selectByIndex(1);
		driver.findElement(By.id("exacreate")).click();
		assertTrue(isElementPresent(By.id("examgroupindex")));

	    }

		@Test
		public void clickOnNewButton() throws Exception{
		clickNextButton();
		driver.findElement(By.xpath(".//*[@id='outer_bloc']/div/fieldset/p/input[1]")).click();
		assertTrue(isElementPresent(By.id("exam_option_name")));
		} 
		
		@Test
		public void clickOnSaveButton() throws Exception{
		clickNextButton();
		driver.findElement(By.xpath(".//*[@id='outer_bloc']/div/fieldset/p/input[1]")).click();
		driver.findElement(By.id("exam_option_name")).clear();
	    driver.findElement(By.id("exam_option_name")).sendKeys(randomstring.randomstring());
	    Select comboBoX = new Select(driver.findElement(By.id("exam_option_exam_type")));
	    comboBoX.selectByIndex(1);
		driver.findElement(By.id("new_exam")).click();
		}
		
}