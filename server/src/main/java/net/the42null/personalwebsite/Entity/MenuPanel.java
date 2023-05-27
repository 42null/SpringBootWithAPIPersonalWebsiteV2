package net.the42null.personalwebsite.Entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuPanel {
	private final String header;
	private final Type type;
	private final String subtitle;
	private final String modeName;
	private final String[] modes;
	private final Page[] pages;
	private final String text;

	public enum Type {
		@JsonAlias("example")
		EXAMPLE,
		@JsonAlias("use")//TODO: Come up with a better name
		USE
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
					 @JsonProperty("type") Type type,
					 @JsonProperty("subtitle") String subtitle,
					 @JsonProperty("modeName") String modeName,
					 @JsonProperty("modes") String[] modes,
					 @JsonProperty("pages") Page[] pages,
					 @JsonProperty("text") String text) {
		this.header = header;
		this.type = type;
		this.subtitle = subtitle;
		this.modeName = modeName;
		this.modes = modes;
		this.pages = pages;
		this.text = text;
	}

	public String getHeader() {
		return header;
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

	public String getText() {
		return text;
	}
}