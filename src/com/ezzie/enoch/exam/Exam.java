package com.ezzie.enoch.exam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.ezzie.enoch.employee_categories.Common;
import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class Exam extends LoggedInUserTest{
  Common common = new Common();
	@Before
	public void setUp() throws Exception{
		super.setUp();
	}
	
	@After
	public void tearDown() throws Exception{
//		super.tearDown();
	}
	
	public void checkLink() throws Exception{
		driver.findElement(By.linkText("Exams")).click();
		Select combo = new Select(driver.findElement(By.id("courses_name")));
		combo.selectByVisibleText("PreNursery");
		Thread.sleep(2000);
		Select combo1 = new Select(driver.findElement(By.id("exams_batch_id")));
		combo1.selectByVisibleText("Prep - A");
		driver.findElement(By.id("exacreate")).click();
		driver.findElement(By.xpath(".//*[@id='outer_bloc']/div/fieldset/p/input[1]")).click();
		driver.findElement(By.id("exam_option_name")).clear();
		driver.findElement(By.id("exam_option_name")).sendKeys(common.randomstring());
		driver.findElement(By.id("new_exam")).click();
		Thread.sleep(2000);
	}
	
//	@Test
	public void newExam() throws Exception{
	    checkLink();
		rowCountCheck();
		driver.findElement(By.id("exam_group_maximum_marks")).clear();
		driver.findElement(By.id("exam_group_maximum_marks")).sendKeys("50");
		driver.findElement(By.id("exam_group_minimum_marks")).clear();
		driver.findElement(By.id("exam_group_minimum_marks")).sendKeys("150");
		driver.findElement(By.id("create_exam")).click();
		String warning = driver.findElement(By.cssSelector("ul.error ul li")).getText();
		verifyTextPresent(warning, "EXAMS MINIMUM MARKS CAN'T BE MORE THAN MAX MARKS.");
	}
	
	@Test
	public void newExamCreate() throws Exception{
		checkLink();
		rowCountCheck();
		driver.findElement(By.id("exam_group_maximum_marks")).clear();
		driver.findElement(By.id("exam_group_maximum_marks")).sendKeys("50");
		driver.findElement(By.id("exam_group_minimum_marks")).clear();
		driver.findElement(By.id("exam_group_minimum_marks")).sendKeys("0");
		driver.findElement(By.id("create_exam")).click();
		String success = driver.findElement(By.cssSelector("ul.success")).getText();
		verifyTextPresent(success, "EXAM GROUP CREATED SUCCESSFULLY.");
	}
	
	public void rowCountCheck(){
		WebElement table_element = driver.findElement(By.id("exammf"));
        List<WebElement> tr_collection = table_element.findElements(By.xpath("id('exammf')/tbody/tr"));
//        String length = Integer.toString(tr_collection.size());
        String start_time ="";
        int row_num,col_num;
        row_num=1;
        for(WebElement trElement : tr_collection){
            List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
            col_num=1;
            for(WebElement tdElement : td_collection)
            {
             if(col_num == 6){  	
            	WebElement timeField1 = tdElement.findElement(By.id("marks_exam_start_time"));
            	String timevalue = timeField1.getAttribute("value");
            	String newdate = convertToDate(timevalue,timevalue).toString();
            	timeField1.clear();
            	timeField1.sendKeys(newdate);
            	start_time = timeField1.getAttribute("value");
             }
			 if(col_num == 7){
				WebElement timeField2 = tdElement.findElement(By.id("marks_exam_end_time"));
			 	String timevalue1 = timeField2.getAttribute("value");
			 	String newdate1 = convertToDate(timevalue1,start_time).toString();
			 	timeField2.clear();
			 	timeField2.sendKeys(newdate1);           
			 }
            	col_num++;
            }
            row_num++;
        }
	}
        

	
	@SuppressWarnings("deprecation")
	public String convertToDate(String dateString, String fieldvalue){
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date convertedDate = new Date();
	        try {
				convertedDate = dateFormat.parse(fieldvalue);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			int newTime = common.randomInteger(10);
				convertedDate.setHours(convertedDate.getHours()+newTime);
				
	        DateFormat dateFormatNeeded = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	        String convertedDate1 = dateFormatNeeded.format(convertedDate); 
	     return convertedDate1;
	}

}
