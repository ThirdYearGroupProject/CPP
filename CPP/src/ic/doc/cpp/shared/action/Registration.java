package ic.doc.cpp.shared.action;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

import ic.doc.cpp.shared.action.RegistrationResult;

import java.lang.String;

public class Registration extends UnsecuredActionImpl<RegistrationResult> {

	private String UserName;
	private String Password;
	private String Email;
	private String Gender;
	private String Company;
	private String FirstName;
	private String LastName;
	private String UserType;

	@SuppressWarnings("unused")
	private Registration() {
		// For serialization only
	}

	public Registration(String UserName, String Password, String Email,
			String Gender, String Company, String FirstName, String LastName,
			String UserType) {
		this.UserName = UserName;
		this.Password = Password;
		this.Email = Email;
		this.Gender = Gender;
		this.Company = Company;
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.UserType = UserType;
	}

	public String getUserName() {
		return UserName;
	}

	public String getPassword() {
		return Password;
	}

	public String getEmail() {
		return Email;
	}

	public String getGender() {
		return Gender;
	}

	public String getCompany() {
		return Company;
	}

	public String getFirstName() {
		return FirstName;
	}

	public String getLastName() {
		return LastName;
	}

	public String getUserType() {
		return UserType;
	}
}
