package com.ezzie.enoch.bank_detail;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import com.ezzie.enoch.employee_categories.Common;
import com.ezzie.enoch.infrastructure.SeleniumBaseTest;

public class BankDetail extends SeleniumBaseTest {
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
	public void emptyBankDetail() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Bank Detail")).click();
		driver.findElement(By.id("bank_field_name")).clear();
		driver.findElement(By.id("create_bank_field")).click();
		String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		verifyTextPresent(warning, "PLEASE ENTER BANK FIELD NAME");
	}
	
	@Test
	public void createBankDetail() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Bank Detail")).click();
		driver.findElement(By.id("bank_field_name")).clear();
		driver.findElement(By.id("bank_field_name")).sendKeys(common.randomstring());
		driver.findElement(By.id("create_bank_field")).click();
		String text= driver.findElement(By.className("success")).getText();
		verifyTextPresent(text,"BANK FIELD WAS SUCCESSFULLY CREATED.");
	} 
	
	@Test
	public void checkNumericValueInName() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Bank Detail")).click();
		driver.findElement(By.id("bank_field_name")).clear();
		driver.findElement(By.id("bank_field_name")).sendKeys(createNumber(5));
		driver.findElement(By.id("create_bank_field")).click();
		String text= driver.findElement(By.className("warning")).getText();
		verifyTextPresent(text,"PLEASE ENTER CHARACTERS FOR NAME");
	} 
	
	@Test
	public void checkSpecialCharacterInName() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Bank Detail")).click();
		driver.findElement(By.id("bank_field_name")).clear();
		driver.findElement(By.id("bank_field_name")).sendKeys(createSpecialChars(5));
		driver.findElement(By.id("create_bank_field")).click();
		String text= driver.findElement(By.className("warning")).getText();
		verifyTextPresent(text,"PLEASE ENTER CHARACTERS FOR NAME");
	} 
	
	@Test
	public void updateBankDetail() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Bank Detail")).click();
		String id = common.rowCountCheckOfDataTable("active-table", "id('active-table')/tbody/tr", "td", 3,"update-bank_field-master-href","id",driver);
		Thread.sleep(2000);
		driver.findElement(By.id(id)).click();
		driver.findElement(By.id("bank_field_name")).sendKeys("Cd");
		driver.findElement(By.id("update_bank_field")).click();
		String success= driver.findElement(By.cssSelector("ul.success")).getText();
		verifyTextPresent(success, "BANK FIELD WAS UPDATED SUCCESSFULLY.");
	}
	
	@Test
	public void createInactiveBankDetail() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Bank Detail")).click();
		driver.findElement(By.id("bank_field_name")).clear();
		driver.findElement(By.id("bank_field_name")).sendKeys(common.randomstring());
		driver.findElement(By.id("bank_field_status_false")).click();
		int inActiveRowCountBeforeChange = common.returnTableRowCount("inactive-table", "id('inactive-table')/tbody/tr", driver);
		Thread.sleep(2000);
		driver.findElement(By.id("create_bank_field")).click();
		Thread.sleep(5000);
		int inActiveRowCountAfterChange = common.returnTableRowCount("inactive-table", "id('inactive-table')/tbody/tr", driver);
		String success = driver.findElement(By.cssSelector("ul.success")).getText();
		Thread.sleep(5000);
		verifyTextPresent(success, "BANK FIELD WAS SUCCESSFULLY CREATED.");
		assertFalse(common.verifyEquality(Integer.toString(inActiveRowCountBeforeChange), Integer.toString(inActiveRowCountAfterChange)));
	}
	
	@Test
	public void activeToInactiveBankDetail() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Bank Detail")).click();
		String id = common.rowCountCheckOfDataTable("active-table", "id('active-table')/tbody/tr", "td", 3,"update-bank_field-master-href","id",driver);
		Thread.sleep(2000);
		driver.findElement(By.id(id)).click();
		driver.findElement(By.id("bank_field_status_false")).click();
		driver.findElement(By.id("update_bank_field")).click();
		String success= driver.findElement(By.cssSelector("ul.success")).getText();
		verifyTextPresent(success, "BANK FIELD WAS UPDATED SUCCESSFULLY.");
	} 
	
	@Test
	public void checkBackButtonInBankDetail() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		Thread.sleep(2000);
		String currentUrl = driver.getCurrentUrl().toString();
		driver.findElement(By.linkText("Bank Detail")).click();
	    driver.findElement(By.xpath(".//*[@id='bank_fields-form']/div[2]/ul/li/a/img")).click();
	    Thread.sleep(2000);
	    String backUrl = driver.getCurrentUrl().toString();
	    assertTrue(common.verifyEquality(currentUrl, backUrl));	
	}
	
	@Test
	public void deleteBankDetail() throws Exception{
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Bank Detail")).click();
		String id = common.rowCountCheckOfDataTable("active-table", "id('active-table')/tbody/tr", "td", 3,"delete-bank_field-master-href","id",driver);
		driver.findElement(By.id(id)).click();
		driver.findElement(By.xpath(".//*[@id='modal']/div/div[1]/div[2]/button[1]")).click();
		Thread.sleep(2000);
		String class_name = common.returnClassName(driver);
		String message = driver.findElement(By.className(class_name)).getText();
		if (class_name =="success") {
			verifyTextPresent(message,"BANK FIELD WAS DELETED SUCCESSFULLY.");
		}else{
			assertTrue(common.verifyDependancyPresence(class_name, message , driver));
		}
	}

}
	
	
	


