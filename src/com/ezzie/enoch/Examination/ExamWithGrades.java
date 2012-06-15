package com.ezzie.enoch.Examination;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


import com.ezzie.enoch.employee_category.Common;
import com.ezzie.enoch.infrastructure.LoggedInUserTest;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class ExamWithGrades extends LoggedInUserTest{
    Common common = new Common();

		@Before
		public void setUp() throws Exception{
			super.setUp();
		}
		@After
		public void tearDown() throws Exception{
			super.tearDown();
		}
		
		@Test
		public void clickOnNextButton() throws Exception{
		driver.findElement(By.linkText("Exams")).click();
		Select comboBox = new Select(driver.findElement(By.id("courses_name")));
		comboBox.selectByVisibleText("PreNursery");
		Thread.sleep(2000);
		Select combBox = new Select(driver.findElement(By.id("exams_batch_id")));
		combBox.selectByIndex(1);
		driver.findElement(By.id("exacreate")).click();
		
        }
		
		@Test
		public void clickOnSaveButton() throws Exception{
		clickOnNextButton();
		driver.findElement(By.xpath(".//*[@id='outer_bloc']/div/fieldset/p/input[1]")).click();
		driver.findElement(By.id("exam_option_name")).clear();
	    driver.findElement(By.id("exam_option_name")).sendKeys(common.randomstring());
	    Select comboBoX = new Select(driver.findElement(By.id("exam_option_exam_type")));
	    comboBoX.selectByIndex(1);
		driver.findElement(By.id("new_exam")).click();
		}
		
		@Test
		 public void createExam() throws Exception {
			clickOnSaveButton();
			common.rowCountCheck("examgf","id('examgf')/tbody/tr",4,5,driver);
		    driver.findElement(By.id("create_grade_exam")).click();	
		    String success = driver.findElement(By.cssSelector("ul.success")).getText();
		    System.out.println(success);
		    verifyTextPresent(success, "EXAM GROUP CREATED SUCCESSFULLY.");
			}
		
		@Test
	   public void clickOnExamGruop() throws Exception {
		   driver.findElement(By.linkText("Exams")).click();
			Select comboBox = new Select(driver.findElement(By.id("courses_name")));
			comboBox.selectByVisibleText("PreNursery");
			Thread.sleep(2000);
			Select combBox = new Select(driver.findElement(By.id("exams_batch_id")));
			combBox.selectByIndex(1);
		    driver.findElement(By.id("exacreate")).click();
		    String dd = common.rowCountCheckOfDataTables("examgroupindex", "id('examgroupindex')/tbody/tr", "td", 2,"a","href",driver);
		    driver.get(dd);
       		  
	   }
	       @Test
	       public void clickOnSubjectTopicName() throws Exception {
	    	   clickOnExamGruop();
	        	String ds = common.rowCountCheckOfDataTables("examGroupGradeShow","id('examGroupGradeShow')/tbody/tr", "td", 3,"a","href",driver);
	            driver.get(ds);
	       
	    }
	       @Test
	       public void enterGradesInFields() throws Exception{
	    	   clickOnSubjectTopicName();
	            common.rowCountEnterGrades(driver);
	            driver.findElement(By.xpath(".//*[@id='main_block']/div/div/form/fieldset/p/input")).click();
	            String success = driver.findElement(By.cssSelector("ul.success")).getText();
	            System.out.println(success);
	            verifyTextPresent(success, "EXAM SCORES UPDATED.");
	       }
	   	@Test 
	   	public void clickOnPublished() throws Exception{
	   		clickOnNextButton();
	   		driver.findElement(By.linkText("Publish Exam Schedule")).click();
	   		Thread.sleep(2000);
	   		
	   		}
	   	@Test
	   	public void clickOnPublishExamResult() throws Exception {
	   		clickOnNextButton();
	   		driver.findElement(By.linkText("Publish Exam Result")).click();
	   		Thread.sleep(1000);
	   	}
	   
}
		