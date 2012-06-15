package com.ezzie.enoch.Examination;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class SubjectWiseReport extends LoggedInUserTest {
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@After
	public void tearDown() throws Exception {
		// super.tearDown();
	}

	 @Test
	 public void checkViewButton() throws Exception{
	 driver.findElement(By.linkText("Subject Wise Report")).click();
	 driver.findElement(By.id("swr")).click();
	 String warning =
	 driver.findElement(By.cssSelector("ul.warning")).getText();
	 System.out.println(warning);
	 verifyTextPresent(warning,"PLEASE SELECT COURSE");
	 }

	 @Test
	 public void selectOnlyCourse() throws Exception{
	 driver.findElement(By.linkText("Subject Wise Report")).click();
	 Select comboBox = new
	 Select(driver.findElement(By.id("exam_report_subject_course_id")));
	 comboBox.selectByVisibleText("Nursery");
	 driver.findElement(By.id("swr")).click();
	 String warning =
	 driver.findElement(By.cssSelector("ul.warning")).getText();
	 System.out.println(warning);
	 verifyTextPresent(warning,"PLEASE SELECT BATCH");
	 }

	 @Test
	 public void selectCourseBatch() throws Exception{
	 driver.findElement(By.linkText("Subject Wise Report")).click();
	 Select comboBox = new Select(driver.findElement(By.id("exam_report_subject_course_id")));
	 comboBox.selectByVisibleText("Nursery");
	 Thread.sleep(2000);
	 Select combBox = new Select(driver.findElement(By.id("subject_batch_id")));
	 combBox.selectByIndex(1);
	 driver.findElement(By.id("swr")).click();
	 String warning =driver.findElement(By.cssSelector("ul.warning")).getText();
	 System.out.println(warning);
	 verifyTextPresent(warning,"PLEASE SELECT SUBJECT");
	 }

	@Test
	public void clickOnViewButton() throws Exception {
		driver.findElement(By.linkText("Subject Wise Report")).click();
		Select comboBox = new Select(driver.findElement(By.id("exam_report_subject_course_id")));
		comboBox.selectByVisibleText("Nursery");
		Thread.sleep(2000);
		Select combBox = new Select(driver.findElement(By.id("subject_batch_id")));
		combBox.selectByIndex(1);
		Thread.sleep(2000);
		Select comoBox  = new Select(driver.findElement(By.id("exam_report_subject_id")));
		comoBox.selectByIndex(1);
		driver.findElement(By.id("swr")).click();
	}
	
	@Test 
	public void checkUrlPage() throws Exception{
	driver.findElement(By.linkText("Subject Wise Report")).click();
	Select comboBox = new Select(driver.findElement(By.id("exam_report_subject_course_id")));
	comboBox.selectByVisibleText("Nursery");
	Thread.sleep(2000);
	Select combBox = new Select(driver.findElement(By.id("subject_batch_id")));
	combBox.selectByIndex(1);
	Thread.sleep(2000);
	Select comoBox  = new Select(driver.findElement(By.id("exam_report_subject_id")));
	comoBox.selectByIndex(1);
	driver.findElement(By.id("swr")).click();
	assertTrue(isElementPresent(By.id("ExSubjectRe")));
	}

	@Test
	public void checkFirstBackButton() throws Exception{
	driver.findElement(By.linkText("Subject Wise Report")).click();
	String currentUrl = driver.getCurrentUrl().toString();
	driver.findElement(By.xpath(".//*[@id='outer_block']/div/div[1]/ul/li/a/img")).click();
	Thread.sleep(2000);
	verifyTextPresent(currentUrl, "http://demo.ezzie.in/dashboard");	
	}
	
	@Test
	public void checkSecondBackButton() throws Exception {
	 clickOnViewButton();
	 driver.findElement(By.xpath(".//*[@id='outer_bloc']/div/div[1]/ul/li/a/img")).click();
	 assertTrue(isElementPresent(By.id("exam_report_subject_id")));
	
	
	}
	
}
