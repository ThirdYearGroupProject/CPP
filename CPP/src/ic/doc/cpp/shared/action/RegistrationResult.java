package ic.doc.cpp.shared.action;

import com.gwtplatform.dispatch.shared.Result;
import java.lang.String;

public class RegistrationResult implements Result {

	private String SessionId;

	@SuppressWarnings("unused")
	private RegistrationResult() {
		// For serialization only
	}

	public RegistrationResult(String SessionId) {
		this.SessionId = SessionId;
	}

	public String getSessionId() {
		return SessionId;
	}
}
