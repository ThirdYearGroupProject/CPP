package ic.doc.cpp.shared.action.student;

import com.gwtplatform.dispatch.shared.ActionImpl;
import java.lang.Long;

public class RetrieveCompanyEvents extends
		ActionImpl<RetrieveCompanyEventsResult> {

	private Long companyId;

	@SuppressWarnings("unused")
	private RetrieveCompanyEvents() {
		// For serialization only
	}

	public RetrieveCompanyEvents(Long companyId) {
		this.companyId = companyId;
	}

	public Long getCompanyId() {
		return companyId;
	}
}
