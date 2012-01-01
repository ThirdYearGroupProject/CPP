package ic.doc.cpp.shared.action;

import com.gwtplatform.dispatch.shared.Result;
import java.lang.String;

public class GetInvitationResult implements Result {

	private String InvitationCode;

	@SuppressWarnings("unused")
	private GetInvitationResult() {
		// For serialization only
	}

	public GetInvitationResult(String InvitationCode) {
		this.InvitationCode = InvitationCode;
	}

	public String getInvitationCode() {
		return InvitationCode;
	}
}
