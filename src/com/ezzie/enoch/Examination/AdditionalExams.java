package com.ezzie.enoch.Examination;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.Select;

import com.ezzie.enoch.employee_category.Common;
import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class AdditionalExams extends LoggedInUserTest{
	Common common = new Common();

	@Before
	public void setUp() throws Exception{
		super.setUp();
	}
	@After
	public void tearDown() throws Exception{
//		super.tearDown();
	}
	@Test
	public void checkNextButton() throws Exception {
		driver.findElement(By.linkText("Additional Exams")).click();
		driver.findElement(By.id("aex")).click();
		String waring = driver.findElement(By.cssSelector("ul.warning")).getText();
		System.out.println(waring);
		verifyTextPresent(waring, "PLEASE SELECT COURSE");
		}
	
     @Test
     public void selectCourse() throws Exception {
    	 driver.findElement(By.linkText("Additional Exams")).click();
    	 Select comobox = new Select(driver.findElement(By.id ("courses_name")));
    	 comobox.selectByVisibleText("PreNursery");
    	 driver.findElement(By.id("aex")).click();
 		 String waring = driver.findElement(By.cssSelector("ul.warning")).getText();
 		 System.out.println(waring);
 		 verifyTextPresent(waring, "PLEASE SELECT BATCH");
 		}
     @Test
     public void checkBackButtonOnFirstPage() throws Exception {
       	  driver.findElement(By.linkText("Additional Exams")).click();
       	  String CurrentUrl = driver.getCurrentUrl().toString();
       	  driver.findElement(By.xpath(".//*[@id='outer_block']/div/div[1]/ul/li/a/img")).click();
       	  Thread.sleep(1000);
       	  verifyTextPresent(CurrentUrl, "http://demo.ezzie.in/dashboard");
         }
     
     @Test
     public void clickOnNextButton() throws Exception {
    	 driver.findElement(By.linkText("Additional Exams")).click();
    	 Select comobox = new Select(driver.findElement(By.id ("courses_name")));
    	 comobox.selectByVisibleText("PreNursery");
    	 Thread.sleep(2000);
    	 Select comoBox = new Select(driver.findElement(By.id ("name_id")));
    	 comoBox.selectByIndex(1);
    	 driver.findElement(By.id("aex")).click();
    	 assertTrue(isElementPresent(By.id("aexamgroupindex")));
    	 }
  
     @Test
 	  public void checkBackButtonONSecondPage() throws Exception {
 	   clickOnNextButton();
 		driver.findElement(By.xpath(".//*[@id='outer_bloc']/div/div[1]/ul/li/a/img")).click();
 		Thread.sleep(1000);
 		assertTrue(isElementPresent(By.id("name_id")));
 		
 		}
     @Test
     public void clickOnNewButton() throws Exception {
    	 clickOnNextButton();
    	 driver.findElement(By.xpath(".//*[@id='outer_bloc']/div/fieldset/p/input")).click();
    	 assertTrue(isElementPresent(By.id("aexanew")));
    	 }
     @Test 
     public void clickOnBackButtonOnThridPage() throws Exception {
    	 clickOnNewButton();
    	 driver.findElement(By.xpath(".//*[@id='outer_bloc']/div/div[1]/ul/li/a/img")).click();
    	 assertTrue(isElementPresent(By.id("aexamgroupindex")));
    	 
     }
     @Test
     public void checkEmptyName() throws Exception{
    	 clickOnNewButton();
    	 driver.findElement(By.id("additional_exam_group")).click();
    	 String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
    	 System.out.println(warning);
    	 verifyTextPresent(warning, "PLEASE ENTER ADDITIONAL EXAM GROUP");
    	  }
     @Test
     public void checkNumericName() throws Exception {
    	 clickOnNewButton();
    	 driver.findElement(By.id("exam_option_name")).clear();
    	 driver.findElement(By.id("exam_option_name")).sendKeys(createNumber(10));
    	 driver.findElement(By.id("additional_exam_group")).click();
    	 String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
    	 System.out.println(warning);
    	 verifyTextPresent(warning, "PLEASE ENTER CHARACTERS FOR ADDITIONAL EXAM NAME");
    	  }
     @Test
     public void checkSpecialCharacter() throws Exception {
    	 clickOnNewButton();
    	 driver.findElement(By.id("exam_option_name")).clear();
    	 driver.findElement(By.id("exam_option_name")).sendKeys(createSpecialChars(6));
    	 driver.findElement(By.id("additional_exam_group")).click();
    	 String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
    	 System.out.println(warning);
    	 verifyTextPresent(warning, "PLEASE ENTER CHARACTERS FOR ADDITIONAL EXAM NAME");
    	 }
     
     @Test
     public void selectAnyStudent() throws Exception{
    	 clickOnNewButton();
    	 driver.findElement(By.id("exam_option_name")).clear();
    	 driver.findElement(By.id("exam_option_name")).sendKeys(common.randomstring());
    	 common.randomlyCheckCheckBox("aexanew", "id('aexanew')/tbody/tr", "td", 1,"students_list_","id",driver);
    	 driver.findElement(By.id("additional_exam_group")).click();
    	 assertTrue(isElementPresent(By.id("aexammf")));
    	 }
     
     @Test
     public void checkEmptyMaximumMarks() throws Exception {
    	 selectAnyStudent();
    	 driver.findElement(By.id("additional_exam_group_minimum_marks")).clear();
    	 driver.findElement(By.id("additional_exam_group_minimum_marks")).sendKeys("30");
     	 driver.findElement(By.id("create_additional_exam")).click();
    	 String message = driver.findElement(By.cssSelector("ul.error li li")).getText();
    	 System.out.println(message);
    	 verifyTextPresent(message, "EXAMS MAXIMUM MARKS CAN'T BE BLANK");
     }
	@Test
	public void checkEmptyMinimumMarks() throws Exception {
		 selectAnyStudent();
    	 driver.findElement(By.id("additional_exam_group_maximum_marks")).clear();
    	 driver.findElement(By.id("additional_exam_group_maximum_marks")).sendKeys("100");
     	 driver.findElement(By.id("create_additional_exam")).click();
    	 String message = driver.findElement(By.cssSelector("ul.error li li")).getText();
    	 System.out.println(message);
	}
	@Test
	public void createExamGroup() throws Exception {
		selectAnyStudent();
		driver.findElement(By.id("additional_exam_group_maximum_marks")).clear();
	    driver.findElement(By.id("additional_exam_group_maximum_marks")).sendKeys("100");
	    driver.findElement(By.id("additional_exam_group_minimum_marks")).sendKeys("34"); 
        Thread.sleep(2000);
	    common.timeEntryInAdditionalExam("aexammf","id('aexammf')/tbody/tr",6,7,driver);
	    Thread.sleep(2000);
	    driver.findElement(By.id("create_additional_exam")).click();
	    String success = driver.findElement(By.cssSelector("ul.success")).getText();
	    System.out.println(success);
	    verifyTextPresent(success, "ADDITIONAL EXAM GROUP SUCCESSFULY SAVED.");
	}
	@Test
	public void clickBackButtonOnFourthPage() throws Exception {
		selectAnyStudent();
		driver.findElement(By.xpath(".//*[@id='outer_bloc']/div/div[1]/ul/li/a/img")).click();
		assertTrue(isElementPresent(By.id("aexamgroupindex")));
	}
	@Test
	public void clickOnExamGroup() throws Exception {
		clickOnNextButton();
		String ds = common.rowCountCheckOfDataTables("aexamgroupindex","id('aexamgroupindex')/tbody/tr", "td", 2,"a","href",driver);
		driver.get(ds);
		assertTrue(isElementPresent(By.id("aexamGroupShow")));
		}
	@Test 
	public void clickBackButtonOnSubjectPage() throws Exception {
		clickOnNextButton();
		driver.findElement(By.xpath(".//*[@id='outer_bloc']/div/div[1]/ul/li/a/img")).click();
		assertTrue(isElementPresent(By.id("aexamgroupindex")));

	}
   @Test
   public void enterMarks() throws Exception {
	    clickOnExamGroup();
	   String id = common.rowCountCheckOfDataTables("aexamGroupShow","id('aexamGroupShow')/tbody/tr", "td", 3,"a","href",driver);
	   driver.get(id);
	   common.rowCountEnterMarks("aexamShow", "id('aexamShow')/tbody/tr", driver);
	   driver.findElement(By.xpath(".//*[@id='outer_bloc']/div/form/fieldset/p/input")).click();
	   String success = driver.findElement(By.cssSelector("ul.success")).getText();
	   System.out.println(success);
	   verifyTextPresent(success, "EXAM SCORES UPDATED");
   }
   
}

