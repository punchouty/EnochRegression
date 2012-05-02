package com.ezzie.enoch.infrastructure;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.ezzie.enoch.student.StudentWizardStep2;

@RunWith(Suite.class)
// specify an array of test classes
@Suite.SuiteClasses({ com.ezzie.enoch.user.SigninScreenTest.class,
		com.ezzie.enoch.student.StudentWizardStep1.class,
		com.ezzie.enoch.student.StudentWizardStep2.class })
public class AllTests {

}
