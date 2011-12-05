package ic.doc.cpp.shared.dto;

import java.io.Serializable;
import java.util.List;

public class CompanyUserDto implements Serializable {

	private static final long serialVersionUID = 1603401237099288076L;

	protected String login;
	protected String salt;
	protected String password;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String gender;
    protected CompanyDto company;
	protected List<StudentUserDto> interestedStudents;
	
	public CompanyUserDto(String login, String salt, String password,
			String firstName, String lastName, String email, String gender,
			CompanyDto company, List<StudentUserDto> interestedStudents) {
		super();
		this.login = login;
		this.salt = salt;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.company = company;
		this.interestedStudents = interestedStudents;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public CompanyDto getCompany() {
		return company;
	}
	public void setCompany(CompanyDto company) {
		this.company = company;
	}
	public List<StudentUserDto> getInterestedStudents() {
		return interestedStudents;
	}
	public void setInterestedStudents(List<StudentUserDto> interestedStudents) {
		this.interestedStudents = interestedStudents;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((salt == null) ? 0 : salt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyUserDto other = (CompanyUserDto) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (salt == null) {
			if (other.salt != null)
				return false;
		} else if (!salt.equals(other.salt))
			return false;
		return true;
	}
	
}
