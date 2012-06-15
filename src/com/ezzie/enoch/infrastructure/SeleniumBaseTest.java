package com.ezzie.enoch.infrastructure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class SeleniumBaseTest {

	protected WebDriver driver;
	protected String baseUrl;
	protected StringBuffer verificationErrors = new StringBuffer();
	private String firstNameStudent = "student_first_name";
	private String lastNameStudent = "student_last_name";
	private String addressStudent = "student_address_line1";
	private String cityStudent = "student_city";
	private String phoneStudent = "student_phone2";
	private String firstNameEmployee = "first_name";
	private String lastNameEmployee = "employee_last_name";
	private String nextButton = "next_button";
	private String newEmployee = "#new_employee > img.first-child";

	@BeforeClass
	public static void loadConfiguration() {
		Properties prop = new Properties();
		try {
			InputStream in = new FileInputStream(new File(
					"conf/test.properties"));
			prop.load(in);
			Set<String> keys = prop.stringPropertyNames();
			for (String key : keys) {
				String value = System.getProperty(key);
				if (value == null)
					System.setProperty(key, prop.getProperty(key));
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void setUp() throws Exception {
		if (System.getProperty("browser", "Firefox")
				.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		} else if (System.getProperty("browser").equalsIgnoreCase("IE")) {
			DesiredCapabilities ieCapabilities = DesiredCapabilities
					.internetExplorer();
			ieCapabilities
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
			driver = new InternetExplorerDriver(ieCapabilities);

		} else if (System.getProperty("browser").equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		}
		baseUrl = System.getProperty("baseUrl");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		System.out.println(verificationErrorString);
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	protected boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	protected void verifyTextPresent(String source, String expected) {
		assertTrue(source.indexOf(expected) > -1);
	}

	protected String createString(int length) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			builder.append("Amelia");
		}
		return builder.toString();
	}

	protected String createAlphaNum(int length) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			builder.append("a7");
		}
		return builder.toString();
	}

	protected String createSpecialChars(int length) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			builder.append("a${");
		}
		return builder.toString();
	}

	protected String createNumber(int length) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			builder.append('9');
		}
		return builder.toString();
	}

	public void switchTOStudentWizard2() throws Exception {
		driver.findElement(By.cssSelector("#new_student > img.first-child"))
				.click();
		driver.switchTo().window("Student Admission");
		alphabetsMinLength(firstNameStudent);
		alphabetsMinLength(lastNameStudent);
		Select course = new Select(driver.findElement(By
				.id("adv_search_course_id")));
		course.selectByVisibleText("Nursery");
		Thread.sleep(1000);
		Select batch = new Select(driver.findElement(By.id("student_batch_id")));
		batch.selectByVisibleText("Nursery - A");
		Thread.sleep(1000);
		driver.findElement(By.id("next_button")).click();
	}

	public void switchToStudentWizard3() throws Exception {
		alphabetsMinLength(addressStudent);
		alphabetsMinLength(cityStudent);
		numberMaxLength(phoneStudent);
		driver.findElement(By.id("wizard_next_button")).click();
		try {
			assertTrue(isElementPresent(By.cssSelector("span.status-ok")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	public void switchToStudentWizard5BlankImage() throws Exception {
		driver.findElement(By.id("wizard_next_button")).click();
		try {
			assertTrue(isElementPresent(By.cssSelector("span.status-ok")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals(
					"Student successfully created.please check mail."
							.toUpperCase(),
					driver.findElement(
							By.cssSelector("ul.message.warning  > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	public void switchToStudentWizard5WithImage() throws Exception {
		driver.findElement(By.id("upload_image")).sendKeys(
				"C:\\Users\\Public\\Pictures\\Sample Pictures\\andesk.png");
		driver.findElement(By.id("wizard_next_button")).click();
		try {
			assertEquals(
					"Student succesfully updated. Please check mail."
							.toUpperCase(),
					driver.findElement(
							By.cssSelector("ul.message.warning  > li"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertTrue(isElementPresent(By.id("previews")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.id("wizard_next_button")).click();
		try {
			assertEquals("Successfully updated user.".toUpperCase(), driver
					.findElement(By.cssSelector("ul.message.warning  > li"))
					.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}

	public void switchToStudentUpdateUnderStudentSearch() throws Exception {
		driver.findElement(By.linkText("Student Search")).click();
		driver.findElement(By.id("target")).clear();
		driver.findElement(By.id("target")).sendKeys("Amelia");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Amelia")).click();
	}

	public void switchToStudentUpdateWithAdvancedSearch() throws Exception {
		driver.findElement(By.xpath("//img[@alt='Student-search']")).click();
		driver.findElement(By.linkText("Advanced Search")).click();
		new Select(driver.findElement(By.id("advv_search_course_id")))
				.selectByVisibleText("PreNursery");
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("student_batch_id")))
				.selectByVisibleText("Prep - A");
		driver.findElement(By.id("student_advanced_name")).clear();
		driver.findElement(By.id("student_advanced_name")).sendKeys("Amelia");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Amelia")).click();
	}
	
	public void switchToEmployeeWizard2(){
		findElementCSSSelector(newEmployee);
		driver.switchTo().window("mywindow");
		alphabetsMinLength(firstNameEmployee);
		alphabetsMinLength(lastNameEmployee);
		findElementById(nextButton);
	}
	
	public void switchToEmployeeWizard3() throws Exception{
		switchToEmployeeWizard2();
		new Select(driver.findElement(By.id("employee_employee_department_id")))
		.selectByVisibleText("Mathematics");
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("employee_employee_category_id")))
		.selectByVisibleText("Teaching");
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("employee_employee_position_id"))).selectByVisibleText("Jr.Teacher");
		driver.findElement(By.id("next_button")).click();
		findElementById(nextButton);
	}
	
	public void switchToEmployeeWizard4() throws Exception{
		switchToEmployeeWizard3();
		}

	public void alphabetsEmpty(String alphabet) {
		driver.findElement(By.id(alphabet)).clear();
		driver.findElement(By.id(alphabet)).sendKeys(createString(0));
	}

	public void alphabetsMinLength(String alphabet) {
		driver.findElement(By.id(alphabet)).clear();
		driver.findElement(By.id(alphabet)).sendKeys(createString(1));
	}

	public void alphabetMaxLength(String alphabet) {
		driver.findElement(By.id(alphabet)).clear();
		driver.findElement(By.id(alphabet)).sendKeys(createString(10));
	}

	public void alphanumericsEmpty(String alphanumerics) {
		driver.findElement(By.id(alphanumerics)).clear();
		driver.findElement(By.id(alphanumerics)).sendKeys(createAlphaNum(0));
	}

	public void alphanumericsMinLength(String alphanumerics) {
		driver.findElement(By.id(alphanumerics)).clear();
		driver.findElement(By.id(alphanumerics)).sendKeys(createAlphaNum(1));
	}

	public void alphanumericsMaxLength(String alphanumerics) {
		driver.findElement(By.id(alphanumerics)).clear();
		driver.findElement(By.id(alphanumerics)).sendKeys(createAlphaNum(51));
	}

	public void specialCharMinLength(String specialChars) {
		driver.findElement(By.id(specialChars)).clear();
		driver.findElement(By.id(specialChars)).sendKeys(createSpecialChars(1));
	}

	public void numberMinLength(String number) {
		driver.findElement(By.id(number)).clear();
		driver.findElement(By.id(number)).sendKeys(createNumber(0));
	}

	public void numberMaxLength(String number) {
		driver.findElement(By.id(number)).clear();
		driver.findElement(By.id(number)).sendKeys(createNumber(10));
	}

	public void number11Length(String number) {
		driver.findElement(By.id(number)).clear();
		driver.findElement(By.id(number)).sendKeys(createNumber(11));
	}

	public void alphabetsMinLinkText(String alphabet) {
		driver.findElement(By.xpath(alphabet)).clear();
		driver.findElement(By.xpath(alphabet)).sendKeys(createString(1));
	}

	public void alphabetsEmptyLinkText(String alphabet) {
		driver.findElement(By.xpath(alphabet)).clear();
		driver.findElement(By.xpath(alphabet)).sendKeys(createString(0));
	}

	public void alphanumericsLinkText(String alphabet) {
		driver.findElement(By.xpath(alphabet)).clear();
		driver.findElement(By.xpath(alphabet)).sendKeys(createAlphaNum(1));
	}

	public void specialCharsLinkText(String alphabet) {
		driver.findElement(By.xpath(alphabet)).clear();
		driver.findElement(By.xpath(alphabet)).sendKeys(createSpecialChars(1));
	}

	public void maxLengthLinkText(String alphabet) {
		driver.findElement(By.xpath(alphabet)).clear();
		driver.findElement(By.xpath(alphabet)).sendKeys(createString(51));
	}

	public void findElementLinkText(String alphabet) {
		driver.findElement(By.linkText(alphabet)).click();
	}

	public void findElementById(String alphabet) {
		driver.findElement(By.id(alphabet)).click();
	}

	public void findElementCSSSelector(String alphabet) {
		driver.findElement(By.cssSelector(alphabet)).click();
	}

	public void findElementXPath(String alphabet) {
		driver.findElement(By.xpath(alphabet)).click();
	}
	
	public void verifyText(Object o){
		try {
			assertEquals(o, driver
					.findElement(By.cssSelector("ul.message.warning > li"))
					.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
	public void verifyTextSuccess(Object o){
		try {
			assertEquals(o, driver
					.findElement(By.cssSelector("ul.message.success > li"))
					.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
	
public class ReadCSV {
		
		public String getValue(String scenarioName, String columnName,String fileName){
		 
		try {
		 
		// csv file containing data
			//fileName = "C:/Users/VHANDA/Desktop/data.csv";
		String strFile  =  fileName;
		String strLine   =   "";
		StringTokenizer st  =   null;
		int lineNumber   =   0;
		 
		// create BufferedReader to read csv file
		BufferedReader br = new BufferedReader(new FileReader(strFile));
		 
		strLine = br.readLine(); //read first line
		st = new StringTokenizer(strLine, ",");
		int totalRows = st.countTokens();
		 
		 
		Map<Object,String> mp=new HashMap<Object, String>();
		 
		//Fetch the header
		for(int row=0; row<totalRows; row++){
		mp.put(new Integer(row), st.nextToken());
		}
		lineNumber++;
		 
		while ((strLine = br.readLine()) != null){
		st = new StringTokenizer(strLine, ",");
		lineNumber++;
		if(st.nextToken().equalsIgnoreCase(scenarioName)){
		//Identified the row Now return the specific element based on column name specified.
		totalRows= st.countTokens();
		for(int key=1; key<=totalRows; key++){
		String value = st.nextToken();
		if(mp.get(key).equalsIgnoreCase(columnName)){
		return value;
		}
		}
		}
		}
		
		}catch (Exception e){
		System.out.println("Exception while reading csv file: " + e);
		}
		 
		return "Element Not Found";
		}
	}
public void verifyStudentSuccessfullyUpdated() throws Exception {
	try {
		assertEquals(
				"Student succesfully updated. Please check mail."
						.toUpperCase(),
				driver.findElement(
						By.cssSelector("ul.message.warning  > li"))
						.getText());
	} catch (Error e) {
		verificationErrors.append(e.toString());
	}
}

}
