package com.ezzie.enoch.admission_additional_detail;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.ezzie.enoch.employee_category.Common;
import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class AdmissionAdditionalDetail extends LoggedInUserTest {
	Common common = new Common();
	
	@Before
	public void setUp() throws Exception {
		  super.setUp();
	}
	@After
	public void teardown() throws Exception{
//		super.tearDown();
	}
	 @Test
	 public void emptyAddittionaledField() throws Exception{
			driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
			driver.findElement(By.linkText("Admission Additional Detail")).click();
			driver.findElement(By.id("student_additional_field_name")).clear();
			driver.findElement(By.id("create_student_additional_field")).click();
			String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
			System.out.println(warning);
			verifyTextPresent(warning, "PLEASE ENTER ADDITIONAL FIELD NAME");
	 }
	   @Test
	 public void enterNumericInAdditionalNameField() throws Exception{
			driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
			driver.findElement(By.linkText("Admission Additional Detail")).click();
			driver.findElement(By.id("student_additional_field_name")).clear();
			driver.findElement(By.id("student_additional_field_name")).sendKeys(createNumber(7));
			driver.findElement(By.id("create_student_additional_field")).click();
			String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
			System.out.println(warning);
			verifyTextPresent(warning, "PLEASE ENTER CHARACTERS FOR NAME");
	 }
	
	   @Test
		 public void enterSpecialCharacterInAdditionalNameField() throws Exception{
				driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
				driver.findElement(By.linkText("Admission Additional Detail")).click();
				driver.findElement(By.id("student_additional_field_name")).clear();
				driver.findElement(By.id("student_additional_field_name")).sendKeys(createSpecialChars(6));
				driver.findElement(By.id("create_student_additional_field")).click();
				String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
				System.out.println(warning);
				verifyTextPresent(warning, "SPECIAL CHARACTER ARE NOT ALLOWED IN ADDITIONAL FIELD NAME");
		 }
	   @Test
		 public void checkMaximLengthInAdditionalNameField() throws Exception{
				driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
				driver.findElement(By.linkText("Admission Additional Detail")).click();
				driver.findElement(By.id("student_additional_field_name")).clear();
				driver.findElement(By.id("student_additional_field_name")).sendKeys(createString(20));
				driver.findElement(By.id("create_student_additional_field")).click();
				String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
				System.out.println(warning);
				verifyTextPresent(warning, "YOU CAN NOT ENTER MORE THAN 50 CHARACTER IN FIELD");
		 }
	
	   @Test
		 public void createAdditionalNameField() throws Exception{
				driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
				driver.findElement(By.linkText("Admission Additional Detail")).click();
				driver.findElement(By.id("student_additional_field_name")).clear();
				driver.findElement(By.id("student_additional_field_name")).sendKeys(common.randomstring());
				driver.findElement(By.id("create_student_additional_field")).click();
				String success = driver.findElement(By.cssSelector("ul.success")).getText();
				System.out.println(success);
				verifyTextPresent(success, "STUDENT ADDITIONAL FIELD WAS SUCCESSFULLY CREATED.");
		 }
	   
	    @Test
		  public void createInactiveStudentCategory() throws Exception{
		      driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
			  driver.findElement(By.linkText("Admission Additional Detail")).click();
			  driver.findElement(By.id("student_additional_field_name")).clear();
			  driver.findElement(By.id("student_additional_field_name")).sendKeys(common.randomstring());
			  driver.findElement(By.id("student_additional_field_status_false")).click();
			  driver.findElement(By.id("create_student_additional_field")).click();
			  String success = driver.findElement(By.cssSelector("ul.success")).getText();
			  System.out.println(success);
			  verifyTextPresent(success, "STUDENT ADDITIONAL FIELD WAS SUCCESSFULLY CREATED.");
				 }
	    @Test
		  public void activeToInactiveStudentCategory() throws Exception{
		      driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
			  driver.findElement(By.linkText("Admission Additional Detail")).click();
			  String dd = common.rowCountCheckOfDataTable("active-table", "id('active-table')/tbody/tr", "td", 3,"update-additionalfield-href","id",driver);
			  driver.findElement(By.id(dd)).click();
			  driver.findElement(By.id("student_additional_field_status_false")).click();
			  driver.findElement(By.id("update_student_additional_field")).click();
			  String success = driver.findElement(By.cssSelector("ul.success")).getText();
			  System.out.println(success);
			  verifyTextPresent(success, "STUDENT ADDITIONAL FIELD WAS SUCCESSFULLY UPDATED.");
				 }
	    
	    
	    @Test
		  public void editAndUpdateStudentCategory() throws Exception{
		      driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
			  driver.findElement(By.linkText("Admission Additional Detail")).click();
			  String dd = common.rowCountCheckOfDataTable("active-table", "id('active-table')/tbody/tr", "td", 3,"update-additionalfield-href","id",driver);
			  driver.findElement(By.id(dd)).click();
			  driver.findElement(By.id("student_additional_field_name")).sendKeys(common.randomstring());
			  driver.findElement(By.id("update_student_additional_field")).click();
			  String success = driver.findElement(By.cssSelector("ul.success")).getText();
			  System.out.println(success);
			  verifyTextPresent(success, "STUDENT ADDITIONAL FIELD WAS SUCCESSFULLY UPDATED.");
				 }
	    @Test
		  public void deleteStudentCategory() throws Exception{
		      driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
			  driver.findElement(By.linkText("Admission Additional Detail")).click();
			  String dd = common.rowCountCheckOfDataTable("active-table", "id('active-table')/tbody/tr", "td", 3,"delete-additionalfield-href","id",driver);
			  driver.findElement(By.id(dd)).click();
			  driver.findElement(By.xpath(".//*[@id='modal']/div/div[1]/div[2]/button[1]")).click();
			  String success = driver.findElement(By.cssSelector("ul.success")).getText();
			  System.out.println(success);
			  verifyTextPresent(success, "ADDITIONAL FIELD DELETED SUCCESSFULLY.");
				
	    }
	    @Test
		  public void checkBackButton() throws Exception{
		      driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
		      Thread.sleep(2000);
		      String currentUrl = driver.getCurrentUrl().toString();
		      System.out.println(currentUrl);
		      driver.findElement(By.linkText("Admission Additional Detail")).click();
		      Thread.sleep(2000);
	          driver.findElement(By.xpath(".//*[@id='studentsfield-form']/div[2]/ul/li/a/img")).click();
	          Thread.sleep(2000);
	          String backUrl = driver.getCurrentUrl().toString();
			  System.out.println(backUrl);
		      assertTrue(common.verifyEquality(currentUrl, backUrl));
		      }
	    @Test
	 	public void duplicateNameVerify() throws Exception{
	    	 driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
	    	 driver.findElement(By.linkText("Student Category")).click();
	 		 Thread.sleep(2000);
	 		 String id = common.rowCountCheckOfDataTable("active-table", "id('active-table')/tbody/tr", "td", 3,"update-additionalfield-href","id",driver);
	 		 String result  = common.returnIdValue(id);   
	 		 System.out.println(result);
	 		 String duplicateName = driver.findElement(By.id("student_additional_field_name"+result)).getText();
	 		 System.out.println(duplicateName);
	 	   	 driver.findElement(By.id("student_additional_field_name")).clear();
	 		 driver.findElement(By.id("student_additional_field_name")).sendKeys(duplicateName);
	 		 Thread.sleep(2000);
	 		 driver.findElement(By.id("create_student_additional_field")).click();
	 		 String message = driver.findElement(By.cssSelector("ul.error")).getText();
	 		 assertTrue(common.verifyEquality(message, "THERE ARE ERRORS WHILE PROCESSING YOUR REQUEST\n"+
	 												    "NAME : HAS ALREADY BEEN TAKEN"));
	 		 }
	    @Test
	     public void clearButtonOnAdditionalDetail() throws Exception {
	    	 driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
	         driver.findElement(By.linkText("Student Category")).click();
	         String id = common.rowCountCheckOfDataTable("active-table", "id('active-table')/tbody/tr", "td", 3,"update-studentcategoryfield-href","id",driver);
		     Thread.sleep(2000);
			 String attr1 = driver.findElement(By.id("create_student_additional_field")).getAttribute("disabled");
			 driver.findElement(By.id(id)).click();
			 String attr2 = driver.findElement(By.id("create_student_additional_field")).getAttribute("disabled");
			 assertFalse(common.verifyEquality(attr1,attr2));
			 driver.findElement(By.id("reset_student_additional_field")).click();
			 String attr3 = driver.findElement(By.id("create_student_additional_field")).getAttribute("disabled");
			 assertTrue(common.verifyEquality(attr1,attr3));
		 }
	    
}
