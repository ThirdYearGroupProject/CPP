package ic.doc.cpp.shared.action.student;

import com.gwtplatform.dispatch.shared.ActionImpl;
import java.lang.Long;

public class AddStudentInterestedCompany extends
		ActionImpl<AddStudentInterestedCompanyResult> {

	private Long companyId;

	@SuppressWarnings("unused")
	private AddStudentInterestedCompany() {
		// For serialization only
	}

	public AddStudentInterestedCompany(Long companyId) {
		this.companyId = companyId;
	}

	public Long getCompanyId() {
		return companyId;
	}
}
