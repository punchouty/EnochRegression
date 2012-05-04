package com.ezzie.enoch.infrastructure;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
// specify an array of test classes
@Suite.SuiteClasses({
		com.ezzie.enoch.user.SigninScreenTest.class,
		com.ezzie.enoch.student.StudentWizardStep1.class,
		com.ezzie.enoch.student.StudentWizardStep2.class,
		com.ezzie.enoch.student.StudentWizardStep3.class,
		com.ezzie.enoch.student.StudentWizardStep5.class,
		com.ezzie.enoch.studentnormalsearch.NormalSearchPersonalInfoUpdate.class,
		com.ezzie.enoch.studentnormalsearch.NormalSearchContactsUpdate.class,
		com.ezzie.enoch.studentnormalsearch.NormalSearchGuardianUpdate.class,
		com.ezzie.enoch.studentnormalsearch.NormalSearchHistoryUpdate.class,
		com.ezzie.enoch.studentnormalsearch.NormalSearchAttendanceReport.class,
		com.ezzie.enoch.studentnormalsearch.NormalSearchAward.class,
		com.ezzie.enoch.studentadvancedsearch.AdvancedSearchPersonalInfo.class,
		com.ezzie.enoch.studentadvancedsearch.AdvancedSearchContacts.class,
		com.ezzie.enoch.studentadvancedsearch.AdvancedSearchGuardian.class,
		com.ezzie.enoch.studentadvancedsearch.AdvancedSearchHistory.class,
		com.ezzie.enoch.studentadvancedsearch.AdvancedSearchAttendanceReport.class,
		com.ezzie.enoch.studentadvancedsearch.AdvancedSearchAward.class })
public class AllTests {

}
