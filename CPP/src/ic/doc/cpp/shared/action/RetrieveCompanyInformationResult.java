package ic.doc.cpp.shared.action;

import com.gwtplatform.dispatch.shared.Result;
import ic.doc.cpp.shared.dto.CompanyDto;

public class RetrieveCompanyInformationResult implements Result {

	private CompanyDto companayDto;

	@SuppressWarnings("unused")
	private RetrieveCompanyInformationResult() {
		// For serialization only
	}

	public RetrieveCompanyInformationResult(CompanyDto companayDto) {
		this.companayDto = companayDto;
	}

	public CompanyDto getCompanayDto() {
		return companayDto;
	}
}
