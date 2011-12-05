package ic.doc.cpp.client.company.core;

import ic.doc.cpp.client.place.NameTokens;
import ic.doc.cpp.client.student.core.ListgridSectionStackSectionRecord;

public class MainNavigationPaneSectionData {
	  private static ListgridSectionStackSectionRecord[] records;

	  public static ListgridSectionStackSectionRecord[] getRecords() {
		if (records == null) {
		  records = getNewRecords();
		}
		return records;
	  }

	  public static ListgridSectionStackSectionRecord[] getNewRecords() {
		return new ListgridSectionStackSectionRecord[]{
				new ListgridSectionStackSectionRecord("eventPosting", NameTokens.postevent, "Posting Events", null),
		};
	  }

}
