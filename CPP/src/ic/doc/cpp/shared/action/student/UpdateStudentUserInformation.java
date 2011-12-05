package ic.doc.cpp.shared.action.student;

import ic.doc.cpp.shared.dto.StudentUserDto;

import com.gwtplatform.dispatch.shared.ActionImpl;

public class UpdateStudentUserInformation extends
		ActionImpl<UpdateStudentUserInformationResult> {

	private StudentUserDto student;

	@SuppressWarnings("unused")
	private UpdateStudentUserInformation() {
		// For serialization only
	}

	public UpdateStudentUserInformation(StudentUserDto student) {
		this.student = student;
	}

	public StudentUserDto getStudentUserDto() {
		return student;
	}

	@Override
	public boolean isSecured() {
		return true;
	}
	
	
}
