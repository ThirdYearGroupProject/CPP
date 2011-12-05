package ic.doc.cpp.server.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "company_user")
public class CompanyUser {
	@Id
	@Column(name = "company_login", length = 64)
	protected String login;

	@Column(name = "salt", length = 64, nullable = false)
	protected String salt;

	@Column(name = "password", length = 64, nullable = false)
	protected String password;
	
	@Column(name = "first_name", length = 100, nullable = false)
	protected String firstName;

	@Column(name = "last_name", length = 100, nullable = false)
	protected String lastName;
	
	@Column(name = "email", length = 100, nullable = false)
	protected String email;
	
	@Column(name = "gender", length = 20, nullable = false)
	protected String gender;
	
    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id")
    protected Company company;
    
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "company_user_student_user",
		joinColumns = {@JoinColumn(name = "company_login")},
		inverseJoinColumns = {@JoinColumn(name = "login")})
	protected List<StudentUser> interestedStudents;
	
	public CompanyUser() {}

	public CompanyUser(String login, String salt, String password,
			String firstName, String lastName, String email, String gender,
			Company company, List<StudentUser> interestedStudents) {
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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<StudentUser> getInterestedStudents() {
		return interestedStudents;
	}

	public void setInterestedStudents(List<StudentUser> interestedStudents) {
		this.interestedStudents = interestedStudents;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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
		CompanyUser other = (CompanyUser) obj;
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
