package com.ezzie.enoch.employee_category;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.openqa.selenium.support.ui.Select;

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
	            System.out.println(result);
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
	
//	========================================ANU================================ //
	

public void rowCountCheck(String tableId, String tableRowPath,int col1,int col2, WebDriver driver){
	System.out.println("inside");
	WebElement table_element = driver.findElement(By.id(tableId));
    List<WebElement> tr_collection = table_element.findElements(By.xpath(tableRowPath));
    String length = Integer.toString(tr_collection.size());
    String start_time ="";
    int row_num,col_num;
    row_num=1;
    for(WebElement trElement : tr_collection){
        List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
        System.out.println(td_collection.size());
        col_num=1;
        for(WebElement tdElement : td_collection)
        {
         if(col_num == col1){  	
        	 System.out.println("this print");
        	WebElement timeField1 = tdElement.findElement(By.id("marks_exam_start_time"));
        	String timevalue = timeField1.getAttribute("value");
        	String newdate = ConvertToDate(timevalue,timevalue).toString();
        	timeField1.clear();
        	timeField1.sendKeys(newdate);
        	start_time = timeField1.getAttribute("value");
         }
		 if(col_num == col2){
			WebElement timeField2 = tdElement.findElement(By.id("marks_exam_end_time"));
		 	String timevalue1 = timeField2.getAttribute("value");
		 	String newdate1 = ConvertToDate(timevalue1,start_time).toString();
		 	timeField2.clear();
		 	timeField2.sendKeys(newdate1);   
		 	System.out.println("this is ");
		 }
        	col_num++;
        }
        row_num++;
    }
}
    

