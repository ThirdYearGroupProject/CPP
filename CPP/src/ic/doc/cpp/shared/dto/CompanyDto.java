package ic.doc.cpp.shared.dto;

import java.io.Serializable;
import java.util.List;

public class CompanyDto implements Serializable {
	
	private static final long serialVersionUID = -9050049510672663966L;
			
	protected Long companyId;
	protected String name;
    protected CompanyCategoryDto categoryDto;
    protected List<EventDto> eventDtos;
	protected String description;
	protected String website;
	protected String logo;
	
	public CompanyDto() {}

	public CompanyDto(Long companyId, String name,
			CompanyCategoryDto categoryDto, List<EventDto> eventDtos,
			String description, String website, String logo) {
		super();
		this.companyId = companyId;
		this.name = name;
		this.categoryDto = categoryDto;
		this.eventDtos = eventDtos;
		this.description = description;
		this.website = website;
		this.logo = logo;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CompanyCategoryDto getCategoryDto() {
		return categoryDto;
	}

	public void setCategoryDto(CompanyCategoryDto categoryDto) {
		this.categoryDto = categoryDto;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public List<EventDto> getEvents() {
		return eventDtos;
	}


	public void setEvents(List<EventDto> events) {
		this.eventDtos = events;
	}


	public List<EventDto> getEventDtos() {
		return eventDtos;
	}


	public void setEventDtos(List<EventDto> eventDtos) {
		this.eventDtos = eventDtos;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Id: ").append(companyId).append(", ");
		sb.append("Name: ").append(name).append(", ");
		sb.append("Category: ").append(categoryDto.getCategoryName()).append(", ");
		
		return sb.toString();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		CompanyDto other = (CompanyDto) obj;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
