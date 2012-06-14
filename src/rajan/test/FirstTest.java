package rajan.test;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class FirstTest {
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println("Inside before class");
	}
	
	@AfterClass
	public static void afterClass() {
		System.out.println("Inside after class");
	}
	
	@Before
	public void setUp(){
		System.out.println("setup");
	}
	
	@After
	public void tearDown() {
		System.out.println("tear down");
	}
	
	@Test
	public void sizeOfEmptyArrayList() {
		System.out.println("in side test");
		ArrayList<String> list = new ArrayList<String>();
		Assert.assertEquals(0, list.size());
	}
	
	@Test
	public void somethingElse() {
		System.out.println("somethingElse");
		ArrayList<String> list = new ArrayList<String>();
		Assert.assertEquals(0, list.size());
	}

}
