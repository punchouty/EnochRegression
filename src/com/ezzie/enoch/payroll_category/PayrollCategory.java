package com.ezzie.enoch.payroll_category;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
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

public class PayrollCategory extends SeleniumBaseTest {
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
	public void emptyPayrollCategoryName() throws Exception {
	    driver.findElement(By.linkText("HR Settings")).click();
	    driver.findElement(By.linkText("Payroll Category")).click();
	    driver.findElement(By.id("payroll_category_name")).clear();
	    driver.findElement(By.id("create_category")).click();
	    String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
	    verifyTextPresent(warning, "PLEASE ENTER CATEGORY NAME");
	}

	@Test
	public void enter_numeric_payroll_category() throws Exception {
	    driver.findElement(By.linkText("HR Settings")).click();
	    driver.findElement(By.linkText("Payroll Category")).click();
		driver.findElement(By.id("payroll_category_name")).clear();
	    driver.findElement(By.id("payroll_category_name")).sendKeys(createNumber(5));
	    driver.findElement(By.id("create_category")).click();
	    String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
	    System.out.println(warning);
	    verifyTextPresent(warning, "PLEASE ENTER CATEGORY NAME");
	}
	
	@Test
	public void check_percentage_of_payroll_category() throws Exception {
    driver.findElement(By.linkText("HR Settings")).click();
    driver.findElement(By.linkText("Payroll Category")).click();
    driver.findElement(By.id("payroll_category_name")).clear();
    driver.findElement(By.id("payroll_category_name")).sendKeys("Abhbs");
    driver.findElement(By.id("payroll_category_percentage")).clear();
    driver.findElement(By.id("payroll_category_percentage")).sendKeys("6767");
    Select comboBox = new Select(driver.findElement(By.id("payroll_category_payroll_category_id")));
    comboBox.selectByVisibleText("Medical Allowance");
    driver.findElement(By.id("create_category")).click();
	}
    

	@Test
	public void check_clear_pyaroll_category() throws Exception {
    driver.findElement(By.linkText("HR Settings")).click();
    driver.findElement(By.linkText("Payroll Category")).click();
    driver.findElement(By.id("payroll_category_name")).clear();
    driver.findElement(By.id("payroll_category_name")).sendKeys("Abbs");
    driver.findElement(By.id("payroll_category_percentage")).clear();
    driver.findElement(By.id("payroll_category_percentage")).sendKeys("hfdjkhjsdh");
    Select comboBox = new Select(driver.findElement(By.id("payroll_category_payroll_category_id")));
    comboBox.selectByVisibleText("Medical Allowance"); 
    String text = comboBox.getFirstSelectedOption().getText();
    System.out.println(text);
    driver.findElement(By.id("reset_category")).click();
    String textT = comboBox.getFirstSelectedOption().getText();
    System.out.println(textT);
    assertFalse(verifyEquality(text,textT));
	}
	
	
	@Test
	public void check_deduction_of_payroll_category() throws Exception {
    driver.findElement(By.linkText("HR Settings")).click();
    driver.findElement(By.linkText("Payroll Category")).click();
    driver.findElement(By.id("payroll_category_name")).clear();
    driver.findElement(By.id("payroll_category_name")).sendKeys(common.randomstring());
    driver.findElement(By.id("payroll_category_percentage")).clear();
    driver.findElement(By.id("payroll_category_percentage")).sendKeys(Integer.toString(common.randomInteger(100)));
    Select comboBox = new Select(driver.findElement(By.id("payroll_category_payroll_category_id")));
    comboBox.selectByVisibleText("Medical Allowance");
    driver.findElement(By.id("payroll_category_is_deduction")).click();
    driver.findElement(By.id("create_category")).click();
    String success = driver.findElement(By.cssSelector("ul.success")).getText();
    System.out.println(success);
    verifyTextPresent(success,"PAYROLL CATEGORY WAS SUCCESSFULLY CREATED.");
    }
    
