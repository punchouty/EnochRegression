package com.ezzie.enoch.employee_categories;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ezzie.enoch.infrastructure.SeleniumBaseTest;

public class Common extends SeleniumBaseTest{
	
	
	public Boolean verifyDependancyPresence(String class_name, String message,WebDriver driver){
			String msg = driver.findElement(By.cssSelector("ul.error li")).getText();
			return verifyEquality(message,msg);
	}
	
	public String returnClassName(WebDriver driver){
		String classname = "";
		if(isElementPresents(By.className("success") , driver)){
			classname = "success";
		}else if(isElementPresents(By.className("error"), driver)){
			classname = "error";
		}else{
			classname = "warning";
		}
		return classname;
	}
	
	public String returnIdValue(String id){
		int length = id.length();
	    String result = "";
	    for (int i = 0; i < length; i++) {
	        Character character = id.charAt(i);
	        if (Character.isDigit(character)) {
	            result += character;
	        }
	    }
	    return result;
	}
	
	public int returnTableRowCount(String tableId,String tableRow,WebDriver driver){
		WebElement table_element = driver.findElement(By.id(tableId));
        List<WebElement> tr_collection = table_element.findElements(By.xpath(tableRow));
        return tr_collection.size();
	}
	
	public boolean verifyEquality(String expected, String actual){
		if(expected.equals(actual))
			return true;
		else
			return false;	
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
	
	public Map returnHrefValue(String tableId,String tableRow,String tableTd, int tableCol,String tagName, String attr, WebDriver driver){
		WebElement table_element = driver.findElement(By.id(tableId));
		Set<String> set = new HashSet<String>();
		Map<String,String> map = new LinkedHashMap<String,String>();
        List<WebElement> tr_collection = table_element.findElements(By.xpath(tableRow));      
        String idName = "";
        String idText = "";
        int row_num,col_num;
        row_num=1;
        for(WebElement trElement : tr_collection){
            List<WebElement> td_collection = trElement.findElements(By.xpath(tableTd));
            col_num=1;
            for(WebElement tdElement : td_collection){
            	 if(col_num == tableCol){
            		WebElement timeField1 = tdElement.findElement(By.tagName(tagName));
            		idName = timeField1.getAttribute(attr);
            		idText = timeField1.getText();
            		map.put(idName, idText);
                    set.add(idName);
            	 }
            	 col_num++;
            }
            row_num++;
        }
        return map;
	}
	
	public int randomInteger(int number){
		Random rn = new Random();
		int n = rn.nextInt(number);
		if(n<0)
			 n = -n;
		if(n==0)
			  n= n+1;
		return new Integer(n);
	}
	
	
	public String randomstring(int lo, int hi){  
        int n = rand(lo, hi);  
        char b[] = new char[n];  
        for (int i = 0; i < n; i++)  
                b[i] = (char)rand('a', 'z');  
        return new String(b);  
	}  

	private int rand(int lo, int hi){  
            java.util.Random rn = new java.util.Random();  
        int n = hi - lo + 1;  
        int i = rn.nextInt(n);  
        if (i < 0)  
                i = -i;  
        return lo + i;  
	}  

	public String randomstring(){  
        return randomstring(2, 15);  
	}
	
	public boolean isElementPresents(By by,WebDriver driver) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
}
