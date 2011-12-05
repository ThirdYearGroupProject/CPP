package ic.doc.cpp.server;

import ic.doc.cpp.server.dao.CompanyDao;
import ic.doc.cpp.server.dao.EventCategoryDao;
import ic.doc.cpp.server.dao.EventDao;
import ic.doc.cpp.server.domain.Event;

import java.util.Date;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mortbay.log.Log;

@SuppressWarnings("deprecation")
public class NewsFeedTest {

	private long startTimeMillis;
	
	private static Date today = new Date();
    
	private static int year = today.getYear();
	private static int month = today.getMonth();
    private static int start = today.getDate() - today.getDay();

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
	public void testEventCategoryDao() {
		try {
			createTestEvent();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createTestEvent() {
		Date currentDate = new Date();
		
		Date date1 = new Date(year, month, start + 2, 11, 0, 0);
		Date _date1 = new Date(year, month, start + 2, 12, 0, 0);
		
		EventDao eventDao = new EventDao();
		EventCategoryDao eventCategoryDao = new EventCategoryDao();
		CompanyDao companyDao = new CompanyDao();
		Event event = new Event();
		event.setTitle("Microsoft Summer Internship");
		event.setCategory(eventCategoryDao.retrieveEventCategory(2L));
		event.setCompany(companyDao.retrieveCompany(1L));
		event.setPicture("companyLogo/microsoft.jpg");
		event.setCreatedTimestamp(currentDate);
		event.setUpdatedTimestamp(currentDate);
		event.setDescription("A good internship.");
		event.setStart_date(date1);
		event.setEnd_date(_date1);
		event.setWebsite("www.test.com");
		eventDao.createEvent(event);
	}

}
