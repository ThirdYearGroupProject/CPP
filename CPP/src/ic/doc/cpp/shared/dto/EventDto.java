package ic.doc.cpp.shared.dto;

import java.io.Serializable;
import java.util.Date;

public class EventDto implements Serializable{
	
	private static final long serialVersionUID = 6072601952142896135L;
	
	protected Long eventId;
	protected String title;
	protected Long categoryId;
	protected Long companyId;
	protected String description;
	protected String website;
	protected Date start_date;
	protected Date end_date;
	protected String picture;
	
	protected boolean likedByCurrentUser;
	
	public EventDto() {}

	public EventDto(Long eventId, String title, Long categoryId,
			Long companyId, String description, String website,
			Date start_date, Date end_date, String picture) {
		super();
		this.eventId = eventId;
		this.title = title;
		this.categoryId = categoryId;
		this.companyId = companyId;
		this.description = description;
		this.website = website;
		this.start_date = start_date;
		this.end_date = end_date;
		this.picture = picture;
		this.likedByCurrentUser = false;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getPicture() {
		return picture;
	}
	
	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public boolean isLikedByCurrentUser() {
		return likedByCurrentUser;
	}

	public void setLikedByCurrentUser(boolean likedByCurrentUser) {
		this.likedByCurrentUser = likedByCurrentUser;
	}

	@Override
	public String toString() {
		return "EventDto [eventId=" + eventId + ", title=" + title
				+ ", categoryId=" + categoryId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eventId == null) ? 0 : eventId.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		EventDto other = (EventDto) obj;
		if (eventId == null) {
			if (other.eventId != null)
				return false;
		} else if (!eventId.equals(other.eventId))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
}
