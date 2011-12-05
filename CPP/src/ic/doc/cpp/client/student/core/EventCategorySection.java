package ic.doc.cpp.client.student.core;

public class EventCategorySection extends CategorySectionStackSection {
	public EventCategorySection(String sectionTitle, String contextAreaName, EventCategoryTreeGrid grid) {
		  super(sectionTitle, contextAreaName);
		  grid.setAutoFetchData(true);
		  this.addItem(grid);
		  this.setExpanded(true); 
	}
}
