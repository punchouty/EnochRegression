package com.ezzie.enoch.test;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ChildTest extends BaseTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println(" Child class - setUpBeforeClass");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println(" Child class - tearDownAfterClass");
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		System.out.println(" Child class - setUp");
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		System.out.println(" Child class - tearDown");
	}

	@Test
	public void test1(){
		System.out.println("test1");
	}

	@Test
	public void test2(){
		System.out.println("test2");
	}
}
