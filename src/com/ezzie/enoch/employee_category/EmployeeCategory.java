package com.ezzie.enoch.employee_category;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import com.ezzie.enoch.infrastructure.SeleniumBaseTest;

public class EmployeeCategory extends SeleniumBaseTest{
    Common common = new Common();
	@Before
	public void setup() throws Exception{
		super.setUp();
		driver.get(baseUrl + "/signin");
		driver.findElement(By.id("session_username")).clear();
		driver.findElement(By.id("session_username")).sendKeys("E0001");
		driver.findElement(By.id("session_password")).clear();
		driver.findElement(By.id("session_password")).sendKeys("password");
		driver.findElement(By.name("commit")).click();
	}
	
	@After
	public void teardown() throws Exception{
		driver.findElement(By.linkText("LOGOUT")).click();	
		super.tearDown();
	}
	
	@Test
	public void emptyCategoryName() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Employee Category")).click();
		driver.findElement(By.id("create_employee_category")).click();
		String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		assertTrue(common.verifyEquality(warning,"PLEASE ENTER CATEGORY NAME"));
	}
	
	@Test
	public void emptyCategoryPrefix() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Employee Category")).click();
		driver.findElement(By.id("employee_category_name")).clear();
		driver.findElement(By.id("employee_category_name")).sendKeys(common.randomstring());
		driver.findElement(By.id("employee_category_prefix")).clear();
		driver.findElement(By.id("create_employee_category")).click();
		String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		assertTrue(common.verifyEquality(warning,"PLEASE ENTER CATEGORY PREFIX"));
	}

	@Test
	public void createCategory() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Employee Category")).click();
		String name = common.randomstring();
		String prefixName = name.substring(0,4);
		driver.findElement(By.id("employee_category_name")).clear();
		driver.findElement(By.id("employee_category_name")).sendKeys(name);
		driver.findElement(By.id("employee_category_prefix")).clear();
		driver.findElement(By.id("employee_category_prefix")).sendKeys(prefixName);
		driver.findElement(By.id("create_employee_category")).click();
		String success = driver.findElement(By.cssSelector("ul.success")).getText();
		assertTrue(common.verifyEquality(success,"EMPLOYEE CATEGORY WAS SUCCESSFULLY CREATED."));
	}

	@Test
	public void createInactiveCategory() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Employee Category")).click();
		String name = common.randomstring();
		String prefixName = name.substring(0,4);
	    driver.findElement(By.id("employee_category_status_false")).click();
		driver.findElement(By.id("employee_category_name")).clear();
		driver.findElement(By.id("employee_category_name")).sendKeys(name);
		driver.findElement(By.id("employee_category_prefix")).clear();
		driver.findElement(By.id("employee_category_prefix")).sendKeys(prefixName);
		driver.findElement(By.id("create_employee_category")).click();
		String success = driver.findElement(By.cssSelector("ul.success")).getText();
		assertTrue(common.verifyEquality(success,"EMPLOYEE CATEGORY WAS SUCCESSFULLY CREATED."));
	}
	
	@Test
	public void editAndUpdateCategory() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Employee Category")).click();
		Thread.sleep(2000);
		String id = common.rowCountCheckOfDataTable("active-table", "id('active-table')/tbody/tr", "td", 3,"update-employee-category-master-href","id",driver);
		Thread.sleep(2000);
		driver.findElement(By.id(id)).click();
		String name = common.randomstring();
		String prefixName = name.substring(0,4);
		driver.findElement(By.id("employee_category_name")).sendKeys(name);
		driver.findElement(By.id("employee_category_prefix")).sendKeys(prefixName);
		driver.findElement(By.id("update_employee_category")).click();
		String success = driver.findElement(By.cssSelector("ul.success")).getText();
		assertTrue(common.verifyEquality(success,"EMPLOYEE CATEGORY IS UPDATED SUCCESSFULLY."));
	}
	
	@Test
	public void verifySpecialCharacterInName() throws Exception {
		driver.findElement(By.linkText("HR Settings")).click();
	    driver.findElement(By.linkText("Employee Category")).click();
		driver.findElement(By.id("employee_category_name")).clear();
		driver.findElement(By.id("employee_category_name")).sendKeys(createSpecialChars(5));
		driver.findElement(By.id("employee_category_prefix")).clear();
		driver.findElement(By.id("employee_category_prefix")).sendKeys(common.randomstring());
		driver.findElement(By.id("create_employee_category")).click();
		String message = driver.findElement(By.className("warning")).getText();
		verifyTextPresent(message, "SPECIAL CHARACTER ARE NOT ALLOWED IN CATEGORY NAME");
	}
	
	@Test
	public void verifySpecialCharacterInPrefix() throws Exception {
		driver.findElement(By.linkText("HR Settings")).click();
	    driver.findElement(By.linkText("Employee Category")).click();
		driver.findElement(By.id("employee_category_name")).clear();
		driver.findElement(By.id("employee_category_name")).sendKeys(common.randomstring());
		driver.findElement(By.id("employee_category_prefix")).clear();
		driver.findElement(By.id("employee_category_prefix")).sendKeys(createSpecialChars(5));
		driver.findElement(By.id("create_employee_category")).click();
		String message = driver.findElement(By.className("warning")).getText();
		verifyTextPresent(message, "SPECIAL CHARACTER ARE NOT ALLOWED IN PREFIX");
	}
	@Test
	public void deleteCategory() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Employee Category")).click();
		Thread.sleep(2000);
		String id = common.rowCountCheckOfDataTable("active-table", "id('active-table')/tbody/tr", "td", 3,"delete-employee-category-master-href","id",driver);
		Thread.sleep(2000);
		driver.findElement(By.id(id)).click();
		driver.findElement(By.xpath(".//*[@id='modal']/div/div[1]/div[2]/button[1]")).click();
		String class_name= common.returnClassName(driver);
		String message = driver.findElement(By.className(class_name)).getText();
		if (class_name == "success"){
			verifyTextPresent(message, "EMPLOYEE CATEGORY DELETED SUCCESSFULLY.");
		}else{
		assertTrue(common.verifyDependancyPresence(class_name,message,driver));
		}
	}
	
	@Test
	public void clearButtonVerify() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Employee Category")).click();
		Thread.sleep(2000);
		String id = common.rowCountCheckOfDataTable("active-table", "id('active-table')/tbody/tr", "td", 3,"update-employee-category-master-href","id",driver);
		Thread.sleep(2000);
		String attr1 = driver.findElement(By.id("create_employee_category")).getAttribute("disabled");
		driver.findElement(By.id(id)).click();
		String attr2 = driver.findElement(By.id("create_employee_category")).getAttribute("disabled");
		assertFalse(common.verifyEquality(attr1,attr2));
		driver.findElement(By.id("reset_employee_category")).click();
		String attr3 = driver.findElement(By.id("create_employee_category")).getAttribute("disabled");
		assertTrue(common.verifyEquality(attr1,attr3));
	}

	@Test
	public void duplicateNameVerification() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Employee Category")).click();
		Thread.sleep(2000);
		String id = common.rowCountCheckOfDataTable("active-table", "id('active-table')/tbody/tr", "td", 3,"update-employee-category-master-href","id",driver);
		String result  = common.returnIdValue(id);   
		String duplicateName = driver.findElement(By.id("category_name_"+result)).getText();
		driver.findElement(By.id("employee_category_name")).clear();
		driver.findElement(By.id("employee_category_name")).sendKeys(duplicateName);
		driver.findElement(By.id("employee_category_prefix")).sendKeys("newPrefix");
		driver.findElement(By.id("create_employee_category")).click();
		Thread.sleep(2000);
		String message = driver.findElement(By.cssSelector("ul.error")).getText();
		assertTrue(common.verifyEquality(message, "THERE ARE ERRORS WHILE PROCESSING YOUR REQUEST\n"+
												    "NAME : HAS ALREADY BEEN TAKEN"));
	}
	
	@Test
	public void duplicatePrefixVerification() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Employee Category")).click();
		Thread.sleep(2000);
		String id = common.rowCountCheckOfDataTable("active-table", "id('active-table')/tbody/tr", "td", 3,"update-employee-category-master-href","id",driver);
		String result  = common.returnIdValue(id);   
		String duplicatePrefix = driver.findElement(By.id("category_prefix_"+result)).getText();
		driver.findElement(By.id("employee_category_name")).clear();
		driver.findElement(By.id("employee_category_name")).sendKeys("New Name");
		driver.findElement(By.id("employee_category_prefix")).sendKeys(duplicatePrefix);
		driver.findElement(By.id("create_employee_category")).click();
		Thread.sleep(2000);
		String message = driver.findElement(By.cssSelector("ul.error")).getText();
		assertTrue(common.verifyEquality(message, "THERE ARE ERRORS WHILE PROCESSING YOUR REQUEST\n"+
												    "PREFIX : HAS ALREADY BEEN TAKEN"));
	}
	
	@Test
	public void inActiveTabCategoryDeleteVerification() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Employee Category")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Inactive")).click();
		String id = common.rowCountCheckOfDataTable("inactive-table", "id('inactive-table')/tbody/tr", "td", 3,"delete-employee-category-master-href","id",driver);
		Thread.sleep(2000);
		driver.findElement(By.id(id)).click();
		driver.findElement(By.xpath(".//*[@id='modal']/div/div[1]/div[2]/button[1]")).click();
		String class_name= common.returnClassName(driver);
		String message = driver.findElement(By.className(class_name)).getText();
		if (class_name == "success"){
			verifyTextPresent(message, "EMPLOYEE CATEGORY DELETED SUCCESSFULLY.");
		}else{
			assertTrue(common.verifyDependancyPresence(class_name,message,driver ));
		}
	}

	@Test
	public void checkNameCharacterLength() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Employee Category")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("employee_category_name")).clear();
		driver.findElement(By.id("employee_category_name")).sendKeys(createString(50));
		driver.findElement(By.id("employee_category_prefix")).sendKeys("No");
		driver.findElement(By.id("create_employee_category")).click();
		String message = driver.findElement(By.cssSelector("ul.warning")).getText();
		assertTrue(common.verifyEquality(message, "YOU CAN NOT ENTER MORE THAN 50 CHARACTER IN NAME"));
	}
	
	@Test
	public void checkPrefixCharacterLength() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Employee Category")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("employee_category_name")).clear();
		driver.findElement(By.id("employee_category_name")).sendKeys("Category");
		driver.findElement(By.id("employee_category_prefix")).sendKeys(createString(50));
		driver.findElement(By.id("create_employee_category")).click();
		String message = driver.findElement(By.cssSelector("ul.warning")).getText();
		assertTrue(common.verifyEquality(message, "YOU CAN NOT ENTER MORE THAN 50 CHARACTER IN PREFIX"));
	}
	
	
}
