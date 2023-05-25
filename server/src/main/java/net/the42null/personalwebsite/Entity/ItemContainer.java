package net.the42null.personalwebsite.Entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.the42null.personalwebsite.exception.ResourceNotFoundException;
import org.hibernate.annotations.NotFound;

public class ItemContainer {
	private final Long id;
	private final String header;
	private final Type type;
	public enum Type {//TODO: Move away from enum so it is not tied to ItemContainer
		@JsonAlias("education")
		EDUCATION,
		@JsonAlias("accomplishment")
		ACCOMPLISHMENTS,
		@JsonAlias("work-experience")
		WORK_EXPERIENCE,
		@JsonAlias("relevant-coursework")
		RELEVANT_COURSEWORK,
		@JsonAlias("certification")
		CERTIFICATE
	}

	private final String subtitle;
	private final Image[] images;
	private final String text;
	static class Image{

		private final String src, alt, title, background, radius, padding, onClick;

		@JsonCreator
		Image(@JsonProperty("src") String src,
			  @JsonProperty("alt") String alt,
			  @JsonProperty("title") String title,
			  @JsonProperty("background") String background,
			  @JsonProperty("radius") String radius,
			  @JsonProperty("padding") String padding,
			  @JsonProperty(value = "onClick", defaultValue = "") String onClick
			) {
			this.src = src;
			this.alt = alt;
			this.title = title;
			this.background = background;
			this.radius = radius;
			this.padding = padding;
			this.onClick = onClick;
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
		public String getOnClick() {
			if(onClick==null){return "";}
			return onClick;
		}
	}

@JsonCreator
public ItemContainer(@JsonProperty("id") long id,
					 @JsonProperty("header") String header,
					 @JsonProperty("type") Type type,
					 @JsonProperty("subtitle") String subtitle,
					 @JsonProperty("images") Image[] images,
					 @JsonProperty("text") String text){
		this.id = id;
		this.header = header;
		this.type = type;
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
	public Type getType(){
//		switch(type) {
//			case EDUCATION:
////				return "education";
//				return Type.EDUCATION;
//			case ACCOMPLISHMENTS:
////				return "accomplishments";
//				return Type.ACCOMPLISHMENTS;
//			case WORK_EXPERIENCE:
////				return "experience";
//				return Type.WORK_EXPERIENCE;
//			case RELEVANT_COURSEWORK:
////				return "coursework";
//				return Type.RELEVANT_COURSEWORK;
//		}
		return type;
//		throw new ResourceNotFoundException("ItemContainer", "type", type.name());
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