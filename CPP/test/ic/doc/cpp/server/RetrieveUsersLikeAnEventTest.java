package ic.doc.cpp.server;

import java.util.List;

import ic.doc.cpp.server.dao.EventDao;
import ic.doc.cpp.server.domain.StudentUser;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mortbay.log.Log;

public class RetrieveUsersLikeAnEventTest {

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
	public void testRetrieveUsersLikeAnEventTest() {
		EventDao eventDao = new EventDao();
		List<StudentUser> users = eventDao.retieveUsersLikeAnEvent(1L);

		users = eventDao.retieveUsersLikeAnEvent(1L);
		Log.debug("Size: "+users.size());
	}
}
