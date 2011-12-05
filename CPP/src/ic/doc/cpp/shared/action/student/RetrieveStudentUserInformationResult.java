package ic.doc.cpp.shared.action.student;

import ic.doc.cpp.shared.dto.StudentUserDto;

import com.gwtplatform.dispatch.shared.Result;

public class RetrieveStudentUserInformationResult implements Result {

	private StudentUserDto studentUserDto;

	@SuppressWarnings("unused")
	private RetrieveStudentUserInformationResult() {
		// For serialization only
	}

	public RetrieveStudentUserInformationResult(StudentUserDto studentUserDto) {
		this.studentUserDto = studentUserDto;
	}

	public StudentUserDto getStudentUserDto() {
		return studentUserDto;
	}
}
