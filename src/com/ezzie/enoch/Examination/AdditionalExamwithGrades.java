package com.ezzie.enoch.Examination;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.ezzie.enoch.employee_category.Common;
import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class AdditionalExamwithGrades extends LoggedInUserTest {
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
    public void clickOnNextButton() throws Exception {
   	 driver.findElement(By.linkText("Additional Exams")).click();
   	 Select comobox = new Select(driver.findElement(By.id ("courses_name")));
   	 comobox.selectByVisibleText("Nursery");
   	 Thread.sleep(2000);
   	 Select comoBox = new Select(driver.findElement(By.id ("name_id")));
   	 comoBox.selectByIndex(1);
   	 driver.findElement(By.id("aex")).click();
   	 assertTrue(isElementPresent(By.id("aexamgroupindex")));
   	 }
	 @Test
     public void clickOnNewButton() throws Exception {
    	 clickOnNextButton();
    	 driver.findElement(By.xpath(".//*[@id='outer_bloc']/div/fieldset/p/input")).click();
    	 assertTrue(isElementPresent(By.id("aexanew")));
    	 }
	 
	 @Test
     public void selectAnyStudent() throws Exception{
    	 clickOnNewButton();
    	 driver.findElement(By.id("exam_option_name")).clear();
    	 driver.findElement(By.id("exam_option_name")).sendKeys(common.randomstring());
    	 Select comobox = new Select(driver.findElement(By.id("exam_option_exam_type")));
    	 comobox.selectByIndex(1);
    	 common.randomlyCheckCheckBox("aexanew", "id('aexanew')/tbody/tr", "td", 1,"students_list_","id",driver);
    	 driver.findElement(By.id("additional_exam_group")).click();
    	 assertTrue(isElementPresent(By.id("exammf")));
    	 }
	 
	 @Test
	 public void createExamGroup() throws Exception {
		 selectAnyStudent();
		 common.rowCountCheck("exammf", "id('exammf')/tbody/tr",4, 5, driver);
		 System.out.println("print");
		 driver.findElement(By.id("create_grade_additional_exam")).click();
		 String success = driver.findElement(By.cssSelector("ul.success")).getText();
		 System.out.println(success);
		 verifyTextPresent(success, "ADDITIONAL EXAM GROUP CREATED SUCCESSFULLY.");
	 }
	 @Test
	 public void EnterGrades() throws Exception {
		 
		 
	 }
	 
}
