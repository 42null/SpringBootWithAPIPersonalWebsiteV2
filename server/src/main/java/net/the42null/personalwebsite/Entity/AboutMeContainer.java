package net.the42null.personalwebsite.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AboutMeContainer {
	private final Long id;
	private final String title;
	private final String subtitle;
	private final Image[] images;
	private final String text;

	static class Image{

		private final String src, alt, title;

		@JsonCreator
		Image(@JsonProperty("src") String src,
			  @JsonProperty("alt") String alt,
			  @JsonProperty("title") String title
			) {
			this.src = src;
			this.alt = alt;
			this.title = title;
		}

		public String getSrc() {
			return src;
		}
		public String getAlt() {
			return alt;
		}
		public String getTitle() {
			return title;
		}

	}


@JsonCreator
public AboutMeContainer(@JsonProperty("id") long id,
						@JsonProperty("title") String title,
						@JsonProperty("subtitle") String subtitle,
						@JsonProperty("images") Image[] images,
						@JsonProperty("text") String text
						){
		this.id = id;
		this.title = title;
		this.subtitle = subtitle;
		this.images = images;
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

	public Image[] getImages() {
		return images;
	}

	public String getText() {
		return text;
	}

}