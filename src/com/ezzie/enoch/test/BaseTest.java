package com.ezzie.enoch.test;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class BaseTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println(" Base class - setUpBeforeClass");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println(" Base class - tearDownAfterClass");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println(" Base class - setUp");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println(" Base class - tearDown");
	}

}
