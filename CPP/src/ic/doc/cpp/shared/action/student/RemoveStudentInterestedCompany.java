package ic.doc.cpp.shared.action.student;

import com.gwtplatform.dispatch.shared.ActionImpl;
import java.lang.Long;

public class RemoveStudentInterestedCompany extends
		ActionImpl<RemoveStudentInterestedCompanyResult> {

	private Long companyId;

	@SuppressWarnings("unused")
	private RemoveStudentInterestedCompany() {
		// For serialization only
	}

	public RemoveStudentInterestedCompany(Long companyId) {
		this.companyId = companyId;
	}

	public Long getCompanyId() {
		return companyId;
	}
}
