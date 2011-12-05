package ic.doc.cpp.shared.action.student;

import com.gwtplatform.dispatch.shared.Result;

import ic.doc.cpp.shared.dto.CompanyDto;

import java.util.List;

public class RetrieveStudentInterestedCompaniesResult implements Result {

	private List<CompanyDto> companyDto;

	@SuppressWarnings("unused")
	private RetrieveStudentInterestedCompaniesResult() {
		// For serialization only
	}

	public RetrieveStudentInterestedCompaniesResult(List<CompanyDto> companyDto) {
		this.companyDto = companyDto;
	}

	public List<CompanyDto> getCompanyDto() {
		return companyDto;
	}
}
