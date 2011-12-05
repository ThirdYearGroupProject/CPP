package ic.doc.cpp.server.util;

import ic.doc.cpp.server.dao.CompanyDao;
import ic.doc.cpp.server.dao.CompanyUserDao;
import ic.doc.cpp.server.dao.EventDao;
import ic.doc.cpp.server.dao.StudentUserDao;
import ic.doc.cpp.server.domain.Company;
import ic.doc.cpp.server.domain.CompanyUser;
import ic.doc.cpp.server.domain.Event;
import ic.doc.cpp.server.domain.StudentUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.inject.Provider;

public class GetEntityThroughDao {
	public static StudentUser getStudentUser(final Provider<HttpServletRequest> provider) {
		HttpSession session = provider.get().getSession();
		String login = session.getAttribute("login.authenticated").toString();
		StudentUserDao studentDao = new StudentUserDao();
		return studentDao.retrieveUser(login);
	}
	
	public static CompanyUser getCompanyUser(final Provider<HttpServletRequest> provider) {
		HttpSession session = provider.get().getSession();
		String login = session.getAttribute("login.authenticated").toString();
		CompanyUserDao comapnyDao = new CompanyUserDao();
		return comapnyDao.retrieveUser(login);
	}
	
	public static Company getCompany(Long companyId) {
		CompanyDao companyDao = new CompanyDao();
		return companyDao.retrieveCompany(companyId);
	}
	
	public static Event getEvent(Long eventId) {
		EventDao eventDao = new EventDao();
		return eventDao.retrieveEvent(eventId);
	}
}
