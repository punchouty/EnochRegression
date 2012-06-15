package com.ezzie.enoch.ptm_master;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasInputDevices;
import org.openqa.selenium.Mouse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ezzie.enoch.employee_category.Common;
import com.ezzie.enoch.infrastructure.SeleniumBaseTest;

public class PtmMaster extends SeleniumBaseTest{
	Common common = new Common();
	@Before
	public void setUp() throws Exception {
		super.setUp();
		driver.get(baseUrl + "/signin");
		driver.findElement(By.id("session_username")).clear();
		driver.findElement(By.id("session_username")).sendKeys("E0005");
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
	public void checkEmployeeLogin() throws Exception{
		Mouse mouse = ((HasInputDevices) driver).getMouse();
		Locatable hoverItem = (Locatable) driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[5]/a"));
		mouse.mouseMove(hoverItem.getCoordinates());
		WebElement myDynamicElement = (new WebDriverWait(driver, 15))
		  .until(new ExpectedCondition<WebElement>(){
			@Override
			public WebElement apply(WebDriver d) {
				return d.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[5]/div[2]/ul/li/a"));
			}});
		myDynamicElement.click();
		assertTrue(common.isElementPresents(By.id("ptm_student_batch_id"), driver));
	}
	
	@Test
	public void checkStudentTablePresence() throws Exception{
		checkEmployeeLogin();
		new Select(driver.findElement(By.id("ptm_student_batch_id"))).selectByIndex(1);
		assertTrue(common.isElementPresents(By.className("ptmStudentAssign"), driver));
	}
	
	@Test
	public void checkAtLeastOneStudentSelected() throws Exception{
		checkStudentTablePresence();
		driver.findElement(By.id("create_ptm_student")).click();
		String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		verifyTextPresent(warning, "PLEASE SELECT ATLEAST 1 STUDENT");
	}
	
	@Test
	public void checkPtmCreationFormModalBox() throws Exception{
		checkStudentTablePresence();
		driver.findElement(By.id("select-ptm_all")).click();
		driver.findElement(By.id("create_ptm_student")).click();
		assertTrue(common.isElementPresents(By.id("modal"), driver));
	}
	
	@Test
	public void checkTitlePresenceForPTM() throws Exception{
		checkPtmCreationFormModalBox();
		driver.findElement(By.xpath(".//*[@id='modal']/div/div[1]/div[2]/button[1]")).click();
		String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		verifyTextPresent(warning, "PLEASE ENTER MEETING TILTE");
	}
	
	@Test
	public void checkDescriptionPresenceForPTM() throws Exception{
		checkPtmCreationFormModalBox();
		driver.findElement(By.xpath("(//*[@id='ptm_master_title'])[2]")).sendKeys("title");
		driver.findElement(By.xpath(".//*[@id='modal']/div/div[1]/div[2]/button[1]")).click();
		String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		verifyTextPresent(warning, "PLEASE ENTER MEETING DESCRIPTION");
	}
	
	@Test
	public void createParentTeacherMeeting() throws Exception{
		checkPtmCreationFormModalBox();
		driver.findElement(By.xpath("(//*[@id='ptm_master_title'])[2]")).sendKeys(common.randomstring());
		driver.findElement(By.xpath("(//*[@id='ptm_master_description'])[2]")).sendKeys("Parent Teacher Meeting Descripton");
		driver.findElement(By.xpath(".//*[@id='modal']/div/div[1]/div[2]/button[1]")).click();
		String success = driver.findElement(By.cssSelector("ul.success")).getText();
		verifyTextPresent(success, "PARENT TEACHER MEETING IS SUCCESFULLY CREATED.");
		Thread.sleep(2000);
		String anotherSuccess = driver.findElement(By.cssSelector("ul.success")).getText();
		verifyTextPresent(anotherSuccess, "PARENT TEACHER MEETING DETAIL IS SUCCESFULLY CREATED.");
	}
	
	@Test
	public void verifyPTMCreation() throws Exception{
		checkPtmCreationFormModalBox();
		String myTitle = common.randomstring();
		driver.findElement(By.xpath("(//*[@id='ptm_master_title'])[2]")).sendKeys(myTitle);
		driver.findElement(By.xpath("(//*[@id='ptm_master_description'])[2]")).sendKeys("Parent Teacher Meeting Descripton");
		driver.findElement(By.xpath(".//*[@id='modal']/div/div[1]/div[2]/button[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("show_active_ptm")).click();
		Thread.sleep(2000);
		List<WebElement> myText = driver.findElements(By.xpath("(//*[@class='task with-legend'])[2]"));
		System.out.println(myText.size());
		for(WebElement list : myText){
			System.out.println(list.findElement(By.cssSelector("div.task-description")).findElement(By.tagName("h3")).getText());
		}
		
//		assertTrue(common.isElementPresents(By.xpath("(//*[@id='PTMEvent'])[2]"), driver));
//		String success = driver.findElement(By.cssSelector("ul.success")).getText();
//		verifyTextPresent(success, "PARENT TEACHER MEETING IS SUCCESFULLY CREATED.");
//		Thread.sleep(2000);
//		String anotherSuccess = driver.findElement(By.cssSelector("ul.success")).getText();
//		verifyTextPresent(anotherSuccess, "PARENT TEACHER MEETING DETAIL IS SUCCESFULLY CREATED.");
	}
}
