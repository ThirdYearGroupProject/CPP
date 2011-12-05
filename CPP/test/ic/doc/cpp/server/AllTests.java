package ic.doc.cpp.server;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CompanyCategoryTest.class, 
		EventCategoryTest.class, CompanyTest.class, EventTest.class, StudentUserTest.class, CompanyUserTest.class})
public class AllTests {

}
