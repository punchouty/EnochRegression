package com.ezzie.enoch.employee_positions;

import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import com.ezzie.enoch.employee_categories.Common;
import com.ezzie.enoch.infrastructure.SeleniumBaseTest;


public class EmployeePostions extends SeleniumBaseTest {
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
	public void checkEmptyPositionName() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Employee Position")).click();
		driver.findElement(By.id("employee_position_name")).clear();
		driver.findElement(By.id("create_position")).click();
		String text = driver.findElement(By.className("warning")).getText();
		verifyTextPresent(text, "PLEASE ENTER POSITION NAME");
	}
	
	@Test
	public void emptyEmployeeCategoryName() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Employee Position")).click();
		driver.findElement(By.id("employee_position_name")).clear();
		driver.findElement(By.id("employee_position_name")).sendKeys("New Position");
		Select comboBox = new Select(driver.findElement(By.id("employee_position_employee_category_id")));
		comboBox.selectByIndex(0); 
		driver.findElement(By.id("create_position")).click();	
		String text = driver.findElement(By.className("warning")).getText();
		verifyTextPresent(text, "PLEASE SELECT CATEGORY");
	}
	
	@Test
	public void createCategory() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Employee Position")).click();
		driver.findElement(By.id("employee_position_name")).clear();
		driver.findElement(By.id("employee_position_name")).sendKeys(common.randomstring());
		Select comboBox = new Select(driver.findElement(By.id("employee_position_employee_category_id")));
		comboBox.selectByIndex(1); 
		driver.findElement(By.id("create_position")).click();	
		String text = driver.findElement(By.className("success")).getText();
		verifyTextPresent(text, "EMPLOYEE POSITION WAS SUCCESSFULLY CREATED.");
	}
	
	@Test
	public void checkCSSChange() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Employee Position")).click();
		String id = common.rowCountCheckOfDataTable("active-table", "id('active-table')/tbody/tr", "td", 3,"update-position-master-href","id",driver);
		Thread.sleep(2000);
		driver.findElement(By.id(id)).click();
		driver.findElement(By.id(id)).click();
		String attr1 = driver.findElement(By.id("create_position")).getAttribute("disabled");
		String attr2 = driver.findElement(By.id("update_position")).getAttribute("disabled");
		assertEquals(attr1, "true");
		assertEquals(attr2, "false");
	}

	@Test
	public void checkClearButton() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Employee Position")).click();
		driver.findElement(By.id("employee_position_name")).sendKeys("New Value");
		Select comboBox = new Select(driver.findElement(By.id("employee_position_employee_category_id")));
		comboBox.selectByVisibleText("Management");
		driver.findElement(By.id("reset_position")).click();
		String selectedValueAfterReset= comboBox.getFirstSelectedOption().getText();
	    assertEquals("Select", selectedValueAfterReset);
	}
	
	@Test
	public void createInActivePosition() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Employee Position")).click();
		driver.findElement(By.id("employee_position_name")).sendKeys(common.randomstring());
		Select comboBox = new Select(driver.findElement(By.id("employee_position_employee_category_id")));
		comboBox.selectByVisibleText("Management");
		driver.findElement(By.id("employee_position_status_false")).click();
		driver.findElement(By.id("create_position")).click();
		String text = driver.findElement(By.className("success")).getText();
	    verifyTextPresent(text, "EMPLOYEE POSITION WAS SUCCESSFULLY CREATED.");
	}
	
	@Test
	public void deletePosition() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Employee Position")).click();
		String id = common.rowCountCheckOfDataTable("active-table", "id('active-table')/tbody/tr", "td", 3,"delete-position-master-href","id",driver);
		driver.findElement(By.id(id)).click();
		driver.findElement(By.xpath(".//*[@id='modal']/div/div[1]/div[2]/button[1]")).click();
		String class_name = common.returnClassName(driver);
		String message = driver.findElement(By.className(class_name)).getText();
		if (class_name == "success"){
			verifyTextPresent(message, "EMPLOYEE POSITION DELETED SUCCESSFULLY.");
		}else{
		assertTrue(common.verifyDependancyPresence(class_name,message,driver));
		}
	}
	
	@Test
	public void checkBackButton() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Employee Position")).click();
		String currentUrl = driver.getCurrentUrl().toString();
		driver.findElement(By.xpath(".//*[@id='positions-form']/div[2]/ul/li/a/img")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String backUrl=  driver.getCurrentUrl().toString();
        assertTrue(common.verifyEquality(currentUrl,backUrl));
	}
	
	@Test
	public void editEmployeePosition() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Employee Position")).click();
		Thread.sleep(2000);
		String id = common.rowCountCheckOfDataTable("active-table", "id('active-table')/tbody/tr", "td", 3,"update-position-master-href","id",driver);
		driver.findElement(By.id(id)).click();
		driver.findElement(By.id("employee_position_name")).sendKeys(common.randomstring());
		driver.findElement(By.id("update_position")).click();
		String success = driver.findElement(By.className("success")).getText();
		verifyTextPresent(success, "EMPLOYEE POSITION WAS UPDATED SUCCESSFULLY.");
	}
	
	@Test
	public void checkPositionNameCharacterLength() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Employee Position")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("employee_position_name")).clear();
		driver.findElement(By.id("employee_position_name")).sendKeys(createString(50));
		driver.findElement(By.id("create_position")).click();
		String warning = driver.findElement(By.className("warning")).getText();
		verifyTextPresent(warning, "YOU CAN NOT ENTER MORE THAN 50 CHARACTER IN NAME");
	}
	
	@Test
	public void verifySpecialCharacterInName() throws Exception {
		driver.findElement(By.linkText("HR Settings")).click();
	    driver.findElement(By.linkText("Employee Position")).click();
		driver.findElement(By.id("employee_position_name")).clear();
		driver.findElement(By.id("employee_position_name")).sendKeys(createSpecialChars(5));
		driver.findElement(By.id("create_position")).click();
		String message = driver.findElement(By.className("warning")).getText();
		verifyTextPresent(message, "PLEASE ENTER CHARACTERS FOR NAME");
	}
}
