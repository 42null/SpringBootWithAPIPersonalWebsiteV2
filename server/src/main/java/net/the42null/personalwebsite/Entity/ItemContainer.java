package net.the42null.personalwebsite.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemContainer {
	private final Long id;
	private final String header;
	private final String subtitle;
	private final Image[] images;
	private final String text;

	static class Image{

		private final String src, alt, title, background, radius, padding;

		@JsonCreator
		Image(@JsonProperty("src") String src,
			  @JsonProperty("alt") String alt,
			  @JsonProperty("title") String title,
			  @JsonProperty("background") String background,
			  @JsonProperty("radius") String radius,
			  @JsonProperty("padding") String padding
			) {
			this.src = src;
			this.alt = alt;
			this.title = title;
			this.background = background;
			this.radius = radius;
			this.padding = padding;
		}

		public String getSrc() {
			if(src==null){return "";}
			return src;
		}
		public String getAlt() {
			if(alt==null){
				if(title==null){
					return "";
				}
				return title;
			}
			return alt;
		}
		public String getTitle() {
			if(title==null){
				if(alt==null){
					return "";
				}
				return alt;
			}
			return title;
		}
		public String getBackground() {
			if(background==null){return "transparent";}
			return background;
		}
		public String getRadius() {
			if(padding==null){return "0";}
			return radius;
		}
		public String getPadding() {
			if(padding==null){return "0";}
			return padding;
		}

	}


@JsonCreator
public ItemContainer(@JsonProperty("id") long id,
					 @JsonProperty("header") String header,
					 @JsonProperty("subtitle") String subtitle,
					 @JsonProperty("images") Image[] images,
					 @JsonProperty("text") String text
						){
		this.id = id;
		this.header = header;
		this.subtitle = subtitle;
		this.images = images;
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public String getHeader() {
		return header;
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