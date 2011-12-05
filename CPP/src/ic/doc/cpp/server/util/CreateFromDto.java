package ic.doc.cpp.server.util;

import ic.doc.cpp.server.dao.CompanyCategoryDao;
import ic.doc.cpp.server.dao.CompanyDao;
import ic.doc.cpp.server.dao.EventDao;
import ic.doc.cpp.server.domain.Company;
import ic.doc.cpp.server.domain.CompanyCategory;
import ic.doc.cpp.server.domain.Event;
import ic.doc.cpp.shared.dto.CompanyCategoryDto;
import ic.doc.cpp.shared.dto.CompanyDto;
import ic.doc.cpp.shared.dto.EventDto;

import java.util.ArrayList;
import java.util.List;

public class CreateFromDto {

	public static List<Company> getCompanysFromDtos(List<CompanyDto> companyDtos) {
		List<Company>  companys = new ArrayList<Company>(companyDtos.size());
		for (CompanyDto c : companyDtos) {
			companys.add(getCompanyFromDto(c));
		}
		return companys;
	}

	private static Company getCompanyFromDto(CompanyDto c) {
		CompanyDao companyDao = new CompanyDao();
		return companyDao.retrieveCompany(c.getCompanyId());
	}

	public static List<Event> getEventsFromDtos(List<EventDto> eventDtos) {
		List<Event>  events = new ArrayList<Event>(eventDtos.size());
		for (EventDto e : eventDtos) {
			events.add(getEventFromDto(e));
		}
		return events;
	}

	private static Event getEventFromDto(EventDto e) {
		EventDao eventDao = new EventDao();
		return eventDao.retrieveEvent(e.getEventId());
	}

	public static List<CompanyCategory> getCompanyCategoryFromDto(
			List<CompanyCategoryDto> interestedAreaDtos) {
		List<CompanyCategory> categorys = new ArrayList<CompanyCategory>();
		for (CompanyCategoryDto c : interestedAreaDtos) {
			categorys.add(getCompanyCategoryFromDto(c));
		}
		return null;
	}

	private static CompanyCategory getCompanyCategoryFromDto(
			CompanyCategoryDto c) {
		CompanyCategoryDao companyCategoryDao = new CompanyCategoryDao();
		CompanyCategory category = companyCategoryDao.retrieveCompanyCategory(c.getCategoryId());
		return category;
	}

}
