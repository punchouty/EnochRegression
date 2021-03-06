package com.ezzie.enoch.attendances;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;


import org.openqa.selenium.support.ui.Select;

import com.ezzie.enoch.infrastructure.SeleniumBaseTest;

public class Attendances extends SeleniumBaseTest {
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
	public void markAttendance() throws Exception{
		driver.findElement(By.linkText("Attendance")).click();
		driver.findElement(By.linkText("Attendance Register")).click();
		Select comboBox = new Select(driver.findElement(By.id("attendance_course_search_course_id")));
		comboBox.selectByVisibleText("PreNursery");
		Thread.sleep(2000);
		Select comboBox1 = new Select(driver.findElement(By.id("student_batch_id")));
		comboBox1.selectByVisibleText("Prep - A");
		Thread.sleep(2000);
		Select comboBox2 = new Select(driver.findElement(By.xpath("//html/body/article/div[2]/section/div/div/div[4]/div/div/table/tbody/tr[1]/td[4]/select")));
		comboBox2.selectByIndex(1);
		driver.findElement(By.id("save_attendances")).click();
		String text = driver.findElement(By.className("success")).getText();
	    verifyTextPresent(text, "ATTENDANCE MARKED SUCESSFULLY.");
	}
	
	@Test
	public void checkAlreadyMarkedAttendance() throws Exception{
		driver.findElement(By.linkText("Attendance")).click();
		driver.findElement(By.linkText("Attendance Register")).click();
		Select comboBox = new Select(driver.findElement(By.id("attendance_course_search_course_id")));
		comboBox.selectByVisibleText("PreNursery");
		Thread.sleep(2000);
		Select comboBox1 = new Select(driver.findElement(By.id("student_batch_id")));
		comboBox1.selectByVisibleText("Prep - A");
		Thread.sleep(2000);
		Select comboBox2 = new Select(driver.findElement(By.xpath("//html/body/article/div[2]/section/div/div/div[4]/div/div/table/tbody/tr[1]/td[4]/select")));
		String status = comboBox2.getFirstSelectedOption().getText();
		verifyTextPresent(status, "A");
	}
	
	@Test
	public void multipleAttendanceMark() throws Exception{
		driver.findElement(By.linkText("Attendance")).click();
		driver.findElement(By.linkText("Attendance Register")).click();
		Select comboBox = new Select(driver.findElement(By.id("attendance_course_search_course_id")));
		comboBox.selectByVisibleText("PreNursery");
		Thread.sleep(2000);
		Select comboBox1 = new Select(driver.findElement(By.id("student_batch_id")));
		comboBox1.selectByVisibleText("Prep - A");
		Thread.sleep(4000);
		Select comboBox2 = new Select(driver.findElement(By.xpath("//html/body/article/div[2]/section/div/div/div[4]/div/div/table/tbody/tr[1]/td[4]/select")));
		comboBox2.selectByIndex(1);
		Select comboBox3 = new Select(driver.findElement(By.xpath("//html/body/article/div[2]/section/div/div/div[4]/div/div/table/tbody/tr[3]/td[4]/select")));
		comboBox3.selectByIndex(1);
		Select comboBox4 = new Select(driver.findElement(By.xpath("//html/body/article/div[2]/section/div/div/div[4]/div/div/table/tbody/tr[2]/td[4]/select")));
		comboBox4.selectByIndex(1);
		driver.findElement(By.id("save_attendances")).click();
		String text = driver.findElement(By.className("success")).getText();
	    verifyTextPresent(text, "ATTENDANCE MARKED SUCESSFULLY.");
	}
	
	@Test
	public void checkAlreadyMarkedMultipleAttendance() throws Exception{
		driver.findElement(By.linkText("Attendance")).click();
		driver.findElement(By.linkText("Attendance Register")).click();
		Select comboBox = new Select(driver.findElement(By.id("attendance_course_search_course_id")));
		comboBox.selectByVisibleText("PreNursery");
		Thread.sleep(2000);
		Select comboBox1 = new Select(driver.findElement(By.id("student_batch_id")));
		comboBox1.selectByVisibleText("Prep - A");
		Thread.sleep(2000);
		Select comboBox2 = new Select(driver.findElement(By.xpath("//html/body/article/div[2]/section/div/div/div[4]/div/div/table/tbody/tr[1]/td[4]/select")));
		String status2 = comboBox2.getFirstSelectedOption().getText();
	    Select comboBox3 = new Select(driver.findElement(By.xpath("//html/body/article/div[2]/section/div/div/div[4]/div/div/table/tbody/tr[3]/td[4]/select")));
		String status3 = comboBox3.getFirstSelectedOption().getText();
	    Select comboBox4 = new Select(driver.findElement(By.xpath("//html/body/article/div[2]/section/div/div/div[4]/div/div/table/tbody/tr[2]/td[4]/select")));
		String status4 = comboBox4.getFirstSelectedOption().getText();
		assertTrue(verifyResult(status2 ,status3 , status4));
	}
	
