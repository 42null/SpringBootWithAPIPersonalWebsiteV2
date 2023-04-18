package net.the42null.personalwebsite.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AboutMeContainer {
	private final Long id;
	private final String title;
	private final String subtitle;
	private final String imageUrl;
	private final String imageAlt;
	private final String text;

@JsonCreator
public AboutMeContainer(@JsonProperty("id") long id,
						@JsonProperty("title") String title,
						@JsonProperty("subtitle") String subtitle,
						@JsonProperty("imageURL") String imageUrl,
						@JsonProperty("imageAlt") String imageAlt,
						@JsonProperty("text") String text
						){
		this.id = id;
		this.title = title;
		this.subtitle = subtitle;
		this.imageUrl = imageUrl;
		this.imageAlt = imageAlt;
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getText() {
		return text;
	}

	public String getImageAlt() {
		return imageAlt;
	}
}