package com.ezzie.enoch.examination;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ezzie.enoch.infrastructure.LoggedInUserTest;

public class GradingSystem extends LoggedInUserTest{
	RandomInteger randomInteger;
	RandomString randomstring;
@Before
  public void setUp() throws Exception{
	  super.setUp();
  }
  
  @After
  public void tearDown() throws Exception{
//	  super.tearDown();
  }
//  @Test
//  public void empty_grade_name() throws Exception {
//	  driver.findElement(By.linkText("Grading System")).click();
//	  driver.findElement(By.id("grading_level_group_grading_level_group_name")).clear();
//	  driver.findElement(By.id("create_grading_level_group")).click();
//	  String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
//	  System.out.println(warning);
//	  verifyTextPresent(warning, "PLEASE ENTER GRADING LEVEL GROUP NAME");
//  }
  
// @Test
// public void numeric_grade_name() throws Exception {
//	 driver.findElement(By.linkText("Grading System")).click();
//	 driver.findElement(By.id("grading_level_group_grading_level_group_name")).clear();
//	 driver.findElement(By.id("grading_level_group_grading_level_group_name")).sendKeys(Integer.toString(randomInteger.randomInteger()));
//	 driver.findElement(By.id("create_grading_level_group")).click();
//	 String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
//	 System.out.println(warning);
//	 verifyTextPresent(warning, "PLEASE ENTER CHARACTERS FOR NAME");
// }
//  @Test
//  public void alphanumeric_grade_name() throws Exception {
// 	 driver.findElement(By.linkText("Grading System")).click();
// 	 driver.findElement(By.id("grading_level_group_grading_level_group_name")).clear();
// 	 driver.findElement(By.id("grading_level_group_grading_level_group_name")).sendKeys("group12");
// 	 driver.findElement(By.id("create_grading_level_group")).click();
// 	 String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
// 	 System.out.println(warning);
// 	 verifyTextPresent(warning, "PLEASE ENTER CHARACTERS FOR NAME");
//  }
  
 @Test
 public void crate_group_grade_name() throws Exception {
	 driver.findElement(By.linkText("Grading System")).click();
	 driver.findElement(By.id("grading_level_group_grading_level_group_name")).clear();
	 driver.findElement(By.id("grading_level_group_grading_level_group_name")).sendKeys(randomstring.randomstring());
	 driver.findElement(By.id("create_grading_level_group")).click();
	 String success = driver.findElement(By.cssSelector("ul.success")).getText();
	 System.out.println(success);
	 verifyTextPresent(success, "GRADING LEVEL GROUP WAS SUCCESSFULLY CREATED.");
 }
 
//@Test
//public void update_grade_name() throws Exception {
//	 driver.findElement(By.linkText("Grading System")).click();
//	 driver.findElement(By.id("update-href-7")).click();
//	 driver.findElement(By.id("grading_level_group_grading_level_group_name")).clear();
//	 driver.findElement(By.id("grading_level_group_grading_level_group_name")).sendKeys("groupone");
//	 driver.findElement(By.id("update_grading_level_group")).click();
//	 String success = driver.findElement(By.cssSelector("ul.success")).getText();
//	 System.out.println(success);
//	 verifyTextPresent(success, "GRADING LEVEL GROUP WAS UPDATED SUCCESSFULLY.");
//}

//@Test
//public void delet_grade_name() throws Exception {
//	 driver.findElement(By.linkText("Grading System")).click();
//	 driver.findElement(By.id("delete-href-9")).click();
//	 driver.findElement(By.xpath(".//*[@id='modal']/div/div[1]/div[2]/button[1]")).click();
//	 String success = driver.findElement(By.cssSelector("ul.success")).getText();
//	 System.out.println(success);
//	 verifyTextPresent(success, "GRADE WAS DELETED SUCCESSFULLY.");
//}
//@Test
//public void active_toinactive_grade_name() throws Exception {
//	 driver.findElement(By.linkText("Grading System")).click();
//	 driver.findElement(By.id("update-href-11")).click();
//	 driver.findElement(By.id("grading_level_group_grading_level_group_name")).clear();
//	 driver.findElement(By.id("grading_level_group_grading_level_group_name")).sendKeys("groxcxupone");
//	 driver.findElement(By.id("grading_level_group_is_active")).click();
//	 driver.findElement(By.id("update_grading_level_group")).click();
//	 String success = driver.findElement(By.cssSelector("ul.success")).getText();
//	 System.out.println(success);
//	 verifyTextPresent(success, "GRADING LEVEL GROUP WAS UPDATED SUCCESSFULLY.");
//}
@Test
public void empty_gradeName_add_grading_level_detail() throws Exception {
	 driver.findElement(By.linkText("Grading System")).click();
	 driver.findElement(By.id("add-grading-level-details-href")).click();
	 driver.findElement(By.id("grading_level_min_score")).clear();
	 driver.findElement(By.id("grading_level_min_score")).sendKeys("54");
	 driver.findElement(By.xpath(".//*[@id='modal']/div/div[1]/div[2]/button[1]")).click();
	 String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
	 System.out.println(warning);
	 verifyTextPresent(warning, "GRADING LEVEL DETAIL NAME CAN'T BE BLANK");
}
  
@Test
public void empty_Mini_score_add_grading_level_detail() throws Exception {
	 driver.findElement(By.linkText("Grading System")).click();
	 rowCountCheckOfgradingYstem();
	 String dd =  rowCountCheckOfgradingYstem();
	 System.out.println(dd);
	 driver.findElement(By.xpath(".//*[@id='add-grading-level-details-href' and @grade-level-group="+dd+"]")).click();
	 driver.findElement(By.id("grading_level_name")).clear();
	 driver.findElement(By.id("grading_level_name")).sendKeys(randomstring.randomstring());
	 driver.findElement(By.id("grading_level_min_score")).clear();
     driver.findElement(By.xpath(".//*[@id='modal']/div/div[1]/div[2]/button[1]")).click();
	 String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
	 System.out.println(warning);
	 verifyTextPresent(warning, "MIN SCORE CAN'T BE BLANK");
} 

@Test