	@Test
	public void unmarkAttendance() throws Exception{
		driver.findElement(By.linkText("Attendance")).click();
		driver.findElement(By.linkText("Attendance Register")).click();
		Select comboBox = new Select(driver.findElement(By.id("attendance_course_search_course_id")));
		comboBox.selectByVisibleText("PreNursery");
		Thread.sleep(2000);
		Select comboBox1 = new Select(driver.findElement(By.id("student_batch_id")));
		comboBox1.selectByVisibleText("Prep - A");
		Thread.sleep(2000);
		Select comboBox2 = new Select(driver.findElement(By.xpath("//html/body/article/div[2]/section/div/div/div[4]/div/div/table/tbody/tr[1]/td[4]/select")));
		comboBox2.selectByIndex(0);
	    Select comboBox3 = new Select(driver.findElement(By.xpath("//html/body/article/div[2]/section/div/div/div[4]/div/div/table/tbody/tr[3]/td[4]/select")));
	    comboBox3.selectByIndex(0);
	    Select comboBox4 = new Select(driver.findElement(By.xpath("//html/body/article/div[2]/section/div/div/div[4]/div/div/table/tbody/tr[2]/td[4]/select")));
	    comboBox4.selectByIndex(0);
	    driver.findElement(By.id("save_attendances")).click();
		String text = driver.findElement(By.className("success")).getText();
	    verifyTextPresent(text, "ATTENDANCE MARKED SUCCESSFULLY.");
	}
	
	@Test
	public void dateSelect() throws Exception{
		driver.findElement(By.linkText("Attendance")).click();
		driver.findElement(By.linkText("Attendance Register")).click();
		Select comboBox = new Select(driver.findElement(By.id("attendance_course_search_course_id")));
		comboBox.selectByVisibleText("PreNursery");
		Thread.sleep(2000);	
		driver.findElement(By.id("period_entry")).click();
		Select comboBox1 = new Select(driver.findElement(By.xpath("html/body/div[7]/div/div/div/select[2]")));
		String currentYear = comboBox1.getFirstSelectedOption().getText();
		int currentYearInteger = Integer.parseInt(currentYear);
		String nextYear = Integer.toString(currentYearInteger + 1);
		comboBox1.selectByVisibleText(nextYear);
		driver.findElement(By.xpath("html/body/div[7]/div/div/table/tbody/tr[5]/td[4]/a")).click();	
		Select comboBox2 = new Select(driver.findElement(By.id("student_batch_id")));
		comboBox2.selectByVisibleText("Prep - A");
		Thread.sleep(2000);
		String text = driver.findElement(By.className("warning")).getText();
		verifyTextPresent(text, "YOU CAN ONLY MARK TODAY'S OR PREVIOUS DAYS ATTENDANCE!");
	}
	
	@Test
	public void verifySundayAttendance() throws Exception{
		driver.findElement(By.linkText("Attendance")).click();
		driver.findElement(By.linkText("Attendance Register")).click();
		Select comboBox = new Select(driver.findElement(By.id("attendance_course_search_course_id")));
		comboBox.selectByVisibleText("PreNursery");
		Thread.sleep(2000);	
		driver.findElement(By.id("period_entry")).click();
		driver.findElement(By.className("week-end")).click();	
		Select comboBox2 = new Select(driver.findElement(By.id("student_batch_id")));
		comboBox2.selectByVisibleText("Prep - A");
		Thread.sleep(2000);
		String text = driver.findElement(By.className("warning")).getText();
		verifyTextPresent(text, "PERIOD ENTRY NOT FOUND");
	}

	
	@Test
	public void studentProfileLink() throws Exception{
		driver.findElement(By.linkText("Attendance")).click();
		driver.findElement(By.linkText("Attendance Register")).click();
		Select comboBox = new Select(driver.findElement(By.id("attendance_course_search_course_id")));
		comboBox.selectByVisibleText("PreNursery");
		Thread.sleep(2000);	
		driver.findElement(By.id("period_entry")).click();
		driver.findElement(By.id("period_entry")).clear();
		driver.findElement(By.xpath("html/body/div[7]/div/div/table/tbody/tr[3]/td[4]/a")).click();
		Select comboBox2 = new Select(driver.findElement(By.id("student_batch_id")));
		comboBox2.selectByVisibleText("Prep - A");
		Thread.sleep(2000);
		String text = driver.findElement(By.className("warning")).getText();
		verifyTextPresent(text, "PERIOD ENTRY NOT FOUND");
	}
	
	   public Boolean verifyResult(String status2, String status3, String status4){
		   if(status2.equals(status3) && status3.equals(status4) && status4.equals(status2))
			   return true;
		   else
			   return false;	   
	   }
	
}
