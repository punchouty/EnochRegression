package com.ezzie.enoch.rooms;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.ezzie.enoch.employee_category.Common;
import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class Rooms extends LoggedInUserTest{
	Common common = new Common();

	@Before
	public void setUp() throws Exception {
		  super.setUp();
	}
	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}
	
	@Test
	public void emptyRoomName() throws Exception{
	driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
	driver.findElement(By.linkText("Rooms")).click();
	driver.findElement(By.id("room_name")).clear();
	driver.findElement(By.id("create_room")).click();
	String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
	System.out.println(warning);
	verifyTextPresent(warning, "PLEASE ENTER A NAME FOR ROOM");
}
	
	@Test
	public void enterNumericInRoomName() throws Exception{
	driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
	driver.findElement(By.linkText("Rooms")).click();
	driver.findElement(By.id("room_name")).clear();
	driver.findElement(By.id("room_name")).sendKeys(createNumber(9));
	driver.findElement(By.id("create_room")).click();
	String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
	System.out.println(warning);
	verifyTextPresent(warning, "PLEASE ENTER A NAME FOR ROOM");
}
	
	@Test
	public void enterSpecialCharacterInRoomName() throws Exception{
	driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
	driver.findElement(By.linkText("Rooms")).click();
	driver.findElement(By.id("room_name")).clear();
	driver.findElement(By.id("room_name")).sendKeys(createSpecialChars(4));
	driver.findElement(By.id("create_room")).click();
	String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
	System.out.println(warning);
	verifyTextPresent(warning, "SPECIAL CHARACTER ARE NOT ALLOWED IN NAME");
}
	@Test
	public void checkMaximumLenghtInRoomName() throws Exception{
	driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
	driver.findElement(By.linkText("Rooms")).click();
	driver.findElement(By.id("room_name")).clear();
	driver.findElement(By.id("room_name")).sendKeys(createString(20));
	driver.findElement(By.id("create_room")).click();
	String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
	System.out.println(warning);
	verifyTextPresent(warning, "PLEASE ENTER A NAME FOR ROOM");
}
	
	@Test
	public void emptyRoomCapacity() throws Exception{
	driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
	driver.findElement(By.linkText("Rooms")).click();
	driver.findElement(By.id("room_name")).clear();
	driver.findElement(By.id("room_name")).sendKeys(common.randomstring());
	driver.findElement(By.id("room_capacity")).clear();
	driver.findElement(By.id("create_room")).click();
	String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
	System.out.println(warning);
	verifyTextPresent(warning, "PLEASE ENTER THE CAPACITY");
}
	
	@Test
	public void EnterAlphabetsInRoomCapacity() throws Exception {
	driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
	driver.findElement(By.linkText("Rooms")).click();
	driver.findElement(By.id("room_name")).clear();
	driver.findElement(By.id("room_name")).sendKeys(common.randomstring());
	driver.findElement(By.id("room_capacity")).clear();
	driver.findElement(By.id("room_capacity")).sendKeys(common.randomstring());
	driver.findElement(By.id("create_room")).click();
	String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
	System.out.println(warning);
	verifyTextPresent(warning, "PLEASE ENTER NUMBERS IN CAPACITY");
}
	@Test
	public void emptyselectTypeOfRoom() throws Exception{
	driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
	driver.findElement(By.linkText("Rooms")).click();
	driver.findElement(By.id("room_name")).clear();
	driver.findElement(By.id("room_name")).sendKeys(common.randomstring());
	driver.findElement(By.id("room_capacity")).clear();
	driver.findElement(By.id("room_capacity")).sendKeys(Integer.toString(common.randomInteger(20)));
	driver.findElement(By.id("create_room")).click();
	String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
	System.out.println(warning);
	verifyTextPresent(warning, "PLEASE SELECT THE ROOM TYPE");
}
	
	@Test
	public void emptyOwnerOfRoom() throws Exception{
	driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
	driver.findElement(By.linkText("Rooms")).click();
	driver.findElement(By.id("room_name")).clear();
	driver.findElement(By.id("room_name")).sendKeys(common.randomstring());
	driver.findElement(By.id("room_capacity")).clear();
	driver.findElement(By.id("room_capacity")).sendKeys(Integer.toString(common.randomInteger(20)));
	Select comobox = new Select(driver.findElement(By.id("room_roomtype")));
	comobox.selectByIndex(1);
	driver.findElement(By.id("create_room")).click();
	String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
	System.out.println(warning);
	verifyTextPresent(warning, "PLEASE SELECT A OWNER FOR ROOM");
}
	
	@Test
	public void createActiveRoom() throws Exception{
	driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
	driver.findElement(By.linkText("Rooms")).click();
	driver.findElement(By.id("room_name")).clear();
	driver.findElement(By.id("room_name")).sendKeys(common.randomstring());
	driver.findElement(By.id("room_capacity")).clear();
	driver.findElement(By.id("room_capacity")).sendKeys(Integer.toString(common.randomInteger(20)));
	Select comobox = new Select(driver.findElement(By.id("room_roomtype")));
	comobox.selectByIndex(1);
	Select comoBox = new Select(driver.findElement(By.id("room_employee_id")));
	comoBox.selectByIndex(1);
	driver.findElement(By.id("create_room")).click();
	String success = driver.findElement(By.cssSelector("ul.success")).getText();
	System.out.println(success);
	verifyTextPresent(success, "RESOURCE WAS SUCCESSFULLY CREATED.");
}


	@Test
	public void createINInactiveRoom() throws Exception{
	driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
	driver.findElement(By.linkText("Rooms")).click();
	driver.findElement(By.id("room_name")).clear();
	driver.findElement(By.id("room_name")).sendKeys(common.randomstring());
	driver.findElement(By.id("room_capacity")).clear();
	driver.findElement(By.id("room_capacity")).sendKeys(Integer.toString(common.randomInteger(20)));
	Select comobox = new Select(driver.findElement(By.id("room_roomtype")));
	comobox.selectByIndex(1);
	Select comoBox = new Select(driver.findElement(By.id("room_employee_id")));
	comoBox.selectByIndex(1);
	driver.findElement(By.id("room_status_false")).click();
	driver.findElement(By.id("create_room")).click();
	String success = driver.findElement(By.cssSelector("ul.success")).getText();
	System.out.println(success);
	verifyTextPresent(success, "RESOURCE WAS SUCCESSFULLY CREATED.");
}
	
	  @Test
	  public void checkActiveToInactiveRoom() throws Exception {
		  driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
		  driver.findElement(By.linkText("Rooms")).click();
		  String dd = common.rowCountCheckOfDataTable("active-table-room", "id('active-table-room')/tbody/tr", "td", 5,"update-room-href","id",driver);
		  driver.findElement(By.id(dd)).click();
		  Thread.sleep(2000);
		  driver.findElement(By.id("room_status_false")).click();
		  driver.findElement(By.id("update_room")).click();
		  String success = driver.findElement(By.cssSelector("ul.success")).getText();
		  System.out.println(success);
		  verifyTextPresent(success, "RESOURCE WAS UPDATED SUCCESSFULLY."); 
		    }
	  
	  @Test
	  public void deleteRoom() throws Exception {
		  driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
		  driver.findElement(By.linkText("Rooms")).click();
		  String dd = common.rowCountCheckOfDataTable("active-table-room", "id('active-table-room')/tbody/tr", "td", 5,"delete-room-href","id",driver);
		  driver.findElement(By.id(dd)).click();
          driver.findElement(By.xpath(".//*[@id='modal']/div/div[1]/div[2]/button[1]")).click();
		  String success = driver.findElement(By.cssSelector("ul.success")).getText();
		  System.out.println(success);
		  verifyTextPresent(success, "RESOURCE WAS DELETED SUCCESSFULLY."); 
		  }
	  
	  @Test
	  public void editAndUpdateRooms() throws Exception {
		  driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
		  driver.findElement(By.linkText("Rooms")).click();
		  String dd = common.rowCountCheckOfDataTable("active-table-room", "id('active-table-room')/tbody/tr", "td", 5,"update-room-href","id",driver);
		  driver.findElement(By.id(dd)).click();
		  driver.findElement(By.id("room_name")).sendKeys(common.randomstring());
		  driver.findElement(By.id("update_room")).click();
		  String success = driver.findElement(By.cssSelector("ul.success")).getText();
		  System.out.println(success);
		  verifyTextPresent(success, "RESOURCE WAS UPDATED SUCCESSFULLY."); 
		  }
	  
	  @Test
	  public void addRoomSkills() throws Exception {
		  driver.findElement(By.xpath(".//*[@id='sub-nav']/div/ul/li[2]/a")).click();
		  driver.findElement(By.linkText("Rooms")).click();
		  driver.findElement(By.id("room_skills")).click();
		  driver.findElement(By.id("room_skill_asign")).click();
		  }
	  
	  
	  }
