package ic.doc.cpp.shared.action;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

import ic.doc.cpp.shared.action.GetInvitationResult;

import java.lang.Long;

public class GetInvitation extends UnsecuredActionImpl<GetInvitationResult> {

	private Long companyId;

	@SuppressWarnings("unused")
	private GetInvitation() {
		// For serialization only
	}

	public GetInvitation(Long companyId) {
		this.companyId = companyId;
	}

	public Long getCompanyId() {
		return companyId;
	}
}
