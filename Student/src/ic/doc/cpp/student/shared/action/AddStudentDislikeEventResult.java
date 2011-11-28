package ic.doc.cpp.student.shared.action;

import com.gwtplatform.dispatch.shared.Result;

public class AddStudentDislikeEventResult implements Result {
	private String message;
	
	@SuppressWarnings("unused")
	private AddStudentDislikeEventResult() {
		// For serialization only
	}
	
	public AddStudentDislikeEventResult(String companyName) {
		this.message = companyName;
	}

	public String getMessage() {
		return message;
	}
}
