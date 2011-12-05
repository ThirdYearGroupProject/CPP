package ic.doc.cpp.shared.action.student;

import com.gwtplatform.dispatch.shared.ActionImpl;
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
