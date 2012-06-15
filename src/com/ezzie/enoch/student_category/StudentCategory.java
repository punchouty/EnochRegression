package com.ezzie.enoch.student_category;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.internal.selenesedriver.GetCurrentUrl;

import com.ezzie.enoch.employee_category.Common;
import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class StudentCategory extends LoggedInUserTest {
	Common common = new Common();
	@Before
	public void setUp() throws Exception {
		  super.setUp();
	}
	@After
	public void tearDown() throws Exception {
//		super.tearDown();
	}

	@Test
	public void emptyStudentCtegoryName() throws Exception{
	driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
	driver.findElement(By.linkText("Student Category")).click();
	driver.findElement(By.id("student_category_name")).clear();
	driver.findElement(By.id("create_student_category_field")).click();
	String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
	System.out.println(warning);
	verifyTextPresent(warning, "PLEASE ENTER CATEGORY NAME");
}
	
	@Test
	public void enterSpecialCharacterInName() throws Exception{
	driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
	driver.findElement(By.linkText("Student Category")).click();
	driver.findElement(By.id("student_category_name")).clear();
	driver.findElement(By.id("student_category_name")).sendKeys(createSpecialChars(6));
    driver.findElement(By.id("create_student_category_field")).click();
	String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
	System.out.println(warning);
	verifyTextPresent(warning, "SPECIAL CHARACTER ARE NOT ALLOWED IN CATEGORY NAME");
}
	@Test
	public void enterNumericValueInName() throws Exception{
	driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
	driver.findElement(By.linkText("Student Category")).click();
	driver.findElement(By.id("student_category_name")).clear();
	driver.findElement(By.id("student_category_name")).sendKeys(createNumber(9));
    driver.findElement(By.id("create_student_category_field")).click();
	String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
	System.out.println(warning);
	verifyTextPresent(warning, "PLEASE ENTER ONLY CHARACTERS FOR NAME");
}
     @Test
     public void checkmaximumlengthInName() throws Exception {
    	 driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
    		driver.findElement(By.linkText("Student Category")).click();
    		driver.findElement(By.id("student_category_name")).clear();
    		driver.findElement(By.id("student_category_name")).sendKeys(createString(20));
    	    driver.findElement(By.id("create_student_category_field")).click();
    		String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
    		System.out.println(warning);
    		verifyTextPresent(warning, "MAXIMUM CHARACTER LENGTH FOR NAME SHOULD 25");
     }
	
     @Test
     public void createStudentCategory() throws Exception {
    	 driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
    		driver.findElement(By.linkText("Student Category")).click();
    		driver.findElement(By.id("student_category_name")).clear();
    		driver.findElement(By.id("student_category_name")).sendKeys(common.randomstring());
    	    driver.findElement(By.id("create_student_category_field")).click();
    		String success = driver.findElement(By.cssSelector("ul.success")).getText();
    		System.out.println(success);
    		verifyTextPresent(success, "STUDENT CATEGORY IS SUCCESSFULLY CREATED.");
    }
     
     @Test
     public void createInactiveStudentCtagory() throws Exception {
    	 driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
         driver.findElement(By.linkText("Student Category")).click();
 		 driver.findElement(By.id("student_category_name")).clear();
		 driver.findElement(By.id("student_category_name")).sendKeys(common.randomstring());
		 driver.findElement(By.id("student_category_is_deleted_true")).click();
		 driver.findElement(By.id("create_student_category_field")).click();
		 String success = driver.findElement(By.cssSelector("ul.success")).getText();
 		System.out.println(success);
 		verifyTextPresent(success, "STUDENT CATEGORY IS SUCCESSFULLY CREATED.");
		  }
     
     @Test
     public void activeToInactiveStudentCtagory() throws Exception {
    	 driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
         driver.findElement(By.linkText("Student Category")).click();
         Thread.sleep(2000);
 		String dd =common.rowCountCheckOfDataTable("active-table", "id('active-table')/tbody/tr", "td", 3,"update-studentcategoryfield-href","id",driver);
 		Thread.sleep(2000);
 		driver.findElement(By.id(dd)).click();
		 driver.findElement(By.id("student_category_is_deleted_true")).click();
		 driver.findElement(By.id("update_student_category_field")).click();
		 String success = driver.findElement(By.cssSelector("ul.success")).getText();
 		System.out.println(success);
 		verifyTextPresent(success, "STUDENT CATEGORY IS SUCCESSFULLY UPDATED.");
     }
     
     @Test
     public void deleteStudentCtagory() throws Exception {
        driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
        driver.findElement(By.linkText("Student Category")).click();
        Thread.sleep(2000);
 		String dd =common.rowCountCheckOfDataTable("active-table", "id('active-table')/tbody/tr", "td", 3,"delete-studentcategoryfield-href","id",driver);
 		Thread.sleep(2000);
 		driver.findElement(By.id(dd)).click();
		driver.findElement(By.xpath(".//*[@id='modal']/div/div[1]/div[2]/button[1]")).click();
		String class_name= common.returnClassName(driver);
		String message = driver.findElement(By.className(class_name)).getText();
		if (class_name == "success"){
			verifyTextPresent(message, "STUDENT CATEGORY IS DELETED SUCCESSFULLY.");
			}else{
			assertTrue(common.verifyDependancyPresence(class_name,message,driver));
		}
    }
     @Test
     public void editAndUpdateStudentCtagory() throws Exception {
    	 driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
         driver.findElement(By.linkText("Student Category")).click();
         Thread.sleep(2000);
 		 String dd =common.rowCountCheckOfDataTable("active-table", "id('active-table')/tbody/tr", "td", 3,"update-studentcategoryfield-href","id",driver);
 		 Thread.sleep(2000);
 		 driver.findElement(By.id(dd)).click();
 		 driver.findElement(By.id("student_category_name")).sendKeys(common.randomstring());
 		 driver.findElement(By.id("update_student_category_field")).click();
		 String success = driver.findElement(By.cssSelector("ul.success")).getText();
 		 System.out.println(success);
 		 verifyTextPresent(success, "EMPLOYEE CATEGORY IS DELETED SUCCESSFULLY.");
    }
     @Test
     public void clearButtonOnStudentCtagory() throws Exception {
    	 driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
         driver.findElement(By.linkText("Student Category")).click();
         String id = common.rowCountCheckOfDataTable("active-table", "id('active-table')/tbody/tr", "td", 3,"update-studentcategoryfield-href","id",driver);
	     Thread.sleep(2000);
		 String attr1 = driver.findElement(By.id("create_student_category_field")).getAttribute("disabled");
		 driver.findElement(By.id(id)).click();
		 String attr2 = driver.findElement(By.id("create_student_category_field")).getAttribute("disabled");
		 assertFalse(common.verifyEquality(attr1,attr2));
		 driver.findElement(By.id("reset_student_category_field")).click();
		 String attr3 = driver.findElement(By.id("create_student_category_field")).getAttribute("disabled");
		 assertTrue(common.verifyEquality(attr1,attr3));
	 }
     
     @Test
     public void checkBackButtonOnStudentCtagory() throws Exception {
    	 driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
          driver.findElement(By.linkText("Student Category")).click();
         String currentUrl = driver.getCurrentUrl().toString();
         driver.findElement(By.xpath(".//*[@id='studentcategory-form']/div[2]/ul/li/a/img")).click();
         Thread.sleep(2000);
         String backUrl = driver.getCurrentUrl().toString();
 		 assertTrue(common.verifyEquality(currentUrl, backUrl));
   }
     
     @Test
 	public void duplicateNameVerify() throws Exception{
    	 driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
    	 driver.findElement(By.linkText("Student Category")).click();
 		 Thread.sleep(2000);
 		 String id = common.rowCountCheckOfDataTable("active-table", "id('active-table')/tbody/tr", "td", 3,"update-studentcategoryfield-href","id",driver);
 		 String result  = common.returnIdValue(id);   
 		 System.out.println(result);
 		 String duplicateName = driver.findElement(By.id("student_category_name_"+result)).getText();
 		 System.out.println(duplicateName);
 	   	 driver.findElement(By.id("student_category_name")).clear();
 		 driver.findElement(By.id("student_category_name")).sendKeys(duplicateName);
 		 Thread.sleep(2000);
 		 driver.findElement(By.id("create_student_category_field")).click();
 		 String message = driver.findElement(By.cssSelector("ul.error")).getText();
 		 assertTrue(common.verifyEquality(message, "THERE ARE ERRORS WHILE PROCESSING YOUR REQUEST\n"+
 												    "NAME : HAS ALREADY BEEN TAKEN"));
 		 }
     
     
}

