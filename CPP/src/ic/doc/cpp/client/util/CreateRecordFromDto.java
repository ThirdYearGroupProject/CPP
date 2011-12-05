package ic.doc.cpp.client.util;

import ic.doc.cpp.client.student.core.data.CompanyTileRecord;
import ic.doc.cpp.shared.dto.CompanyDto;
import ic.doc.cpp.shared.dto.EventDto;

import java.util.ArrayList;
import java.util.List;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.calendar.CalendarEvent;

public class CreateRecordFromDto {
	
	public static List<CompanyTileRecord>
	createCompanyTileRecordsFromCompanyDtos(List<CompanyDto> companyDtos) {
		List<CompanyTileRecord> result = new ArrayList<CompanyTileRecord>();
		for (CompanyDto companyDto : companyDtos) {
			result.add(createCompanyTileRecordFromCompanyDto(companyDto));
		}
		return result;
	}
	
	public static CompanyTileRecord createCompanyTileRecordFromCompanyDto(CompanyDto companyDto) {
		return new CompanyTileRecord(companyDto.getCompanyId(), 
				companyDto.getName(),
				companyDto.getCategoryDto().getCategoryId(), 
				companyDto.getCategoryDto().getCategoryName(),
				companyDto.getWebsite(), 
				companyDto.getDescription(),
				companyDto.getLogo(), 
				companyDto.getEventDtos());
	}
	
	public static Record[] createRecordsFromEventDtos(String companyName, List<EventDto> eventDtos) {
		Record[] records = new Record[eventDtos.size()];
		int i = 0;
		for (EventDto eventDto : eventDtos) {
			records[i++] = createRecordFromEventDto(companyName, eventDto);
		}
		return records;
	}

	public static Record createRecordFromEventDto(String companyName, EventDto eventDto) {
		Record record = new Record();
		record.setAttribute("eventId", eventDto.getEventId());
		record.setAttribute("title", eventDto.getTitle());
		record.setAttribute("description", eventDto.getDescription());
		record.setAttribute("categoryId", eventDto.getCategoryId());
		//record.setAttribute("category", eventDto.getCategoryName());
		record.setAttribute("companyId", eventDto.getCompanyId());
		record.setAttribute("companyName", companyName);
		record.setAttribute("website", eventDto.getWebsite());
		record.setAttribute("picture", eventDto.getPicture());
		record.setAttribute("startDate", eventDto.getStart_date());
		record.setAttribute("endDate", eventDto.getEnd_date());
		
		return record;
	}

	public static List<CalendarEvent> createCalendarEventFromDtos(List<EventDto> eventDtos) {
		List<CalendarEvent> result = new ArrayList<CalendarEvent>();
		for (EventDto eventDto : eventDtos) {
			result.add(createCalendarEventFromDto(eventDto));
		}
		return result;
	}

	private static CalendarEvent createCalendarEventFromDto(EventDto eventDto) {
		int eventId = safeLongToInt(eventDto.getEventId());
		CalendarEvent calendarEvent = new CalendarEvent(eventId, eventDto.getTitle(),
				eventDto.getDescription(), eventDto.getStart_date(), eventDto.getEnd_date());
		return calendarEvent;
	}  
	
	public static int safeLongToInt(long l) {
	    if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
	        throw new IllegalArgumentException
	            (l + " cannot be cast to int without changing its value.");
	    }
	    return (int) l;
	}
}
