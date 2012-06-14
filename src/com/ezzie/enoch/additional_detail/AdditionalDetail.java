package com.ezzie.enoch.additional_detail;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import com.ezzie.enoch.employee_categories.Common;
import com.ezzie.enoch.infrastructure.SeleniumBaseTest;

public class AdditionalDetail extends SeleniumBaseTest{
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
	public void emptyAdditionalDetailName() throws Exception {
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Additional Detail")).click();
		driver.findElement(By.id("additional_field_name")).clear();
		driver.findElement(By.id("create_additional_field")).click();
		String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		verifyTextPresent(warning,"PLEASE ENTER ADDITIONAL FIELD NAME");
	}
	
	@Test
	public void checkNumericValueInAdditionalDetailName() throws Exception {
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Additional Detail")).click();
		driver.findElement(By.id("additional_field_name")).clear();
		driver.findElement(By.id("additional_field_name")).sendKeys(createNumber(5));
		driver.findElement(By.id("create_additional_field")).click();
		String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		verifyTextPresent(warning,"PLEASE ENTER CHARACTERS FOR NAME");
	}

	@Test
	public void checkSpecialCharacterInName() throws Exception {
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Additional Detail")).click();
		driver.findElement(By.id("additional_field_name")).clear();
		driver.findElement(By.id("additional_field_name")).sendKeys(createSpecialChars(5));
		driver.findElement(By.id("create_additional_field")).click();
		String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		System.out.println(warning);
		verifyTextPresent(warning,"PLEASE ENTER CHARACTERS FOR NAME");
	}

	@Test
	public void checkCharacterLengthInName() throws Exception {
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Additional Detail")).click();
		driver.findElement(By.id("additional_field_name")).clear();
		driver.findElement(By.id("additional_field_name")).sendKeys(createString(50));
		driver.findElement(By.id("create_additional_field")).click();
		String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		verifyTextPresent(warning,"YOU CAN NOT ENTER MORE THAN 25 CHARACTERS FOR NAME");
	}
	
	@Test
	public void createAdditionalDetail() throws Exception {
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Additional Detail")).click();
		driver.findElement(By.id("additional_field_name")).clear();
		driver.findElement(By.id("additional_field_name")).sendKeys(common.randomstring());
		driver.findElement(By.id("create_additional_field")).click();
		String success = driver.findElement(By.cssSelector("ul.success")).getText();
		verifyTextPresent(success,"ADDITIONAL FIELD WAS SUCCESSFULLY CREATED.");
	}
	
	@Test
	public void updateAdditionalDetail() throws Exception {
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Additional Detail")).click();
		String id = common.rowCountCheckOfDataTable("active-table", "id('active-table')/tbody/tr", "td", 3,"update-additionalfield-master-href","id",driver);
	    driver.findElement(By.id(id)).click();
		driver.findElement(By.id("additional_field_name")).sendKeys(common.randomstring());
		driver.findElement(By.id("update_additional_field")).click();
		String success = driver.findElement(By.cssSelector("ul.success")).getText();
		verifyTextPresent(success,"ADDITIONAL FIELD WAS UPDATED SUCCESSFULLY.");
	}
    
	@Test
	public void createInActiveAdditionalDetail() throws Exception {
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Additional Detail")).click();
		driver.findElement(By.id("additional_field_name")).clear();
		driver.findElement(By.id("additional_field_name")).sendKeys(common.randomstring());
		driver.findElement(By.id("additional_field_status_false")).click();
      driver.findElement(By.id("create_additional_field")).click();
		String success = driver.findElement(By.cssSelector("ul.success")).getText();
		verifyTextPresent(success,"ADDITIONAL FIELD WAS SUCCESSFULLY CREATED.");
	}
	
	@Test
	public void changeActiveDetailToInActiveAdditionalDetail() throws Exception {
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Additional Detail")).click();
		String id = common.rowCountCheckOfDataTable("active-table", "id('active-table')/tbody/tr", "td", 3,"update-additionalfield-master-href","id",driver);
	    driver.findElement(By.id(id)).click();
		driver.findElement(By.id("additional_field_status_false")).click();
      driver.findElement(By.id("update_additional_field")).click();
		String success = driver.findElement(By.cssSelector("ul.success")).getText();
		System.out.println(success);
		verifyTextPresent(success,"ADDITIONAL FIELD WAS UPDATED SUCCESSFULLY.");
	}
	
	@Test
	public void deleteAdditionalDetail() throws Exception {
		driver.findElement(By.linkText("HR Settings")).click();
		driver.findElement(By.linkText("Additional Detail")).click();
		String id = common.rowCountCheckOfDataTable("active-table", "id('active-table')/tbody/tr", "td", 3,"delete-additionalfield-master-href","id",driver);
	    driver.findElement(By.id(id)).click();
		driver.findElement(By.xpath(".//*[@id='modal']/div/div[1]/div[2]/button[1]")).click();
		String class_name = common.returnClassName(driver);
		String message = driver.findElement(By.className(class_name)).getText();
		if (class_name =="success") {
			verifyTextPresent(message,"ADDITIONAL FIELD WAS DELETED SUCCESSFULLY.");
		}else{
		    assertTrue(common.verifyDependancyPresence(class_name, message , driver));
		}
	}
	
	 @Test
	 public void duplicateNameVerification() throws Exception{
		    driver.findElement(By.linkText("HR Settings")).click();
		    driver.findElement(By.linkText("Additional Detail")).click();
		    String id = common.rowCountCheckOfDataTable("active-table", "id('active-table')/tbody/tr", "td", 3,"update-additionalfield-master-href","id",driver);
		    String Idvalue = common.returnIdValue(id);
		    String name = driver.findElement(By.id("additional_field_name_"+Idvalue)).getText();
		    driver.findElement(By.id("additional_field_name")).clear();
		    driver.findElement(By.id("additional_field_name")).sendKeys(name);
			driver.findElement(By.id("create_additional_field")).click();
			Thread.sleep(2000);
			String class_name = common.returnClassName(driver);
			String error = driver.findElement(By.className(class_name)).getText();
			 assertTrue(common.verifyDependancyPresence(class_name, error, driver));
		 }

}
