package ic.doc.cpp.student.shared.action;

import com.gwtplatform.dispatch.shared.ActionImpl;
import ic.doc.cpp.student.shared.action.UpdateStudentInterestedAreasResult;
import java.util.List;

public class UpdateStudentInterestedAreas extends
		ActionImpl<UpdateStudentInterestedAreasResult> {

	private List<Long> companyCategoryId;

	@SuppressWarnings("unused")
	private UpdateStudentInterestedAreas() {
		// For serialization only
	}

	public UpdateStudentInterestedAreas(List<Long> companyCategoryId) {
		this.companyCategoryId = companyCategoryId;
	}

	public List<Long> getCompanyCategoryId() {
		return companyCategoryId;
	}
}