	@Test
	public void create_inactive_of_payroll_category() throws Exception {
    driver.findElement(By.linkText("HR Settings")).click();
    driver.findElement(By.linkText("Payroll Category")).click();
    driver.findElement(By.id("payroll_category_name")).clear();
    driver.findElement(By.id("payroll_category_name")).sendKeys(common.randomstring());
    driver.findElement(By.id("payroll_category_percentage")).clear();
    driver.findElement(By.id("payroll_category_percentage")).sendKeys(Integer.toString(common.randomInteger(100)));
    Select comboBox = new Select(driver.findElement(By.id("payroll_category_payroll_category_id")));
    comboBox.selectByVisibleText("Medical Allowance");
    driver.findElement(By.id("payroll_category_is_deduction")).click();
    driver.findElement(By.id("payroll_category_status_false")).click();
    driver.findElement(By.id("create_category")).click();
    String success = driver.findElement(By.cssSelector("ul.success")).getText();
    System.out.println(success);
    verifyTextPresent(success,"PAYROLL CATEGORY WAS SUCCESSFULLY CREATED.");
    }
	
	@Test
	public void check_active_to_inactive_payroll_category() throws Exception {
    driver.findElement(By.linkText("HR Settings")).click();
    driver.findElement(By.linkText("Payroll Category")).click();
    driver.findElement(By.id("update-href-10")).click();
    driver.findElement(By.id("payroll_category_status_false")).click();
    driver.findElement(By.id("update_category")).click();
    String success = driver.findElement(By.cssSelector("ul.success")).getText();
    System.out.println(success);
    verifyTextPresent(success,"PAYROLL CATEGORY WAS UPDATED SUCCESSFULLY.");
    }
	
	@Test
	public void delete_payroll_category() throws Exception {
	    driver.findElement(By.linkText("HR Settings")).click();
	    driver.findElement(By.linkText("Payroll Category")).click();
	    driver.findElement(By.id("delete-href-16")).click();
	    driver.findElement(By.xpath(".//*[@id='modal']/div/div[1]/div[2]/button[1]")).click();
	    String success = driver.findElement(By.cssSelector("ul.success")).getText();
	    System.out.println(success);
	    verifyTextPresent(success,"PAYROLL WAS DELETED SUCCESSFULLY.");
	    }
	
	
	

	public String returnClassname(){
		String classname = "";
		if(isElementPresent(By.className("success"))){
			classname = "success";}
		else if(isElementPresent(By.className("error"))){
		classname = "error";}
		else {
			classname = "warning";}
		return classname;

	}
	public Boolean verifyDependancyPresence (String class_name, String message){
		if (class_name =="success") {
			verifyTextPresent(message,"BANK FIELD WAS DELETED SUCCESSFULLY.");
			return true;}
		else{
			String msg = driver.findElement(By.cssSelector("ul.message.error > li")).getText();
			return verifyEquality(message,msg);
		}
	}
	
		public boolean verifyEquality(String text,String textT){
		if(text.equals(textT)){
			return true;
		}
		else{
			return false;
		}
		}
		
		public String rowCountCheckOfDataTable(String tableId,String tableRow,String tableTd, int tableCol,String className, String attr, WebDriver driver){
			WebElement table_element = driver.findElement(By.id(tableId));
			Set<String> set = new HashSet<String>();
	        List<WebElement> tr_collection = table_element.findElements(By.xpath(tableRow));      
	        String idName = "";
	        int row_num,col_num;
	        row_num=1;
	        for(WebElement trElement : tr_collection){
	            List<WebElement> td_collection = trElement.findElements(By.xpath(tableTd));
	            col_num=1;
	            for(WebElement tdElement : td_collection){
	            	 if(col_num == tableCol){  
	            		WebElement timeField1 = tdElement.findElement(By.className(className));
	            		idName = timeField1.getAttribute(attr);
	                    set.add(idName);
	            	 }
	            	 col_num++;
	            }
	            row_num++;
	        }
			Random rand = new Random();
			int dd = rand.nextInt(set.size());
	        return set.toArray()[dd].toString();
		}
}
