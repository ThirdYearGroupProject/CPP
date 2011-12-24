package ic.doc.cpp.shared.action;

import ic.doc.cpp.shared.CompanyRecord;
import ic.doc.cpp.shared.dto.CompanyDto;

import java.util.List;

import com.gwtplatform.dispatch.shared.Result;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class AdminGetAllCResult implements Result {

	private List<CompanyDto> ListOfCompany;

	@SuppressWarnings("unused")
	private AdminGetAllCResult() {
		// For serialization only
	}

	public AdminGetAllCResult(List<CompanyDto> ListOfCompany) {
		this.ListOfCompany = ListOfCompany;
	}

	public ListGridRecord[] getListOfCompany() {
		ListGridRecord[] gridData = new ListGridRecord[ListOfCompany.size()];
		for(int i = 0; i < ListOfCompany.size() ;i++){
			gridData[i] = new CompanyRecord(ListOfCompany.get(i));
		}
		return gridData;
	}
}
