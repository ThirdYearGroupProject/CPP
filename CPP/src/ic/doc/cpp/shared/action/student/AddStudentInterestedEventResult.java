package ic.doc.cpp.shared.action.student;

import com.gwtplatform.dispatch.shared.Result;

public class AddStudentInterestedEventResult implements Result {
	private String message;
	
	@SuppressWarnings("unused")
	private AddStudentInterestedEventResult() {
		// For serialization only
	}
	
	public AddStudentInterestedEventResult(String companyName) {
		this.message = companyName;
	}

	public String getMessage() {
		return message;
	}
}
