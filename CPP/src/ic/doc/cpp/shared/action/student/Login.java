package ic.doc.cpp.shared.action.student;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

import java.lang.String;

public class Login extends UnsecuredActionImpl<LoginResult> {
	
	private String type;
	private String login;
	private String password;

	@SuppressWarnings("unused")
	private Login() {
		// For serialization only
	}

	public Login(String type, String login, String password) {
		this.type = type;
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}