package ic.doc.cpp.shared.action;

import ic.doc.cpp.shared.dto.EventCategoryDto;

import java.util.List;

import com.gwtplatform.dispatch.shared.Result;

public class RetrieveEventCategoriesResult implements Result {

	private List<EventCategoryDto> eventCategoryDtos;

	@SuppressWarnings("unused")
	private RetrieveEventCategoriesResult() {
		// For serialization only
	}

	public RetrieveEventCategoriesResult(List<EventCategoryDto> eventCategoryDtos) {
		this.eventCategoryDtos = eventCategoryDtos;
	}

	public List<EventCategoryDto> getEventCategoryDtos() {
		return eventCategoryDtos;
	}
}
