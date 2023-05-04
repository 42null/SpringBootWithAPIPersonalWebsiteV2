package net.the42null.personalwebsite.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Contact {
	private final Long id;
	private final String description;
	private final String showLink;
	private final String copyStart;
	private final String title;

@JsonCreator
public Contact(@JsonProperty("id") long id,
			   @JsonProperty("description") String description,
			   @JsonProperty("link") String showLink,
			   @JsonProperty("copyStart") String copyStart,
			   @JsonProperty("title") String title
		      ){
		this.id = id;
		this.description = description;
		this.showLink = showLink;
		this.copyStart = copyStart;
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public String getShowLink() {
		return showLink;
	}

	public String getCopyStart() {
		return copyStart;
	}

	public String getTitle() {
		return title;
	}

}