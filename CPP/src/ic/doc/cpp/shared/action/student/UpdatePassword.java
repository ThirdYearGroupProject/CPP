package ic.doc.cpp.shared.action.student;

import com.gwtplatform.dispatch.shared.ActionImpl;
import java.lang.String;

public class UpdatePassword extends ActionImpl<UpdatePasswordResult> {

	private String originalPassword;
	private String newPassword;

	@SuppressWarnings("unused")
	private UpdatePassword() {
		// For serialization only
	}

	public UpdatePassword(String originalPassword, String newPassword) {
		this.originalPassword = originalPassword;
		this.newPassword = newPassword;
	}

	public String getOriginalPassword() {
		return originalPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}
	
	@Override
	public boolean isSecured() {
		return true;
	}
}
