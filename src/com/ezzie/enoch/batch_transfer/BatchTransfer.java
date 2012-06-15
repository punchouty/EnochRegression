package com.ezzie.enoch.batch_transfer;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.ezzie.enoch.employee_category.Common;
import com.ezzie.enoch.infrastructure.SeleniumBaseTest;

public class BatchTransfer extends SeleniumBaseTest{
	Common common = new Common();
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		driver.get(baseUrl + "/signin");
		driver.findElement(By.id("session_username")).clear();
		driver.findElement(By.id("session_username")).sendKeys("E0001");
		driver.findElement(By.id("session_password")).clear();
		driver.findElement(By.id("session_password")).sendKeys("password");
		driver.findElement(By.name("commit")).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.findElement(By.linkText("LOGOUT")).click();	
		super.tearDown();
	}
	
	 @Test
	 public void checkTableIdPresence() throws Exception {
		 driver.findElement(By.linkText("Batch Transfers")).click();
		 Select comboBox = new Select(driver.findElement(By.id("course_search_course_id")));
		 comboBox.selectByIndex(1);
		 Thread.sleep(2000);
		 Select comboBox1 = new Select(driver.findElement(By.id("student_batch_id")));
		 comboBox1.selectByIndex(1);
		 assertTrue(isElementPresent(By.className("batchtransfertable")));
	 }
	 
	 @Test
	 public void checkMultipleCheckBoxSelectionWithSingleClick() throws Exception{
		checkTableIdPresence();
		Thread.sleep(2000);
		driver.findElement(By.id("select-all")).click();
		String checkedCheckBoxCount = checkedCheckBoxCount("mysortable", "id('mysortable')/tbody/tr", "td", 1,"transfer_students_","id",driver);
		Thread.sleep(2000);
		int rowCount =common.returnTableRowCount("mysortable", "id('mysortable')/tbody/tr",driver);
		Thread.sleep(2000);
		assertTrue(common.verifyEquality(checkedCheckBoxCount, Integer.toString(rowCount)));
	 }
	 
	 @Test
	 public void checkNoCheckBoxIsSelectedWithDoubleClick() throws Exception{
		checkTableIdPresence();
		driver.findElement(By.id("select-all")).click();
		String checkedCheckBoxCount = checkedCheckBoxCount("mysortable", "id('mysortable')/tbody/tr", "td", 1,"transfer_students_","id",driver);
		Thread.sleep(2000);
		driver.findElement(By.id("select-all")).click();
		String checkedCheckBoxCountAfterClick = checkedCheckBoxCount("mysortable", "id('mysortable')/tbody/tr", "td", 1,"transfer_students_","id",driver);
		assertFalse(common.verifyEquality(checkedCheckBoxCount, checkedCheckBoxCountAfterClick));
	 }

	 @Test
	 public void checkModalBoxAppearance() throws Exception{
		checkTableIdPresence();
		randomlyCheckCheckBox("mysortable", "id('mysortable')/tbody/tr", "td", 1,"transfer_students_","id",driver);
		Thread.sleep(2000);
		driver.findElement(By.id("transfer")).click();
		Thread.sleep(2000);
		assertTrue(common.isElementPresents(By.id("modal"),driver));
	 }
	
	 @Test
	 public void changeStudentBatchFromBatchTransfer() throws Exception{
			checkTableIdPresence();
			randomlyCheckCheckBox("mysortable", "id('mysortable')/tbody/tr", "td", 1,"transfer_students_","id",driver);
			Thread.sleep(2000);
			driver.findElement(By.id("transfer")).click();
			Thread.sleep(2000);
			new Select(driver.findElement(By.xpath("(//select[@id='courses_search_course_id'])[2]"))).selectByVisibleText("Nursery");
			Thread.sleep(2000);
			new Select(driver.findElement(By.xpath("(//select[@id='student_batch_transfer_batch_id'])[2]"))).selectByVisibleText("Nursery - A-2012");
		    driver.findElement(By.xpath(".//*[@id='modal']/div/div[1]/div[2]/button[2]")).click();
		    Thread.sleep(2000);
		    String success = driver.findElement(By.className("success")).getText();
		    verifyTextPresent(success, "STUDENTS TRANSFERRED SUCCESSFULLY");
	 }
	
	 @Test
	 public void checkAtleastOneStudentSelected() throws Exception{
			checkTableIdPresence();
			driver.findElement(By.id("transfer")).click();
		    String warning = driver.findElement(By.className("warning")).getText();
		    verifyTextPresent(warning, "PLEASE SELECT ATLEAST ONE STUDENT");
	 }
	 
	 @Test
	 public void checkBatchAndCoursePresence() throws Exception{
			checkTableIdPresence();
			randomlyCheckCheckBox("mysortable", "id('mysortable')/tbody/tr", "td", 1,"transfer_students_","id",driver);
			Thread.sleep(2000);
			driver.findElement(By.id("transfer")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(".//*[@id='modal']/div/div[1]/div[2]/button[2]")).click();
		    String warning = driver.findElement(By.className("warning")).getText();
		    verifyTextPresent(warning, "PLEASE SELECT THE COURSE AND BATCH");
	 }
	 
	 @Test
	 public void checkEmptyStudentTable() throws Exception{
			checkTableIdPresence();
			new Select(driver.findElement(By.name("mysortable_length"))).selectByVisibleText("100");
			driver.findElement(By.id("select-all")).click();
			int tableData1 =  common.returnTableRowCount("mysortable","id('mysortable')/tbody/tr", driver);
			driver.findElement(By.id("transfer")).click();
			Thread.sleep(2000);
			new Select(driver.findElement(By.xpath("(//select[@id='courses_search_course_id'])[2]"))).selectByVisibleText("Nursery");
			Thread.sleep(2000);
			new Select(driver.findElement(By.xpath("(//select[@id='student_batch_transfer_batch_id'])[2]"))).selectByVisibleText("Nursery - A-2012");
		    driver.findElement(By.xpath(".//*[@id='modal']/div/div[1]/div[2]/button[2]")).click();
		    Thread.sleep(2000);
		    int tableData2 =  common.returnTableRowCount("mysortable","id('mysortable')/tbody/tr", driver);
		    Thread.sleep(2000);
		    String success = driver.findElement(By.className("success")).getText();
		    verifyTextPresent(success, "STUDENTS TRANSFERRED SUCCESSFULLY");
		    assertFalse(common.verifyEquality(Integer.toString(tableData2), Integer.toString(tableData1)));
	 }
	 
	 
	 @Test
	 public void checkStudentProfileLink() throws Exception{
			checkTableIdPresence();
			@SuppressWarnings("unchecked")
			Map<String,String> hrefValue = common.returnHrefValue("mysortable","id('mysortable')/tbody/tr", "td", 4, "a", "href", driver);
			  Object[] objects = hrefValue.entrySet().toArray();
			  Random rn = new Random();
			  int n = rn.nextInt(hrefValue.size());
			  @SuppressWarnings("unchecked")
			  Map.Entry<String, String> entry = (java.util.Map.Entry<String, String>) objects[n];
			  String hrefLink = entry.getKey();
			  String actualName = entry.getValue();
			  driver.get(hrefLink);
	          Thread.sleep(1500);
			  String profileName = driver.findElement(By.id("changeStudentName")).getText();
	          assertTrue(common.verifyEquality(profileName, actualName));
	 }
	 
	 public String randomlyCheckCheckBox(String tableId,String tableRow,String tableTd, int tableCol,String idOrClass, String attr, WebDriver driver){
			WebElement table_element = driver.findElement(By.id(tableId));
			Set<String> set = new HashSet<String>();
	        List<WebElement> tr_collection = table_element.findElements(By.xpath(tableRow));   
	        int row_num,col_num;
	        row_num=1;
	        for(WebElement trElement : tr_collection){
	            List<WebElement> td_collection = trElement.findElements(By.xpath(tableTd));
	            col_num=1;
	            for(WebElement tdElement : td_collection){
	            	if(col_num == tableCol){
	            		WebElement checks = tdElement.findElement(By.id(idOrClass));
	            		Random rn = new Random();
	            		int n = rn.nextInt(2);
	            		if(n==1)
	            			checks.click();
	            		if(checks.getAttribute("checked") != null){
	            			set.add(tdElement.findElement(By.id(idOrClass)).getAttribute("value"));
	            		}	
	            	 }
	            	 col_num++;
	            }
	            row_num++;
	        }
	        return Integer.toString(set.size());
		}
	 
	 
	 public String checkedCheckBoxCount(String tableId,String tableRow,String tableTd, int tableCol,String idOrClass, String attr, WebDriver driver){
			WebElement table_element = driver.findElement(By.id(tableId));
			Set<String> set = new HashSet<String>();
	        List<WebElement> tr_collection = table_element.findElements(By.xpath(tableRow));   

	        int row_num,col_num;
	        row_num=1;
	        for(WebElement trElement : tr_collection){
	            List<WebElement> td_collection = trElement.findElements(By.xpath(tableTd));
	            col_num=1;
	            for(WebElement tdElement : td_collection){
	            	if(col_num == tableCol){
	            		WebElement checks = tdElement.findElement(By.id(idOrClass));
		            		if(checks.getAttribute("checked") != null){
		            			set.add(tdElement.findElement(By.id(idOrClass)).getAttribute("value"));
		            		}	
	            	 }
	            	 col_num++;
	            }
	            row_num++;
	        }
	        return Integer.toString(set.size());
		}
}
