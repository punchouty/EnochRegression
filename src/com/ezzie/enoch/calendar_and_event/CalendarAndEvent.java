package com.ezzie.enoch.calendar_and_event;

import static org.junit.Assert.*;

import java.awt.Robot;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasInputDevices;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Mouse;
import org.openqa.selenium.Point;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.RemoteWebElement;


import com.ezzie.enoch.employee_category.Common;

import com.ezzie.enoch.infrastructure.SeleniumBaseTest;

public class CalendarAndEvent extends SeleniumBaseTest{
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
	public void goToCalendar() throws Exception{
		driver.findElement(By.linkText("Calendar")).click();
		assertTrue(common.isElementPresents(By.id("new_calendar") , driver));
	}
	
	@Test
	public void randonmlyClickOnAddEvent() throws Exception{
		goToCalendar();
		 Point coordinates = driver.findElement(By.className("add-event")).getLocation();
		  Robot robot = new Robot();
		  robot.mouseMove(coordinates.getX()+50,coordinates.getY()+200);
//		Mouse mouse = ((HasInputDevices) driver).getMouse();
//		Locatable hoverItem = (Locatable) driver.findElement(By.xpath(".//*[@id='calendar']/div[2]/table/tbody/tr[2]/td[4]"));
////		mouse.mouseMove(((Locatable)weSecNav).getCoordinates(), 0, 0 );
//		System.out.println(hoverItem);
//
//		mouse.mouseMove(hoverItem.getCoordinates());
////		System.out.println(hoverItem.getCoordinates().toString());
		  
		  Point coordinate = driver.findElement(By.xpath(".//*[@id='calendar']/div[2]/table/tbody/tr[2]/td[4]/div/div")).getLocation();


		  robot.mouseMove(coordinate.getX()+10,coordinate.getY()+150);

		
//		((JavascriptExecutor)driver).executeScript("$('div.add-event').hover();");
//		System.out.println("here");
//		String sjhksj = driver.findElement(By.linkText("Add")).getClass().toString();  
//		System.out.println(sjhksj);
		
		//just trying with for loop as the tertiary popup disappears quickly and hence getting ElementNotVisible Exception
//		for (int i = 0 ; i< 2; i++){
//		    mouse.mouseDown(((Locatable)weSecNav).getCoordinates());
//		    mouse.mouseMove(((Locatable)weSecNav).getCoordinates(), 0, 0 );
//		    WebElement weTerNav = driver.findElement(By.className("add-event").linkText("Add"));
////		    WebElement weTerNav = driver.findElement(By.linkText(strTertiaryNav));
//		    boolean isSecDisplayed = ((WebElement)weTerNav).isDisplayed();
//		    System.out.println("isDisplayed: " + isSecDisplayed);
////		    System.out.println(" " + ((WebElement)weSecNav).getAttribute("href"));
////		    weTerNav.click();
//		}
//      //get the element that shows menu with the mouseOver event
//        WebElement menu = driver.findElement(By.xpath(".//*[@id='calendar']/div[2]/table/tbody/tr[2]/td[4]"));
//
//        //the element that I want to click (hidden)
//        WebElement menuOption = driver.findElement(By.xpath(".//*[@id='calendar']/div[2]/table/tbody/tr[2]/td[4]/div/div/a"));
//
//        //build and perform the mouseOver with Advanced User Interactions
//
//        Actions builder = new Actions(driver);
//        builder.moveToElement(menu).build().perform();
//
//        //then click when menu option is visible
//        menuOption.click(); 

//		driver.findElement(By.className("add-event")).click();
	}

}
