package com.ezzie.enoch.employee_grades;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import com.ezzie.enoch.employee_category.Common;
import com.ezzie.enoch.infrastructure.SeleniumBaseTest;

public class EmployeeGrade extends SeleniumBaseTest {
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
	 public void emptyGradeName() throws Exception {
	 driver.findElement(By.linkText("HR Settings")).click();
	 driver.findElement(By.linkText("Employee Grade")).click();
	 driver.findElement(By.id("employee_grade_name")).clear();
	 driver.findElement(By.id("create_grade")).click();
	 String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
	 verifyTextPresent(warning, "PLEASE ENTER GRADE NAME");
	 }
	 
	 @Test
	 public void emptyGradePriority() throws Exception {
		 driver.findElement(By.linkText("HR Settings")).click();
		 driver.findElement(By.linkText("Employee Grade")).click();
		 driver.findElement(By.id("employee_grade_name")).clear();
		 driver.findElement(By.id("employee_grade_name")).sendKeys(common.randomstring());
		 driver.findElement(By.id("create_grade")).click();
		 String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		 verifyTextPresent(warning, "PLEASE ENTER PRIORITY");
	 }
	 
	 
	 @Test
	 public void checkSpecialCharacterInName() throws Exception {
		 driver.findElement(By.linkText("HR Settings")).click();
		 driver.findElement(By.linkText("Employee Grade")).click();
		 driver.findElement(By.id("employee_grade_name")).clear();
		 driver.findElement(By.id("employee_grade_name")).sendKeys(createSpecialChars(5));
		 driver.findElement(By.id("employee_grade_priority")).clear();
		 driver.findElement(By.id("employee_grade_priority")).sendKeys("11");
		 driver.findElement(By.id("create_grade")).click();
		 String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		 verifyTextPresent(warning, "PLEASE ENTER CHARACTERS FOR NAME");
	}
	 
	 @Test
	 public void checkNumericValueInPriority() throws Exception {
		 driver.findElement(By.linkText("HR Settings")).click();
		 driver.findElement(By.linkText("Employee Grade")).click();
		 driver.findElement(By.id("employee_grade_name")).clear();
		 driver.findElement(By.id("employee_grade_name")).sendKeys("New Grade");
		 driver.findElement(By.id("employee_grade_priority")).clear();
		 driver.findElement(By.id("employee_grade_priority")).sendKeys(common.randomstring());
		 driver.findElement(By.id("create_grade")).click();
		 String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		 verifyTextPresent(warning, "PLEASE ENTER NUMERIC VALUE IN PRIORITY");
	 }
	 
	 @Test
	 public void checkCharacterLengthInName() throws Exception {
		 driver.findElement(By.linkText("HR Settings")).click();
		 driver.findElement(By.linkText("Employee Grade")).click();
		 driver.findElement(By.id("employee_grade_name")).clear();
		 driver.findElement(By.id("employee_grade_name")).sendKeys(createString(50));
		 driver.findElement(By.id("employee_grade_priority")).clear();
		 driver.findElement(By.id("employee_grade_priority")).sendKeys("123456");
		 driver.findElement(By.id("create_grade")).click();
		 String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		 verifyTextPresent(warning, "YOU CAN NOT ENTER MORE THAN 25 CHARACTERS FOR NAME");
	 }
	 
	 @Test
	 public void checkCharacterLengthInPriority() throws Exception {
		 driver.findElement(By.linkText("HR Settings")).click();
		 driver.findElement(By.linkText("Employee Grade")).click();
		 driver.findElement(By.id("employee_grade_name")).clear();
		 driver.findElement(By.id("employee_grade_name")).sendKeys(common.randomstring());
		 driver.findElement(By.id("employee_grade_priority")).clear();
		 driver.findElement(By.id("employee_grade_priority")).sendKeys(createNumber(50));
		 driver.findElement(By.id("create_grade")).click();
		 String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		 verifyTextPresent(warning, "YOU CAN NOT ENTER MORE THAN 25 CHARACTERS FOR PRIORITY");
	 }
	
	 @Test
	 public void checkMaxPeriodPerDay() throws Exception {
		 driver.findElement(By.linkText("HR Settings")).click();
		 driver.findElement(By.linkText("Employee Grade")).click();
		 driver.findElement(By.id("employee_grade_name")).clear();
		 driver.findElement(By.id("employee_grade_name")).sendKeys(common.randomstring());
		 driver.findElement(By.id("employee_grade_priority")).clear();
		 driver.findElement(By.id("employee_grade_priority")).sendKeys(Integer.toString(common.randomInteger(100)));
		 driver.findElement(By.id("employee_grade_max_hours_day")).clear();
		 driver.findElement(By.id("employee_grade_max_hours_day")).sendKeys(createNumber(20));
		 driver.findElement(By.id("create_grade")).click();
		 String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		 verifyTextPresent(warning,	 "PLEASE ENTER VALID MAX PERIOD PER DAY (MAXIMUM 3 DIGIT)");
	 }
	
