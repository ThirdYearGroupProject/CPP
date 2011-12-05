package ic.doc.cpp.server;

import ic.doc.cpp.server.dao.CompanyDao;
import ic.doc.cpp.server.dao.EventCategoryDao;
import ic.doc.cpp.server.dao.EventDao;
import ic.doc.cpp.server.domain.Company;
import ic.doc.cpp.server.domain.Event;

import java.util.Date;
import java.util.List;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mortbay.log.Log;

@SuppressWarnings("deprecation")
public class EventTest {

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
			createEvent();
			deleteEvent();
			showCompanyEvents();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showCompanyEvents() {
		CompanyDao companyDao = new CompanyDao();
		Company company = companyDao.retrieveCompany(1L);
		List<Event> events = company.getEvents();
		for (Event e : events) {
			Log.debug(e.toString());
		}
	}

	private void deleteEvent() {
		Log.debug("Testing deleteEvent()....");
		EventDao eventDao = new EventDao();
		Event event = eventDao.retrieveEvent(4L);
		if (event != null)
			eventDao.deleteEvent(event);
	}
	
	private void createEvent() {
		Date currentDate = new Date();
		
		Date date1 = new Date(year, month, start + 2, 9, 0, 0);
		Date _date1 = new Date(year, month, start + 2, 14, 0, 0);
		
		EventDao eventDao = new EventDao();
		EventCategoryDao eventCategoryDao = new EventCategoryDao();
		CompanyDao companyDao = new CompanyDao();
		Event event = new Event();
		event.setTitle("ABC presentation");
		event.setCategory(eventCategoryDao.retrieveEventCategory(4L));
		event.setCompany(companyDao.retrieveCompany(2L));
		event.setPicture("companyLogo/abc.jpg");
		event.setCreatedTimestamp(currentDate);
		event.setUpdatedTimestamp(currentDate);
		event.setDescription("A good internship.");
		event.setStart_date(date1);
		event.setEnd_date(_date1);
		event.setWebsite("www.test.com");
		eventDao.createEvent(event);
		
		Event event1 = new Event();
		event1.setTitle("MS Summer Internship");
		event1.setCategory(eventCategoryDao.retrieveEventCategory(2L));
		event1.setCompany(companyDao.retrieveCompany(1L));
		event1.setPicture("companyLogo/morganstanley.jpg");
		event1.setCreatedTimestamp(currentDate);
		event1.setUpdatedTimestamp(currentDate);
		event1.setDescription("A good internship.");
		event1.setStart_date(date1);
		event1.setEnd_date(_date1);
		event1.setWebsite("www.test.com");
		eventDao.createEvent(event1);
		
		Event event2 = new Event();
		event2.setTitle("MS Spring Internship");
		event2.setCategory(eventCategoryDao.retrieveEventCategory(3L));
		event2.setCompany(companyDao.retrieveCompany(1L));
		event2.setPicture("companyLogo/morganstanley.jpg");
		event2.setCreatedTimestamp(currentDate);
		event2.setDescription("A good internship.");
		event2.setUpdatedTimestamp(currentDate);
		event2.setStart_date(date1);
		event2.setEnd_date(_date1);
		event2.setWebsite("www.test.com");
		eventDao.createEvent(event2);
		
		Event event3 = new Event();
		event3.setTitle("Test Deleting Event");
		event3.setCategory(eventCategoryDao.retrieveEventCategory(3L));
		event3.setCompany(companyDao.retrieveCompany(1L));
		event3.setCreatedTimestamp(currentDate);
		event3.setUpdatedTimestamp(currentDate);
		eventDao.createEvent(event3);
		
		
	}

}
