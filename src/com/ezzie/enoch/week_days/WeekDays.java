package com.ezzie.enoch.week_days;

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

import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class WeekDays extends LoggedInUserTest{
	@Before
	public void setup() throws Exception {
         super.setUp();		
	}
	 @After
	 public void tearDown() throws Exception {
		  super.tearDown();
	 }
	 @Test
	 public void selectCourseAndBatchInAddWeekday() throws Exception{
		 
		 
		 
		 
	 }

	 
	 public String selectCheckBox(String tableId,String tableRow,String tableTd) {
		   int rowsize1 = 0;
		   for(int i = 0; i<=rowsize1; i++){
			 
		 }
		 
		return null;
		 
	 }
//	 public String rowCountCheckOfDataTables(String tableId,String tableRow,String tableTd, int tableCol,String tagname, String href, WebDriver driver){
// 		WebElement table_element = driver.findElement(By.id(tableId));
// 		Set<String> set = new HashSet<String>();
//         List<WebElement> tr_collection = table_element.findElements(By.xpath(tableRow));      
//         String idName = "";
//         int row_num,col_num;
//         row_num=1;
//         for(WebElement trElement : tr_collection){
//             List<WebElement> td_collection = trElement.findElements(By.xpath(tableTd));
//             col_num=1;
//             for(WebElement tdElement : td_collection){
//             	 if(col_num == tableCol){  
//             		WebElement timeField1 = tdElement.findElement(By.tagName(tagname));
//             		idName = timeField1.getAttribute(href);
//
//                     set.add(idName);
//             	 }
//             	 col_num++;
//             }
//             row_num++;
//         }
// 		Random rand = new Random();
// 		int dd = rand.nextInt(set.size());
//         return set.toArray()[dd].toString();
// 	}

	 
	 
}