	 @Test
	 public void checkMaxPeriodPerWeek() throws Exception {
		 driver.findElement(By.linkText("HR Settings")).click();
		 driver.findElement(By.linkText("Employee Grade")).click();
		 driver.findElement(By.id("employee_grade_name")).clear();
		 driver.findElement(By.id("employee_grade_name")).sendKeys(common.randomstring());
		 driver.findElement(By.id("employee_grade_priority")).clear();
		 driver.findElement(By.id("employee_grade_priority")).sendKeys(Integer.toString(common.randomInteger(100)));
		 driver.findElement(By.id("employee_grade_max_hours_week")).clear();
		 driver.findElement(By.id("employee_grade_max_hours_week")).sendKeys(createNumber(20));
		 driver.findElement(By.id("create_grade")).click();
		 String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		 verifyTextPresent(warning,	 "PLEASE ENTER VALID MAX PERIOD PER WEEK (MAXIMUM 3 DIGIT)");
	 }
	 
	 @Test
	 public void checkNumericValueInMaxHoursDay() throws Exception {
		 driver.findElement(By.linkText("HR Settings")).click();
		 driver.findElement(By.linkText("Employee Grade")).click();
		 driver.findElement(By.id("employee_grade_name")).clear();
		 driver.findElement(By.id("employee_grade_name")).sendKeys(common.randomstring());
		 driver.findElement(By.id("employee_grade_priority")).clear();
		 driver.findElement(By.id("employee_grade_priority")).sendKeys(createNumber(2));
		 driver.findElement(By.id("employee_grade_max_hours_day")).clear();
		 driver.findElement(By.id("employee_grade_max_hours_day")).sendKeys(common.randomstring());
		 driver.findElement(By.id("employee_grade_max_hours_week")).clear();
		 driver.findElement(By.id("employee_grade_max_hours_week")).sendKeys("5");
		 driver.findElement(By.id("create_grade")).click();
		 String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		 verifyTextPresent(warning, "PLEASE ENTER NUMERIC VALUE IN MAX PERIODS PER DAY");
	 }
	 
	 @Test
	 public void checkNumericValueInMaxHoursWeek() throws Exception {
		 driver.findElement(By.linkText("HR Settings")).click();
		 driver.findElement(By.linkText("Employee Grade")).click();
		 driver.findElement(By.id("employee_grade_name")).clear();
		 driver.findElement(By.id("employee_grade_name")).sendKeys("ghgdeeg");
		 driver.findElement(By.id("employee_grade_priority")).clear();
		 driver.findElement(By.id("employee_grade_priority")).sendKeys(createNumber(5));
		 driver.findElement(By.id("employee_grade_max_hours_day")).clear();
		 driver.findElement(By.id("employee_grade_max_hours_day")).sendKeys("5");
		 driver.findElement(By.id("employee_grade_max_hours_week")).clear();
		 driver.findElement(By.id("employee_grade_max_hours_week")).sendKeys(createString(1));
		 driver.findElement(By.id("create_grade")).click();
		 String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		 verifyTextPresent(warning,	 "PLEASE ENTER NUMERIC VALUE IN MAX PERIODS PER WEEK");
	 }
	
	 @Test
	 public void editEmployeeGrade() throws Exception {
		 driver.findElement(By.linkText("HR Settings")).click();
		 driver.findElement(By.linkText("Employee Grade")).click();
		 String id = common.rowCountCheckOfDataTable("active-table", "id('active-table')/tbody/tr", "td", 6,"update-grade-href","id",driver);
		 driver.findElement(By.id(id)).click();
		 driver.findElement(By.id("employee_grade_name")).clear();
		 driver.findElement(By.id("employee_grade_name")).sendKeys(common.randomstring());
		 driver.findElement(By.id("update_grade")).click();
		 String class_name = common.returnClassName(driver);
		 String message = driver.findElement(By.className(class_name)).getText();
		 verifyTextPresent(message,"GRADE WAS UPDATED SUCCESSFULLY.");
	}
	 
