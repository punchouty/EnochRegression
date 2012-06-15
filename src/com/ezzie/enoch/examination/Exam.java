package com.ezzie.enoch.examination;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.ezzie.enoch.employee_categories.Common;
import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class Exam extends LoggedInUserTest{ 
    Common common = new Common();
	RandomInteger randominteger;
	RandomString randomstring;


	@Before
	public void setUp() throws Exception{
		 super.setUp();
	}
	
	@After
	public void tearDown() throws Exception{
//		super.tearDown();
	}

	@Test
	public void checkCourseSelection() throws Exception{
		driver.findElement(By.linkText("Exams")).click();
		driver.findElement(By.id("exacreate")).click();
		String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		verifyTextPresent(warning,"PLEASE SELECT COURSE");	
	}
	
	@Test
	public void checkBatchSelection() throws Exception{
		driver.findElement(By.linkText("Exams")).click();
		Select comboBox = new Select(driver.findElement(By.id("courses_name")));
		comboBox.selectByVisibleText("Nursery");
		driver.findElement(By.id("exacreate")).click();
		String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		verifyTextPresent(warning,"PLEASE SELECT BATCH");
	}
	
	@Test
    public void checkBackButtonOnFirstPage() throws Exception {
		Thread.sleep(3000);
		String currentUrl = driver.getCurrentUrl().toString();
		driver.findElement(By.linkText("Exams")).click();
		driver.findElement(By.xpath(".//*[@id='outer_block']/div/div[1]/ul/li/a/img")).click();
		Thread.sleep(1000);
		verifyTextPresent(currentUrl, "http://demo.ezzie.in/dashboard");
    }
		
	@Test
	public void clickNextButton() throws Exception{
		driver.findElement(By.linkText("Exams")).click();
		Select comboBox = new Select(driver.findElement(By.id("courses_name")));
		comboBox.selectByVisibleText("Nursery");
		Thread.sleep(2000);
		Select combBox = new Select(driver.findElement(By.id("exams_batch_id")));
		combBox.selectByIndex(1);
		String currentUrl = driver.getCurrentUrl().toString();
		driver.findElement(By.id("exacreate")).click();
		String nextUrl = driver.getCurrentUrl().toString();
		assertFalse(common.verifyEquality(currentUrl,nextUrl));
		verifyTextPresent(nextUrl, "http://demo.ezzie.in/exam_groups/exam_group_index");
    }

	@Test
	public void checkBackButtonONSecondPage() throws Exception {
		driver.findElement(By.linkText("Exams")).click();
		Select comboBox = new Select(driver.findElement(By.id("courses_name")));
		comboBox.selectByVisibleText("Nursery");
		Thread.sleep(2000);
		Select combBox = new Select(driver.findElement(By.id("exams_batch_id")));
		combBox.selectByIndex(1);
		String currentUrl = driver.getCurrentUrl().toString();
		driver.findElement(By.id("exacreate")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='outer_bloc']/div/div[1]/ul/li/a/img")).click();
		Thread.sleep(1000);
		String backUrl = driver.getCurrentUrl().toString();
		verifyTextPresent(currentUrl , backUrl);
	}
	
	
    public void next_linktext() throws Exception{
		
    	driver.findElement(By.linkText("Exams")).click();
    	System.out.println("first click");
    	Select comboBox = new Select(driver.findElement(By.id("courses_name")));
    	comboBox.selectByVisibleText("Nursery");
    	Thread.sleep(2000);
    	Select combBox = new Select(driver.findElement(By.id("exams_batch_id")));
    	combBox.selectByIndex(1);
    	driver.findElement(By.id("exacreate")).click();
    	driver.findElement(By.xpath("html/body/article/div[2]/section/div/div/fieldset/p/input[1]")).click();
    }
	
	@Test
	public void linkTest_save() throws Exception{
	next_linktext();
	driver.findElement(By.id("new_exam")).click();
	String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
	System.out.println(warning);
	verifyTextPresent(warning, "PLEASE ENTER NAME");
	} 
	
	@Test
	public void checkBackButtonOnThirdPage() throws Exception {
		driver.findElement(By.linkText("Exams")).click();
    	Select comboBox = new Select(driver.findElement(By.id("courses_name")));
    	comboBox.selectByVisibleText("Nursery");
    	Thread.sleep(2000);
    	Select combBox = new Select(driver.findElement(By.id("exams_batch_id")));
    	combBox.selectByIndex(1);
    	driver.findElement(By.id("exacreate")).click();
    	Thread.sleep(2000);
      
		driver.findElement(By.xpath(".//*[@id='outer_bloc']/div/fieldset/p/input[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//*[@id='outer_block']/div/div[1]/ul/li/a/img")).click();
	     assertTrue(isElementPresent(By.id("examgroupindex")));
		 }
	
	@Test
	public void checkNexturlpage() throws Exception {
	next_linktext();
    driver.findElement(By.id("exam_option_name")).clear();
    driver.findElement(By.id("exam_option_name")).sendKeys(randomstring.randomstring());
    Select comboBoX = new Select(driver.findElement(By.id("exam_option_exam_type")));
    comboBoX.selectByIndex(0);
    driver.findElement(By.id("new_exam")).click();
    assertTrue(isElementPresent(By.name("exam_group[minimum_marks]")));
   }
	@Test
	public void enterValuesInName() throws Exception {
		next_linktext();
	    driver.findElement(By.id("exam_option_name")).clear();
	    driver.findElement(By.id("exam_option_name")).sendKeys(randomstring.randomstring());
	    Select comboBox = new Select(driver.findElement(By.id("exam_option_exam_type")));
	    comboBox.selectByIndex(0);
	    driver.findElement(By.id("new_exam")).click();	
	}
	
	
	@Test
    public void empty_maximum_marks() throws Exception{
    enterValuesInName();
    driver.findElement(By.id("exam_group_maximum_marks")).clear();
    driver.findElement(By.id("exam_group_maximum_marks")).click();
    driver.findElement(By.id("exam_group_minimum_marks")).sendKeys("34"); 
    driver.findElement(By.id("create_exam")).click();
    String message = driver.findElement(By.cssSelector("ul.error li li")).getText();
    System.out.println(message);
    verifyTextPresent(message, "EXAMS MAXIMUM MARKS CAN'T BE BLANK");
	}
	
	@Test
    public void enterMaximumMmarks() throws Exception{
	enterValuesInName();
    driver.findElement(By.id("exam_group_maximum_marks")).clear();
    driver.findElement(By.id("exam_group_maximum_marks")).sendKeys("45");
    driver.findElement(By.id("exam_group_minimum_marks")).clear(); 
//    driver.findElement(By.id("marks_exam_start_time")).clear();
//    driver.findElement(By.id("marks_exam_start_time")).sendKeys(rowCountCheck());
//    driver.findElement(By.id("marks_exam_end_time")).clear();
//    driver.findElement(By.id("marks_exam_end_time")).sendKeys("2012-05-26 07:46:53 +0000");
    driver.findElement(By.id("create_exam")).click();
    String message = driver.findElement(By.cssSelector("ul.error li li")).getText();
    System.out.println(message);
    verifyTextPresent(message, "EXAMS MINIMUM MARKS CAN'T BE BLANK");
	}
	
	@Test
    public void enterMarksmaximum_marks() throws Exception{
		enterValuesInName();
    driver.findElement(By.id("exam_group_maximum_marks")).clear();
    driver.findElement(By.id("exam_group_maximum_marks")).sendKeys("100");
    driver.findElement(By.id("exam_group_minimum_marks")).sendKeys("34"); 
    rowCountCheck();
    driver.findElement(By.id("create_exam")).click();
    String message = driver.findElement(By.cssSelector("ul.error li li")).getText();
    System.out.println(message);
    verifyTextPresent(message, "EXAMS MAXIMUM MARKS CAN'T BE BLANK");
	}
	
	@Test
	public void enter_marks_next() throws Exception{
	driver.findElement(By.linkText("Exams")).click();
	Select comboBox = new Select(driver.findElement(By.id("courses_name")));
	comboBox.selectByVisibleText("Nursery");
	Thread.sleep(2000);
	Select combBox = new Select(driver.findElement(By.id("exams_batch_id")));
	combBox.selectByVisibleText("Nursery - A");
    driver.findElement(By.id("exacreate")).click();
    Thread.sleep(2000);
	System.out.println("hfrush");
	assertTrue(isElementPresent(By.id("examGroupShow")));

	 }
	
	@Test
	public void urlScreen_marks_InTopic() throws Exception{
		enter_marks_next();
		System.out.println("this us");
		driver.findElement(By.linkText("Addition")).click();
		Thread.sleep(2000);
		assertTrue(isElementPresent(By.id("examShow")));
		driver.findElement(By.id("exam_19_marks")).sendKeys(Integer.toString(RandomInteger.randomInteger()));
		driver.findElement(By.id("exam_20_marks")).sendKeys(Integer.toString(RandomInteger.randomInteger()));
		driver.findElement(By.id("exam_21_marks")).sendKeys(Integer.toString(RandomInteger.randomInteger()));
		driver.findElement(By.id("exam_22_marks")).sendKeys(Integer.toString(RandomInteger.randomInteger()));
		driver.findElement(By.id("exam_23_marks")).sendKeys(Integer.toString(RandomInteger.randomInteger()));
		driver.findElement(By.id("exam_24_marks")).sendKeys(Integer.toString(RandomInteger.randomInteger()));
		driver.findElement(By.xpath(".//*[@id='main_block']/div/div/form/fieldset/p/input")).click();
		String success = driver.findElement(By.cssSelector("ul.success")).getText();
		System.out.println(success);
		verifyTextPresent(success, "EXAM SCORES UPDATED.");
	}

		@Test
	public void connect_exam_group() throws Exception{
		driver.findElement(By.linkText("Exams")).click();
		Select comboBox = new Select(driver.findElement(By.id("courses_name")));
		comboBox.selectByVisibleText("Nursery");
		Thread.sleep(2000);
		Select combBox = new Select(driver.findElement(By.id("exams_batch_id")));
		combBox.selectByIndex(1);
		driver.findElement(By.id("exacreate")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='outer_bloc']/div/fieldset/p/input[2]")).click();
		assertTrue(isElementPresent(By.id("view_group")));
	}
		@Test
	public void emptyInGroupName() throws Exception {
	connect_exam_group();
	Thread.sleep(1000);
	driver.findElement(By.xpath(".//*[@id='outer_bloc']/div/fieldset/p/input")).click();
	driver.findElement(By.id("connect_save")).click();
	String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
	System.out.println(warning);
	verifyTextPresent(warning, "PLEASE ENTER GROUPED EXAM NAME");
	}  
		
		@Test
		public void enterNumericInGroupName() throws Exception {
		connect_exam_group();
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//*[@id='outer_bloc']/div/fieldset/p/input")).click();
		driver.findElement(By.id("exam_grouping_grouped_exam_name")).clear();
		driver.findElement(By.id("exam_grouping_grouped_exam_name")).sendKeys(createNumber(9));
		driver.findElement(By.id("connect_save")).click();
		String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		System.out.println(warning);
		verifyTextPresent(warning, "PLEASE ENTER CHARACTER");
		}  
		
		@Test 
		public void enterSpecialCharacterInGroupNmae() throws Exception {
	    connect_exam_group();
		Thread.sleep(1000);	
		driver.findElement(By.xpath(".//*[@id='outer_bloc']/div/fieldset/p/input")).click();
		driver.findElement(By.id("exam_grouping_grouped_exam_name")).clear();
		driver.findElement(By.id("exam_grouping_grouped_exam_name")).sendKeys(createSpecialChars(6));
		driver.findElement(By.id("connect_save")).click();
		String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
		System.out.println(warning);
		verifyTextPresent(warning, "SPECIAL CHARACTERS ARE NOT ALLOWED FOR GROUPED EXAM NAME");
		}
         @Test
        public void clickOnexamGroup() throws Exception{
		driver.findElement(By.linkText("Exams")).click();
		Select comboBox = new Select(driver.findElement(By.id("courses_name")));
		comboBox.selectByVisibleText("Nursery");
		Thread.sleep(2000);
		Select combBox = new Select(driver.findElement(By.id("exams_batch_id")));
		combBox.selectByIndex(1);
	    driver.findElement(By.id("exacreate")).click();
	    String dd = rowCountCheckOfDataTables("examgroupindex", "id('examgroupindex')/tbody/tr", "td", 2,"a","href",driver);
	    driver.get(dd);
    }
         @Test
         public void checkBackButtonOnSubjectPage() throws Exception {
        	driver.findElement(By.linkText("Exams")).click();
     		Select comboBox = new Select(driver.findElement(By.id("courses_name")));
    		comboBox.selectByVisibleText("Nursery");
    		Thread.sleep(2000);
    		Select combBox = new Select(driver.findElement(By.id("exams_batch_id")));
    		combBox.selectByIndex(1);
    	    driver.findElement(By.id("exacreate")).click();
    	    String dd = rowCountCheckOfDataTables("examgroupindex", "id('examgroupindex')/tbody/tr", "td", 2,"a","href",driver);
    	    driver.get(dd);
    	    driver.findElement(By.xpath(".//*[@id='outer_bloc']/div/div[1]/ul/li/a")).click();
            assertTrue(isElementPresent(By.id("examgroupindex")));
         }
         
        @Test
        public void clickOnTopicName() throws Exception{
        	clickOnexamGroup();
        	String ds = rowCountCheckOfDataTables("examGroupShow","id('examGroupShow')/tbody/tr", "td", 3,"a","href",driver);
            driver.get(ds);
      }
        
        @Test
        public void deleteSubjectTopicExam() throws Exception{
        	clickOnexamGroup();
        	System.out.println("this is delete");
        	String ds = rowCountCheckOfDataTable("examGroupShow","id('examGroupShow')/tbody/tr", "td", 6,"a","exam_id",driver);
        	driver.get(ds);
        	driver.findElement(By.xpath(".//*[@id='modal']/div/div[1]/div[2]/button[1]")).click();
        	String success = driver.findElement(By.cssSelector("ul.success")).getText();
        	
        	System.out.println(success);
        	verifyTextPresent(success, "EXAM DELETED SUCCESSFULY");
        	
        }
        @Test
        public void checkBackButtonOnResultEnteryPage() throws Exception{
        	clickOnTopicName();
        	driver.findElement(By.xpath(".//*[@id='main_block']/div/div/div[1]/ul/li/a/img")).click();
        	assertTrue(isElementPresent(By.id("examGroupShow")));
        	
        }
        
     	public String rowCountCheckOfDataTables(String tableId,String tableRow,String tableTd, int tableCol,String tagname, String href, WebDriver driver){
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
                		WebElement timeField1 = tdElement.findElement(By.tagName(tagname));
                		idName = timeField1.getAttribute(href);

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

			
	
	public void rowCountCheck(){
		WebElement table_element = driver.findElement(By.id("exammf"));
        List<WebElement> tr_collection = table_element.findElements(By.xpath("id('exammf')/tbody/tr"));
//        String length = Integer.toString(tr_collection.size());
        String start_time ="";
        int row_num,col_num;
        row_num=1;
        for(WebElement trElement : tr_collection){
            List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
            col_num=1;
            for(WebElement tdElement : td_collection)
            {
             if(col_num == 6){  	
            	WebElement timeField1 = tdElement.findElement(By.id("marks_exam_start_time"));
            	String timevalue = timeField1.getAttribute("value");
            	String newdate = ConvertToDate(timevalue,timevalue).toString();
            	timeField1.clear();
            	timeField1.sendKeys(newdate);
            	start_time = timeField1.getAttribute("value");
             }
			 if(col_num == 7){
				WebElement timeField2 = tdElement.findElement(By.id("marks_exam_end_time"));
			 	String timevalue1 = timeField2.getAttribute("value");
			 	String newdate1 = ConvertToDate(timevalue1,start_time).toString();
			 	timeField2.clear();
			 	timeField2.sendKeys(newdate1);           
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
			int newTime = RandomInteger.randomInteger();
			System.out.println(convertedDate.getHours());
				convertedDate.setHours(convertedDate.getHours()+newTime);
				System.out.println(convertedDate);
	        DateFormat dateFormatNeeded = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	        String convertedDate1 = dateFormatNeeded.format(convertedDate); 
	     return convertedDate1;
	}
	public String rowCountCheckOfDataTable(String tableId,String tableRow,String tableTd, int tableCol,String id, String attr, WebDriver driver){
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
            		WebElement timeField1 = tdElement.findElement(By.id(id));
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
	
	public String returnIdValue(String IdValue){
		String result ="";
		int length = IdValue.length();
		for(int i =0; i<length;i++)
		{
			Character character = IdValue.charAt(i);
			if(Character.isDigit(character)){
				result += character;
			}
		}
		return result;
	}
}