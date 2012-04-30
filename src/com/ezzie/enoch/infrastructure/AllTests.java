package com.ezzie.enoch.infrastructure;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

// specify an array of test classes
@Suite.SuiteClasses({
	com.ezzie.enoch.user.SigninScreenTest.class, 
	com.ezzie.enoch.student.StudentWizardStep1.class}
)
public class AllTests {	

}
