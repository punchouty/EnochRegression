package com.ezzie.enoch.Examination;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.internal.selenesedriver.GetCurrentUrl;
import org.openqa.selenium.support.ui.Select;

import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class ExamWiseRreport extends LoggedInUserTest{
	@Before
	public void setUp() throws Exception{
		super.setUp();
	}
	@After
	public void tearDown() throws Exception{
//		super.tearDown();
	}
	@Test
	public void checkOnViewButtonExam() throws Exception{
		driver.findElement(By.linkText("Exam Wise Report")).click();
		driver.findElement(By.id("ewr")).click();
		String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		System.out.println(warning);
		verifyTextPresent(warning,"PLEASE SELECT COURSE");
		}
	
	@Test
	public void selectOnlyCourse() throws Exception{
		driver.findElement(By.linkText("Exam Wise Report")).click();
        Select comboBox = new Select(driver.findElement(By.id("exam_wise_report_course")));
		comboBox.selectByVisibleText("Nursery");
        driver.findElement(By.id("ewr")).click();
		String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		System.out.println(warning);
		verifyTextPresent(warning,"PLEASE SELECT BATCH");
	}
	
	@Test
	public void selectCourseBatch() throws Exception{
	driver.findElement(By.linkText("Exam Wise Report")).click();
	Select comboBox = new Select(driver.findElement(By.id("exam_wise_report_course")));
	comboBox.selectByVisibleText("Nursery");
	Thread.sleep(2000);
	Select combBox = new Select(driver.findElement(By.id("exam_report_batch_id")));
	combBox.selectByIndex(1);
	driver.findElement(By.id("ewr")).click();
	String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
	System.out.println(warning);
	verifyTextPresent(warning,"PLEASE SELECT EXAM GROUP");
	}
	
	 @Test
	public void  nextlinkPageClickView() throws Exception{
	driver.findElement(By.linkText("Exam Wise Report")).click();
	Select comboBox = new Select(driver.findElement(By.id("exam_wise_report_course")));
	comboBox.selectByVisibleText("Nursery");
	Thread.sleep(2000);
	Select combBox = new Select(driver.findElement(By.id("exam_report_batch_id")));
	combBox.selectByIndex(1);
	Thread.sleep(2000);
	System.out.println("this is next pagegg");
	Select comBox = new Select(driver.findElement(By.id("exam_report_exam_group_id")));
	comBox.selectByIndex(1);
	driver.findElement(By.id("ewr")).click();
	System.out.println("this is next page");

	}
		public void  nextlinkPageClick() throws Exception{
		driver.findElement(By.linkText("Exam Wise Report")).click();
		Select comboBox = new Select(driver.findElement(By.id("exam_wise_report_course")));
		comboBox.selectByVisibleText("Nursery");
		Thread.sleep(2000);
		Select combBox = new Select(driver.findElement(By.id("exam_report_batch_id")));
		combBox.selectByIndex(1);
		Thread.sleep(2000);
		System.out.println("this is next pagegg");
		Select comBox = new Select(driver.findElement(By.id("exam_report_exam_group_id")));
		comBox.selectByIndex(1);
		driver.findElement(By.id("ewr")).click();
		System.out.println("this is next page");

		}
	 
	@Test
	 public void checkBackButtonOnFirstPage() throws Exception {
		 driver.findElement(By.linkText("Exam Wise Report")).click();
		 String currentUrl = driver.getCurrentUrl().toString();
		 Thread.sleep(1000);
		 driver.findElement(By.xpath(".//*[@id='outer_block']/div/div[1]/ul/li/a/img")).click();
		 Thread.sleep(1000);
		 verifyTextPresent(currentUrl, "http://demo.ezzie.in/dashboard");
		 }
	@Test
	public void ClickOnConsolidated_report() throws Exception{
		nextlinkPageClick();
	    driver.findElement(By.xpath(".//*[@id='outer_block']/div/fieldset/p/input[1]")).click();
		assertTrue(isElementPresent(By.id("ExConsolidate")));
		
	}
	@Test
	public void checkBackButtonOnSecondPage() throws Exception {
		nextlinkPageClick();
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//*[@id='outer_block']/div/div[1]/ul/li/a/img")).click();
        assertTrue(isElementPresent(By.id("exam_report_exam_group_id")));
		}
	
	
	}	
		