@SuppressWarnings("deprecation")
public String ConvertToDate(String dateString, String fieldvalue){
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date convertedDate = new Date();
        try {
			convertedDate = dateFormat.parse(fieldvalue);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int newTime = randomInteger(10);
		System.out.println(convertedDate.getHours());
			convertedDate.setHours(convertedDate.getHours()+newTime);
			System.out.println(convertedDate);
        DateFormat dateFormatNeeded = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        String convertedDate1 = dateFormatNeeded.format(convertedDate); 
     return convertedDate1;
}

	public String rowCountCheckOfDataTables(String tableId,String tableRow,String tableTd, int tableCol,String tagname, String href, WebDriver driver){
		System.out.println("inside");
 		WebElement table_element = driver.findElement(By.id(tableId));
		Set<String> set = new HashSet<String>();
        List<WebElement> tr_collection = table_element.findElements(By.xpath(tableRow)); 
        System.out.println(tr_collection.size());
        String idName = "";
        int row_num,col_num;
        row_num=1;
        for(WebElement trElement : tr_collection){
            List<WebElement> td_collection = trElement.findElements(By.xpath(tableTd));
            System.out.println("Td Size is " +td_collection.size());
            col_num=1;
            for(WebElement tdElement : td_collection){
            	 if(col_num == tableCol){  
            		WebElement timeField1 = tdElement.findElement(By.tagName(tagname));
            		idName = timeField1.getAttribute(href);
                    System.out.println(idName);
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
   
public Boolean verifyBooleanCondition(String currentUrl, String nextUrl){
	
	if(currentUrl.equals(nextUrl))
		return true;
	else
		return false;
	
}
public void rowCountEnterGrades(WebDriver driver){
		WebElement table_element = driver.findElement(By.id("examShow"));
       List<WebElement> tr_collection = table_element.findElements(By.xpath("id('examShow')/tbody/tr"));
       String start_time ="";
       int row_num,col_num;
       row_num=1;
       for(WebElement trElement : tr_collection){
           List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
           col_num=1;
           for(WebElement tdElement : td_collection)
           {
            if(col_num == 4){  	
           	WebElement timeField1 = tdElement.findElement(By.tagName("select"));
           	String grade = timeField1.getAttribute("id");
           	Select comboBox = new Select(driver.findElement(By.id(grade)));
    		comboBox.selectByIndex(randomInteger(5));
            }
			 if(col_num == 4){

			 }
           	col_num++;
           }
           row_num++;
       }
	}



public void rowCountEnterMarks(String tableId,String tableRow,WebDriver driver){
	WebElement table_element = driver.findElement(By.id(tableId));
    List<WebElement> tr_collection = table_element.findElements(By.xpath(tableRow));
    String start_time ="";
    int row_num,col_num;
    row_num=1;
    for(WebElement trElement : tr_collection){
        List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
        col_num=1;
        for(WebElement tdElement : td_collection)
        {
         if(col_num == 4){  	
        	WebElement timeField1 = tdElement.findElement(By.tagName("input"));
        	timeField1.sendKeys(Integer.toString(randomInteger(100)));
            System.out.println(timeField1);

         }
		 if(col_num == 4){        
		 }
        	col_num++;
        }
        row_num++;
    }
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

public String randomlyCheckCheckBox(String tableId,String tableRow,String tableTd, int tableCol,String idOrClass, String attr, WebDriver driver){
	WebElement table_element = driver.findElement(By.id(tableId));
	Set<String> set = new HashSet<String>();
    List<WebElement> tr_collection = table_element.findElements(By.xpath(tableRow));   
    int row_num,col_num;
    row_num=1;
    for(WebElement trElement : tr_collection){
        List<WebElement> td_collection = trElement.findElements(By.xpath(tableTd));
        col_num=1;
        for(WebElement tdElement : td_collection){
        	if(col_num == tableCol){
        		WebElement checks = tdElement.findElement(By.id(idOrClass));
        		Random rn = new Random();
        		int n = rn.nextInt(2);
        		if(n==1)
        			checks.click();
        		if(checks.getAttribute("checked") != null){
        			set.add(tdElement.findElement(By.id(idOrClass)).getAttribute("value"));
        		}	
        	 }
        	 col_num++;
        }
        row_num++;
    }
    return Integer.toString(set.size());
}
public void timeEntryInAdditionalExam(String tableId, String tableRowPath,int col1,int col2, WebDriver driver){
	System.out.println("inside");
	WebElement table_element = driver.findElement(By.id(tableId));
    List<WebElement> tr_collection = table_element.findElements(By.xpath(tableRowPath));
    String length = Integer.toString(tr_collection.size());
    String start_time ="";
    int row_num,col_num;
    row_num=1;
    for(WebElement trElement : tr_collection){
        List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
        System.out.println(td_collection.size());
        col_num=1;
        for(WebElement tdElement : td_collection)
        {
         if(col_num == col1){  	
        	 System.out.println("this print");
        	WebElement timeField1 = tdElement.findElement(By.className("start_time"));
        	System.out.println("time");
        	String timevalue = timeField1.getAttribute("value");
        	String newdate = ConvertToDate(timevalue,timevalue).toString();
        	timeField1.clear();
        	timeField1.sendKeys(newdate);
        	start_time = timeField1.getAttribute("value");
         }
		 if(col_num == col2){
			WebElement timeField2 = tdElement.findElement(By.className("end_time"));
		 	String timevalue1 = timeField2.getAttribute("value");
		 	String newdate1 = ConvertToDateforAdditionalExam(timevalue1,start_time).toString();
		 	timeField2.clear();
		 	timeField2.sendKeys(newdate1);   
		 	System.out.println("this is ");
		 }
        	col_num++;
        }
        row_num++;
    }
}
    

@SuppressWarnings("deprecation")
public String ConvertToDateforAdditionalExam(String dateString, String fieldvalue){
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date convertedDate = new Date();
        try {
			convertedDate = dateFormat.parse(fieldvalue);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int newTime = randomInteger(10);
		System.out.println(convertedDate.getHours());
			convertedDate.setHours(convertedDate.getHours()+newTime);
			System.out.println(convertedDate);
        DateFormat dateFormatNeeded = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        String convertedDate1 = dateFormatNeeded.format(convertedDate); 
     return convertedDate1;
}

}