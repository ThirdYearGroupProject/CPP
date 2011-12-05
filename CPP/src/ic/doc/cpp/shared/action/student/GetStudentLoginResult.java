package ic.doc.cpp.shared.action.student;

import com.gwtplatform.dispatch.shared.Result;
import java.lang.String;

public class GetStudentLoginResult implements Result {

	private String login;

	@SuppressWarnings("unused")
	private GetStudentLoginResult() {
		// For serialization only
	}

	public GetStudentLoginResult(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}
}
