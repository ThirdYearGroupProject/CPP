package ic.doc.cpp.server.util;

import ic.doc.cpp.server.domain.Company;
import ic.doc.cpp.server.domain.CompanyCategory;
import ic.doc.cpp.server.domain.Event;
import ic.doc.cpp.server.domain.EventCategory;
import ic.doc.cpp.server.domain.StudentUser;
import ic.doc.cpp.shared.dto.CompanyCategoryDto;
import ic.doc.cpp.shared.dto.CompanyDto;
import ic.doc.cpp.shared.dto.EventCategoryDto;
import ic.doc.cpp.shared.dto.EventDto;
import ic.doc.cpp.shared.dto.StudentUserDto;

import java.util.ArrayList;
import java.util.List;

public class CreateDto {
	public static StudentUserDto createStudentDto(StudentUser studentUser) {
		StudentUserDto result = new StudentUserDto(studentUser.getLogin(), studentUser.getSalt(),
				studentUser.getPassword(), studentUser.getEmail(), 
				studentUser.getFirstName(), studentUser.getLastName(), 
				studentUser.getGender(), createEventDtos(studentUser.getEvents()),  
				createCompanyDtos(studentUser.getCompanys()), createCompanyCategoryDtos(studentUser.getInterestedArea()));
		
		result.setDislikeEventDtos(createEventDtos(studentUser.getDislikeEvents()));

		return result;
	}

	public static List<CompanyDto> createCompanyDtos(List<Company> companys) {
		List<CompanyDto> list = new ArrayList<CompanyDto>();
		for (Company c : companys) {
			list.add(createCompanyDto(c));
		}
		return list;
	}

	public static List<EventDto> createEventDtos(List<Event> events) {
		List<EventDto> list = new ArrayList<EventDto>();
		for (Event e : events) {
			list.add(createEventDto(e));
		}
		return list;
	}

	public static List<CompanyCategoryDto> createCompanyCategoryDtos(
			List<CompanyCategory> interestedArea) {
		List<CompanyCategoryDto> list = new ArrayList<CompanyCategoryDto>();
		for (CompanyCategory c : interestedArea) {
			list.add(createCompanyCategoryDto(c));
		}
		return list;
	}
	
	public static EventDto createEventDto(Event e) {
		EventDto result =  new EventDto(e.getEventId(), e.getTitle(), e.getCategory().getCategoryId(),
				e.getCompany().getCompanyId(), e.getDescription(), e.getWebsite(),
				e.getStart_date(), e.getEnd_date(),	e.getPicture());
		return result;
	}

	public static CompanyDto createCompanyDto(Company company) {
		CompanyDto companyDto =  new CompanyDto(company.getCompanyId(), company.getName(), 
				createCompanyCategoryDto(company.getCategory()), 
				createEventDtos(company.getEvents()), company.getDescription(), 
				company.getWebsite(), company.getLogo());
		
		return companyDto;
	}

	public static EventCategoryDto createEventCategoryDto(EventCategory category) {
		return new EventCategoryDto(category.getCategoryId(),
				category.getCategoryName(), category.getParentId());
	}


	public static CompanyCategoryDto createCompanyCategoryDto(CompanyCategory c) {
		return new CompanyCategoryDto(c.getCategoryId(), c.getCategoryName(), c.getParentId());
	}

	public static List<EventCategoryDto> createEventCategoryDtos(
			List<EventCategory> eventCategorys) {
		List<EventCategoryDto> result = new ArrayList<EventCategoryDto>();
		for (EventCategory e : eventCategorys) {
			result.add(createEventCategoryDto(e));
		}
		return result;
	}

}
