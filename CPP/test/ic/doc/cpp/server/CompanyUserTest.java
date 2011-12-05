package ic.doc.cpp.server;

import ic.doc.cpp.server.dao.CompanyDao;
import ic.doc.cpp.server.dao.CompanyUserDao;
import ic.doc.cpp.server.domain.Company;
import ic.doc.cpp.server.domain.CompanyUser;
import ic.doc.cpp.server.util.Security;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mortbay.log.Log;

public class CompanyUserTest {

	private long startTimeMillis;
	
	@Before
	public void setUp() throws Exception {
		DOMConfigurator.configure("log4j.xml");
		startTimeMillis = System.currentTimeMillis();
	}

	@After
	public void tearDown() throws Exception {
	    long endTimeMillis = System.currentTimeMillis();
	    float durationSeconds = (endTimeMillis - startTimeMillis) / 1000F;
	    Log.debug("Duration: " + durationSeconds + " seconds");   
	}

	@Test
	public void testCompanyDao() {
		try {
			Log.debug("testCompanyUserDao()");
			createCompanyUser();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void createCompanyUser() {
		Log.debug("createCompanyUser()");
		CompanyUserDao companyUserDao = new CompanyUserDao();
		CompanyUser company1 = new CompanyUser();
		company1.setLogin("TestCompany");
		String password = "N0More$ecret";
		String salt = Security.randomCharString();
		String hash = Security.sha256(salt + password);
		company1.setPassword(hash);
		company1.setSalt(salt);
		company1.setFirstName("Zhouzhou");
		company1.setLastName("Du");
		company1.setGender("Male");
		company1.setEmail("john_1990@163.com");
		
		CompanyDao companyDao = new CompanyDao();
		Company company = companyDao.retrieveCompany(1L);
		
		company1.setCompany(company);
		
		companyUserDao.createUser(company1);
	}
}