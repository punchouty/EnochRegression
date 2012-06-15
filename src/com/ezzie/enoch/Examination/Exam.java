package com.ezzie.enoch.Examination;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.ezzie.enoch.employee_category.Common;
import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class Exam extends LoggedInUserTest{ 
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
	public void clickNextButtonWithoutSelection() throws Exception{
		driver.findElement(By.linkText("Exams")).click();
		driver.findElement(By.id("exacreate")).click();
		String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		System.out.println(warning);
		verifyTextPresent(warning,"PLEASE SELECT COURSE");
		
	}
	
	@Test
	public void selectOnlycourse() throws Exception{
		driver.findElement(By.linkText("Exams")).click();
		Select comboBox = new Select(driver.findElement(By.id("courses_name")));
		comboBox.selectByVisibleText("Nursery");
		driver.findElement(By.id("exacreate")).click();
		String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		System.out.println(warning);
		verifyTextPresent(warning,"PLEASE SELECT BATCH");
		}
	
	@Test
      public void checkBackButtonOnFirstPage() throws Exception {
    	  driver.findElement(By.linkText("Exams")).click();
    	  String CurrentUrl = driver.getCurrentUrl().toString();
    	  driver.findElement(By.xpath(".//*[@id='outer_block']/div/div[1]/ul/li/a/img")).click();
    	  Thread.sleep(1000);
    	  verifyTextPresent(CurrentUrl, "http://demo.ezzie.in/dashboard");
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
    }

	@Test
	public void checkBackButtonONSecondPage() throws Exception {
		driver.findElement(By.linkText("Exams")).click();
		Select comboBox = new Select(driver.findElement(By.id("courses_name")));
		comboBox.selectByVisibleText("Nursery");
		Thread.sleep(2000);
		Select combBox = new Select(driver.findElement(By.id("exams_batch_id")));
		combBox.selectByIndex(1);
		String currentUrl = driver.getCurrentUrl().toString();
		driver.findElement(By.id("exacreate")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='outer_bloc']/div/div[1]/ul/li/a/img")).click();
		Thread.sleep(1000);
		String backUrl = driver.getCurrentUrl().toString();
		verifyTextPresent(currentUrl , backUrl);
		}
	
	@Test
    public void clickOnNewButton() throws Exception{
		driver.findElement(By.linkText("Exams")).click();
    	System.out.println("first click");
    	Select comboBox = new Select(driver.findElement(By.id("courses_name")));
    	comboBox.selectByVisibleText("Nursery");
    	Thread.sleep(2000);
    	Select combBox = new Select(driver.findElement(By.id("exams_batch_id")));
    	combBox.selectByIndex(1);
    	driver.findElement(By.id("exacreate")).click();
    	driver.findElement(By.xpath("html/body/article/div[2]/section/div/div/fieldset/p/input[1]")).click();
    }
	
	@Test
	public void clickOnFirstSaveButton() throws Exception{
		clickOnNewButton();
	driver.findElement(By.id("new_exam")).click();
	String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
	System.out.println(warning);
	verifyTextPresent(warning, "PLEASE ENTER NAME");
	} 
	
	@Test
	public void checkBackButtonOnThirdPage() throws Exception {
		driver.findElement(By.linkText("Exams")).click();
    	Select comboBox = new Select(driver.findElement(By.id("courses_name")));
    	comboBox.selectByVisibleText("Nursery");
    	Thread.sleep(2000);
    	Select combBox = new Select(driver.findElement(By.id("exams_batch_id")));
    	combBox.selectByIndex(1);
    	driver.findElement(By.id("exacreate")).click();
    	Thread.sleep(2000);
        driver.findElement(By.xpath(".//*[@id='outer_bloc']/div/fieldset/p/input[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//*[@id='outer_block']/div/div[1]/ul/li/a/img")).click();
	     assertTrue(isElementPresent(By.id("examgroupindex")));
		 }

	@Test
	public void enterValuesInName() throws Exception {
		clickOnNewButton();
	    driver.findElement(By.id("exam_option_name")).clear();
	    driver.findElement(By.id("exam_option_name")).sendKeys(common.randomstring());
	    Select comboBox = new Select(driver.findElement(By.id("exam_option_exam_type")));
	    comboBox.selectByIndex(0);
	    driver.findElement(By.id("new_exam")).click();	
	}
	
	@Test
    public void emptyMaximumMarks() throws Exception{
    enterValuesInName();
    driver.findElement(By.id("exam_group_maximum_marks")).clear();
    driver.findElement(By.id("exam_group_minimum_marks")).sendKeys("34"); 
    driver.findElement(By.id("create_exam")).click();
    String message = driver.findElement(By.cssSelector("ul.error li li")).getText();
    System.out.println(message);
    verifyTextPresent(message, "EXAMS MAXIMUM MARKS CAN'T BE BLANK");
	}
	
	@Test
    public void emptyMinimumMmarks() throws Exception{
	enterValuesInName();
    driver.findElement(By.id("exam_group_maximum_marks")).clear();
    driver.findElement(By.id("exam_group_maximum_marks")).sendKeys("45");
    driver.findElement(By.id("exam_group_minimum_marks")).clear(); 
    driver.findElement(By.id("create_exam")).click();
    String message = driver.findElement(By.cssSelector("ul.error li li")).getText();
    System.out.println(message);
    verifyTextPresent(message, "EXAMS MINIMUM MARKS CAN'T BE BLANK");
	}
	
	@Test
    public void createExamGroup() throws Exception{
		enterValuesInName();
    driver.findElement(By.id("exam_group_maximum_marks")).clear();
    driver.findElement(By.id("exam_group_maximum_marks")).sendKeys("100");
    driver.findElement(By.id("exam_group_minimum_marks")).sendKeys("34"); 
    common.rowCountCheck( "exammf","id('exammf')/tbody/tr",6,7,driver);
    driver.findElement(By.id("create_exam")).click();
	}
	
	@Test
	public void clickOnExamGroup() throws Exception{
		clickNextButton();
	    String ds = common.rowCountCheckOfDataTables("examgroupindex","id('examgroupindex')/tbody/tr", "td", 2,"a","href",driver);
		System.out.println(ds);
		driver.get(ds);
	 }
	
	@Test
	public void enterMarks() throws Exception{
		clickOnExamGroup();
		Thread.sleep(2000);
		String dss = common.rowCountCheckOfDataTables("examGroupShow","id('examGroupShow')/tbody/tr", "td", 3,"a","href",driver);
		driver.get(dss);
		common.rowCountEnterMarks("examShow", "id('examShow')/tbody/tr", driver);
		driver.findElement(By.xpath(".//*[@id='main_block']/div/div/form/fieldset/p/input")).click();
		String success = driver.findElement(By.cssSelector("ul.success")).getText();
		System.out.println(success);
		verifyTextPresent(success, "EXAM SCORES UPDATED");
		}

		@Test
	public void clickOnConnectExamGroup() throws Exception{
		driver.findElement(By.linkText("Exams")).click();
		Select comboBox = new Select(driver.findElement(By.id("courses_name")));
		comboBox.selectByVisibleText("Nursery");
		Thread.sleep(2000);
		Select combBox = new Select(driver.findElement(By.id("exams_batch_id")));
		combBox.selectByIndex(1);
		driver.findElement(By.id("exacreate")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='outer_bloc']/div/fieldset/p/input[2]")).click();
		assertTrue(isElementPresent(By.id("view_group")));
	}
		@Test
	public void emptyInGroupName() throws Exception {
		clickOnConnectExamGroup();
     	Thread.sleep(1000);
	    driver.findElement(By.xpath(".//*[@id='outer_bloc']/div/fieldset/p/input")).click();
	    driver.findElement(By.id("connect_save")).click();
	    String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
	    System.out.println(warning);
	    verifyTextPresent(warning, "PLEASE ENTER GROUPED EXAM NAME");
	}  
		
		@Test
		public void enterNumericInGroupName() throws Exception {
		emptyInGroupName();
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//*[@id='outer_bloc']/div/fieldset/p/input")).click();
		driver.findElement(By.id("exam_grouping_grouped_exam_name")).clear();
		driver.findElement(By.id("exam_grouping_grouped_exam_name")).sendKeys(createNumber(9));
		driver.findElement(By.id("connect_save")).click();
		String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		System.out.println(warning);
		verifyTextPresent(warning, "PLEASE ENTER CHARACTER");
		}  
		
		@Test 
		public void enterSpecialCharacterInGroupName() throws Exception {
		emptyInGroupName();
		Thread.sleep(1000);	
		driver.findElement(By.xpath(".//*[@id='outer_bloc']/div/fieldset/p/input")).click();
		driver.findElement(By.id("exam_grouping_grouped_exam_name")).clear();
		driver.findElement(By.id("exam_grouping_grouped_exam_name")).sendKeys(createSpecialChars(6));
		driver.findElement(By.id("connect_save")).click();
		String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		System.out.println(warning);
		verifyTextPresent(warning, "SPECIAL CHARACTERS ARE NOT ALLOWED FOR GROUPED EXAM NAME");
		}  
		
         @Test
         public void checkBackButtonOnSubjectPage() throws Exception {
        	driver.findElement(By.linkText("Exams")).click();
     		Select comboBox = new Select(driver.findElement(By.id("courses_name")));
    		comboBox.selectByVisibleText("Nursery");
    		Thread.sleep(2000);
    		Select combBox = new Select(driver.findElement(By.id("exams_batch_id")));
    		combBox.selectByIndex(1);
    	    driver.findElement(By.id("exacreate")).click();
    	    String dd = common.rowCountCheckOfDataTables("examgroupindex", "id('examgroupindex')/tbody/tr", "td", 2,"a","href",driver);
    	    driver.get(dd);
    	    driver.findElement(By.xpath(".//*[@id='outer_bloc']/div/div[1]/ul/li/a")).click();
            assertTrue(isElementPresent(By.id("examgroupindex")));
         }
         
        @Test
        public void clickOnTopicName() throws Exception{
        	clickOnExamGroup();
        	String ds = common.rowCountCheckOfDataTables("examGroupShow","id('examGroupShow')/tbody/tr", "td", 3,"a","href",driver);
            driver.get(ds);
      }
        
        @Test
        public void deleteSubjectTopicExam() throws Exception{
        	clickOnExamGroup();
        	System.out.println("this is delete");
        	String ds = common.rowCountCheckOfDataTable("examGroupShow","id('examGroupShow')/tbody/tr", "td", 6,"a","exam_id",driver);
        	driver.get(ds);
        	driver.findElement(By.xpath(".//*[@id='modal']/div/div[1]/div[2]/button[1]")).click();
        	String success = driver.findElement(By.cssSelector("ul.success")).getText();
        	System.out.println(success);
        	verifyTextPresent(success, "EXAM DELETED SUCCESSFULY");
        	
        }
        @Test
        public void EditAndUpdateSubjectTopicExam() throws Exception{
        	clickOnExamGroup();
        	System.out.println("this is delete");
        	String ds = common.rowCountCheckOfDataTables("examGroupShow","id('examGroupShow')/tbody/tr", "td", 6,"a","href",driver);
        	Thread.sleep(2000);
        	driver.get(ds);
        	driver.findElement(By.id("edit_exam")).click();
        	String success = driver.findElement(By.cssSelector("ul.success")).getText();
        	System.out.println(success);
        	verifyTextPresent(success, "UPDATED EXAM DETAILS SUCCESSFULLY.");
        	
        	}
        
        
        @Test
        public void checkBackButtonOnResultEnteryPage() throws Exception{
        	clickOnTopicName();
        	driver.findElement(By.xpath(".//*[@id='main_block']/div/div/div[1]/ul/li/a/img")).click();
        	assertTrue(isElementPresent(By.id("examGroupShow")));
        	
        }
}