	public void create_AddGrading_nameDetail() throws Exception{
	 driver.findElement(By.linkText("Grading System")).click();
	 rowCountCheckOfgradingYstem();
	 String dd =  rowCountCheckOfgradingYstem();
	 System.out.println(dd);
	 driver.findElement(By.xpath(".//*[@id='add-grading-level-details-href' and @grade-level-group="+dd+"]")).click();
	 driver.findElement(By.id("grading_level_name")).clear();
	 driver.findElement(By.id("grading_level_name")).sendKeys(randomstring.randomstring());
	 driver.findElement(By.id("grading_level_min_score")).clear();
	 driver.findElement(By.id("grading_level_min_score")).sendKeys(Integer.toString(randomInteger.randomInteger()));
     driver.findElement(By.xpath(".//*[@id='modal']/div/div[1]/div[2]/button[1]")).click();
	 String msg = driver.findElement(By.cssSelector("ul.success")).getText();
	 System.out.println(msg);
	 verifyTextPresent(msg, "GRADING LEVEL DETAIL WAS CREATED SUCCESSFULLY");
}


//{warning message is not display}
@Test 
public void alphabets_Mini_score_add_grading_level_detail() throws Exception {
	 driver.findElement(By.linkText("Grading System")).click();
	 rowCountCheckOfgradingYstem();
	 String dd =  rowCountCheckOfgradingYstem();
	 System.out.println(dd);
	 driver.findElement(By.xpath(".//*[@id='add-grading-level-details-href' and @grade-level-group="+dd+"]")).click();
	 driver.findElement(By.id("grading_level_name")).clear();
	 driver.findElement(By.id("grading_level_name")).sendKeys(randomstring.randomstring());
	 driver.findElement(By.id("grading_level_min_score")).clear();
	 driver.findElement(By.id("grading_level_min_score")).sendKeys("Artrer");
	 driver.findElement(By.xpath(".//*[@id='modal']/div/div[1]/div[2]/button[1]")).click();
	 String warning = driver.findElement(By.cssSelector("ul.warning")).getText();
	 System.out.println(warning);
	 verifyTextPresent(warning, "GRADING LEVEL GROUP WAS UPDATED SUCCESSFULLY.");
}
@Test
public void check_back_button() throws Exception {
	  driver.findElement(By.linkText("Grading System")).click();
	  String currentUrl = driver.getCurrentUrl().toString();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath(".//*[@id='outer_block']/div/div/ul/li/a/img")).click();
	  Thread.sleep(2000);
	  verifyTextPresent(currentUrl, "http://demo.ezzie.in/dashboard");
}

public String rowCountCheckOfgradingYstem(){
	WebElement table_element = driver.findElement(By.id("active-table"));
	Set<String> set = new HashSet<String>();
    List<WebElement> tr_collection = table_element.findElements(By.xpath("id('active-table')/tbody/tr"));
    int rowlength = tr_collection.size();
    String timevalue = "";
    String id[] = new String[rowlength];
    int row_num,col_num;
    row_num=1;
    for(WebElement trElement : tr_collection){
        List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
        col_num=1;
        for(WebElement tdElement : td_collection){
        	 if(col_num == 3){  
        		WebElement timeField1 = tdElement.findElement(By.className("add-grading-level-detail-href"));
             	timevalue = timeField1.getAttribute("grade-level-group");
                	set.add(timevalue);
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
	
  
