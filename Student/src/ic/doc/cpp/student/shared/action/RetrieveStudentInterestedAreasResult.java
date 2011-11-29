package ic.doc.cpp.student.shared.action;

import com.gwtplatform.dispatch.shared.Result;

import ic.doc.cpp.student.shared.dto.CompanyCategoryDto;

import java.util.List;

public class RetrieveStudentInterestedAreasResult implements Result {

	private List<CompanyCategoryDto> interestedAreas;

	@SuppressWarnings("unused")
	private RetrieveStudentInterestedAreasResult() {
		// For serialization only
	}

	public RetrieveStudentInterestedAreasResult(List<CompanyCategoryDto> interestedAreas) {
		this.interestedAreas = interestedAreas;
	}

	public List<CompanyCategoryDto> getInterestedAreas() {
		return interestedAreas;
	}
}
