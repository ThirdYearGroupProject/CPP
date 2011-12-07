package ic.doc.cpp.shared.action;

import com.gwtplatform.dispatch.shared.ActionImpl;
import ic.doc.cpp.shared.action.UpdateCompanyInformationResult;
import ic.doc.cpp.shared.dto.CompanyDto;

public class UpdateCompanyInformation extends
		ActionImpl<UpdateCompanyInformationResult> {

	private CompanyDto companyDto;

	@SuppressWarnings("unused")
	private UpdateCompanyInformation() {
		// For serialization only
	}

	public UpdateCompanyInformation(CompanyDto companyDto) {
		this.companyDto = companyDto;
	}

	public CompanyDto getCompanyDto() {
		return companyDto;
	}
}
