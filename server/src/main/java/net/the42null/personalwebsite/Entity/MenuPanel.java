package net.the42null.personalwebsite.Entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuPanel {
	private final String header;
	private final String urlEnding;
	private final String tabHeaderName;
	private final Type type;
	private final String subtitle;
	private final String modeName;
	private final String[] modes;
	private final Page[] pages;
	private final AdditionalLink[] additionalLinks;
	private final String text;

	public enum Type {
		@JsonAlias("example")
		EXAMPLE,
		@JsonAlias("inuse")//TODO: Come up with a better name
		INUSE,
		@JsonAlias("allApps")
		ALL
	}

	public static class Page {
		private final String name;
		private final Image[] images;
		private final String text;

		public Page(@JsonProperty("name") String name,
					@JsonProperty("images") Image[] images,
					@JsonProperty("text") String text) {
			this.name = name;
			this.images = images;
			this.text = text;
		}

		public String getName() {
			return name;
		}

		public Image[] getImages() {
			return images;
		}

		public String getText() {
			return text;
		}
	}
	public static class AdditionalLink {
		public enum URLTargetType {
			@JsonAlias({"blank","newTab"})
			BLANK("_blank"),
			@JsonAlias({"self","current"})
			SELF("_self");

			private final String value;

			URLTargetType(String targetValue) {
				this.value = targetValue;
			}

			public String getValue() {
				return value;
			}
		}
		private final String url;
		private final URLTargetType targetType;
		private final String name;
		private final String title;
		private final String description;

		public AdditionalLink(@JsonProperty("url") String url,
							  @JsonProperty("name") String name,
							  @JsonProperty("title") String title,
							  @JsonProperty(value = "urlTargetType", defaultValue = "newTab") URLTargetType targetType,
							  @JsonProperty("description") String description) {
			this.url = url;
			this.name = name;
			this.title = title;
			this.targetType = (targetType != null) ? targetType : URLTargetType.BLANK;//TODO: Fix, should never need to be used as defaultvalue should prevent this.
			this.description = description;
		}

		public String getUrl() {
			return url;
		}
		public String getTarget() {
			return targetType.getValue();
		}

		public String getName() {
			return name;
		}

		public String getTitle() {
			return title;
		}

		public String getDescription() {
			return description;
		}
	}

	public static class Image {
		private final String mode;
		private final String src;
		private final String alt;
		private final String title;

		public Image(@JsonProperty("mode") String mode,
					 @JsonProperty("src") String src,
					 @JsonProperty("alt") String alt,
					 @JsonProperty("title") String title) {
			this.mode = mode;
			this.src = src;
			this.alt = alt;
			this.title = title;
		}

		public String getMode() {
			return mode;
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

	public MenuPanel(@JsonProperty("header") String header,
					 @JsonProperty("urlEnding") String urlEnding,
					 @JsonProperty("tabHeaderName") String tabHeaderName,
					 @JsonProperty("type") Type type,
					 @JsonProperty("subtitle") String subtitle,
					 @JsonProperty("modeName") String modeName,
					 @JsonProperty("modes") String[] modes,
					 @JsonProperty("pages") Page[] pages,
					 @JsonProperty(value = "additionalLinks", defaultValue = "") AdditionalLink[] additionalLinks,//TODO: Make optional
					 @JsonProperty("text") String text) {
		this.header = header;
		this.urlEnding = urlEnding;
		this.tabHeaderName = tabHeaderName;
		this.type = type;
		this.subtitle = subtitle;
		this.modeName = modeName;
		this.modes = modes;
		this.pages = pages;
		this.additionalLinks = additionalLinks;
		this.text = text;
	}

	public String getHeader() {
		return header;
	}

	public String getUrlEnding() {
		return urlEnding;
	}

	public String getTabHeaderName() {
		return tabHeaderName;
	}

	public Type getType() {
		return type;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public String getModeName() {
		return modeName;
	}

	public String[] getModes() {
		return modes;
	}

	public Page[] getPages() {
		return pages;
	}

	public AdditionalLink[] getAdditionalLinks(){return additionalLinks;}

	public String getText() {
		return text;
	}
}