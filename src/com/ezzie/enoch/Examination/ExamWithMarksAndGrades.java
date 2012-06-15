package com.ezzie.enoch.Examination;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.ezzie.enoch.employee_category.Common;
import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class ExamWithMarksAndGrades extends LoggedInUserTest{
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
	public void clickOnNextButton() throws Exception{
	driver.findElement(By.linkText("Exams")).click();
	Select comboBox = new Select(driver.findElement(By.id("courses_name")));
	comboBox.selectByVisibleText("KG");
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
    comboBoX.selectByIndex(2);
	driver.findElement(By.id("new_exam")).click();
	}
	
	@Test
    public void createExamGroup() throws Exception{
		clickOnSaveButton();
    driver.findElement(By.id("exam_group_maximum_marks")).clear();
    driver.findElement(By.id("exam_group_maximum_marks")).sendKeys("100");
    driver.findElement(By.id("exam_group_minimum_marks")).sendKeys("34"); 
    common.rowCountCheck("exammf","id('exammf')/tbody/tr",6,7,driver);
    driver.findElement(By.id("create_exam")).click();
    String success = driver.findElement(By.cssSelector("ul.success")).getText();
    verifyTextPresent(success, "EXAM GROUP CREATED SUCCESSFULLY.");
	}
	
	@Test 
	public void clickOnExamGroup() throws Exception {
		clickOnNextButton ();
		String ds = common.rowCountCheckOfDataTables("examgroupindex","id('examgroupindex')/tbody/tr", "td", 2,"a","href",driver);
		driver.get(ds);
		}
	
	@Test
	public void clickOnsybjectTopic() throws Exception {
		clickOnExamGroup();
		String sub = common.rowCountCheckOfDataTables("examGroupShow","id('examGroupShow')/tbody/tr", "td", 3,"a","href",driver);
		driver.get(sub);
	}
	@Test 
	public void enterMarks() throws Exception {
		clickOnsybjectTopic();
		common.rowCountEnterMarks("examShow", "id('examShow')/tbody/tr", driver);
		driver.findElement(By.xpath(".//*[@id='main_block']/div/div/form/fieldset/p/input")).click();
		String success = driver.findElement(By.cssSelector("ul.success")).getText();
		System.out.println(success);
		verifyTextPresent(success, "EXAM SCORES UPDATED");
	}
	
}
