package com.ezzie.enoch.class_timing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class ClassTiming extends LoggedInUserTest {
	@Before
	public void setUp() throws Exception {
		 super.setUp();
	}
	@After
	public void tearDown() throws Exception{
//		 super.tearDown();
	}
	
//	@Test
	public void selectCourseAndBatch() throws Exception {
		driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
		driver.findElement(By.linkText("Class Timings")).click();
		Select comobox = new Select(driver.findElement(By.id("course_id")));
		comobox.selectByIndex(2);
		Thread.sleep(2000);
		Select comoBox = new Select(driver.findElement(By.id("batch_id")));
		comoBox.selectByIndex(1);
		driver.findElement(By.className("class_timing")).click();
		}
	
	 @Test
	public void emptyNameInAddaWeekDay() throws Exception {
		 selectCourseAndBatch();
		 System.out.println("this is test");
         Thread.sleep(2000);
		 driver.findElement(By.xpath(".//*[@id='class_timing_name']")).sendKeys("jfkdgkhd");
		 System.out.println("this is name");
		 driver.findElement(By.xpath(".//*[@id='modal']/div/div[1]/div[2]/button[2]")).click();
		 System.out.println("this is create");
//	     driver.findElement(By.linkText("Create")).click();
		 System.out.println("this is create");
		 String warning = driver.findElement(By.cssSelector("warning")).getText();
		 System.out.println(warning);
		 verifyTextPresent(warning, "PLEASE ENTER NAME");
	 }
	 @Test
	public void checkbackbutton() throws Exception {
		 driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
		 String currentUrl = driver.getCurrentUrl().toString();
         driver.findElement(By.linkText("Class Timings")).click();
         driver.findElement(By.xpath(".//*[@id='simple_week_form']/div[1]/ul/li/a/img")).click();
         Thread.sleep(2000);
         String backUrl = driver.getCurrentUrl().toString();
         verifyTextPresent(currentUrl, backUrl);
         }
	 
	 @Test
	 public void enterValuesInName() throws Exception {
		 driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
		 driver.findElement(By.linkText("Class Timings")).click();
//		 driver.findElement(By.id("class_timing_name")).clear();
//		 driver.findElement(By.id("class_timing_name")).sendKeys(RandomString.randomstring());
//		 String warning = driver.findElement(By.cssSelector("ul.warning"));
		 
	 }
}
