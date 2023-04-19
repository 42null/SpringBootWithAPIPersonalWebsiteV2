package net.the42null.personalwebsite.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PageUpdateBar {
	private final Long id;
	private final String title;
	private final String url;
	private final String status;

@JsonCreator
public PageUpdateBar(@JsonProperty("id") long id,
					 @JsonProperty("title") String title,
					 @JsonProperty("url") String url,
					 @JsonProperty("status") String status
						){
		this.id = id;
		this.title = title;
		this.url = url;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}
	public String getStatus() {
		return status;
	}

}