	 @Test
	 public void editEmployeeGradePriority() throws Exception {
		 driver.findElement(By.linkText("HR Settings")).click();
		 driver.findElement(By.linkText("Employee Grade")).click();
		 String id = common.rowCountCheckOfDataTable("active-table", "id('active-table')/tbody/tr", "td", 6,"update-grade-href","id",driver);
		 driver.findElement(By.id(id)).click();
		 String result = common.returnIdValue(id);
		 final int priorityValue = common.randomInteger(100);
		 driver.findElement(By.id("employee_grade_name")).sendKeys("AJ");
		 driver.findElement(By.id("employee_grade_priority")).clear();
		 driver.findElement(By.id("employee_grade_priority")).sendKeys(Integer.toString(priorityValue));
		 driver.findElement(By.id("update_grade")).click();
		 String success = driver.findElement(By.cssSelector("ul.success")).getText();
		 Thread.sleep(2000);
		 String afterUpdatePriortyValue = driver.findElement(By.id("grade_priority_"+result)).getText();
		 assertTrue(common.verifyEquality(Integer.toString(priorityValue), afterUpdatePriortyValue));
		 verifyTextPresent(success, "GRADE WAS UPDATED SUCCESSFULLY.");
	 }

	@Test
	public void updateFromActiveToInActive() throws Exception {
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Employee Grade")).click();
		String id = common.rowCountCheckOfDataTable("active-table", "id('active-table')/tbody/tr", "td", 6,"update-grade-href","id",driver);
		driver.findElement(By.id(id)).click();
		driver.findElement(By.id("employee_grade_status_false")).click();
		int inActiveRowCountBeforeChange = common.returnTableRowCount("inactive-table", "id('inactive-table')/tbody/tr", driver);
		driver.findElement(By.id("update_grade")).click();
		Thread.sleep(5000);
		int inActiveRowCountAfterChange = common.returnTableRowCount("inactive-table", "id('inactive-table')/tbody/tr", driver);
		String success = driver.findElement(By.cssSelector("ul.success")).getText();
		Thread.sleep(2000);
		verifyTextPresent(success, "GRADE WAS UPDATED SUCCESSFULLY.");
		assertFalse(common.verifyEquality(Integer.toString(inActiveRowCountBeforeChange), Integer.toString(inActiveRowCountAfterChange)));
	}
	
	@Test
	public void deleteEmployeeGrade() throws Exception {
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Employee Grade")).click();
		String id = common.rowCountCheckOfDataTable("active-table", "id('active-table')/tbody/tr", "td", 6,"delete-grade-href","id",driver);
		driver.findElement(By.id(id)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='modal']/div/div[1]/div[2]/button[1]")).click(); 
		String class_name = common.returnClassName(driver);
		String message = driver.findElement(By.className(class_name)).getText();
		 if (class_name =="success") {
			verifyTextPresent(message,"GRADE WAS DELETED SUCCESSFULLY.");
		 }else{
		    assertTrue(common.verifyDependancyPresence(class_name, message , driver));
		 }
	}

	 @Test
	 public void checkBackButton() throws Exception{
	    driver.findElement(By.linkText("HR Settings")).click();
	    Thread.sleep(2000);
	    String currentUrl = driver.getCurrentUrl().toString();
	    driver.findElement(By.linkText("Employee Grade")).click(); 
	    driver.findElement(By.xpath(".//*[@id='grades-form']/div[2]/ul/li/a/img")).click();
	    Thread.sleep(2000);
	    String backUrl = driver.getCurrentUrl().toString();
	    assertTrue(common.verifyEquality(currentUrl, backUrl));
	 }
	
	 @Test
	 public void duplicateNameVerification() throws Exception{
	    driver.findElement(By.linkText("HR Settings")).click();
	    driver.findElement(By.linkText("Employee Grade")).click();
	    String id = common.rowCountCheckOfDataTable("active-table", "id('active-table')/tbody/tr", "td", 6,"update-grade-href","id",driver);
	    String Idvalue = common.returnIdValue(id);
	    String name = driver.findElement(By.id("grade_name_"+Idvalue)).getText();
	    driver.findElement(By.id("employee_grade_name")).clear();
	    driver.findElement(By.id("employee_grade_name")).sendKeys(name);
	    driver.findElement(By.id("employee_grade_priority")).clear();
	    driver.findElement(By.id("employee_grade_priority")).sendKeys("1254656");
		driver.findElement(By.id("create_grade")).click();
		Thread.sleep(2000);
		String class_name = common.returnClassName(driver);
		String error = driver.findElement(By.className(class_name)).getText();
		 assertTrue(common.verifyDependancyPresence(class_name, error, driver));
	 }

	
}
