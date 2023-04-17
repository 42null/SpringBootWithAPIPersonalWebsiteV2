package net.the42null.personalwebsite.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class GithubRepository {
	private final Long id;
	private final String nodeId;
	private final String name;
	private final String fullName;
	private final boolean isPrivate;
//	private GithubUser owner;
	private final String htmlUrl;
	private final String description;
	private final boolean isFork;
	private final String url;
	private final String forksUrl;
	private final String keysUrl;
	private final String collaboratorsUrl;
	private final String teamsUrl;
	private final String hooksUrl;
	private final String issueEventsUrl;
	private final String eventsUrl;
	private final String assigneesUrl;
	private final String branchesUrl;
	private final String tagsUrl;
	private final String blobsUrl;
	private final String gitTagsUrl;
	private final String gitRefsUrl;
	private final String treesUrl;
	private final String statusesUrl;
	private final String languagesUrl;
	private final String stargazersUrl;
	private final String contributorsUrl;
	private final String subscribersUrl;
	private final String subscriptionUrl;
	private final String commitsUrl;
	private final String gitCommitsUrl;
	private final String commentsUrl;
	private final String issueCommentUrl;
	private final String contentsUrl;
	private final String compareUrl;
	private final String mergesUrl;
	private final String archiveUrl;
	private final String downloadsUrl;
	private final String issuesUrl;
	private final String pullsUrl;
	private final String milestonesUrl;
	private final String notificationsUrl;
	private final String labelsUrl;
	private final String releasesUrl;
	private final String deploymentsUrl;
	private final String createdAt;
	private final LocalDateTime updatedAt;
	private final String pushedAt;
	private final String gitUrl;
	private final String sshUrl;
	private final String cloneUrl;
	private final String svnUrl;
	private final String homepage;
	private final String size;
	private final String stargazersCount;
	private final String watchersCount;
	private final String language;
	private final String hasIssues;
	private final String hasProjects;
	private final String hasDownloads;
	private final String hasWiki;
	private final String hasPages;
	private final String hasDiscussions;
	private final String forksCount;
	private final String mirrorUrl;
	private final String archived;
	private final String disabled;
	private final String openIssuesCount;
	private final String allowForking;
	private final String isTemplate;
	private final String webCommitSignoffRequired;
	private final String visibility;
	private final String forks;
	private final String openIssues;
	private final String watchers;
	private final String defaultBranch;

	public static class GithubUser {
		private String login;
		private int id;
		private String avatarUrl;
		private String htmlUrl;

//		public GithubUser(String login, int id, String avatarUrl, String htmlUrl) {
//			this.login = login;
//			this.id = id;
//			this.avatarUrl = avatarUrl;
//			this.htmlUrl = htmlUrl;
//		}
	}

@JsonCreator
public GithubRepository(@JsonProperty("id") long id,
						@JsonProperty("node_id") String nodeId,
						@JsonProperty("name") String name,
						@JsonProperty("full_name") String fullName,
						@JsonProperty("private") boolean isPrivate,
//						@JsonProperty("owner") GithubUser owner,
						@JsonProperty("html_url") String htmlUrl,
						@JsonProperty("description") String description,
						@JsonProperty("fork") boolean isFork,
						@JsonProperty("url") String url,
						@JsonProperty("forks_url") String forksUrl,
						@JsonProperty("keys_url") String keysUrl,
						@JsonProperty("collaborators_url") String collaboratorsUrl,
						@JsonProperty("teams_url") String teamsUrl,
						@JsonProperty("hooks_url") String hooksUrl,
						@JsonProperty("issue_events_url") String issueEventsUrl,
						@JsonProperty("events_url") String eventsUrl,
						@JsonProperty("assignees_url") String assigneesUrl,
						@JsonProperty("branches_url") String branchesUrl,
						@JsonProperty("tags_url") String tagsUrl,
						@JsonProperty("blobs_url") String blobsUrl,
						@JsonProperty("git_tags_url") String gitTagsUrl,
						@JsonProperty("git_refs_url") String gitRefsUrl,
						@JsonProperty("trees_url") String treesUrl,
						@JsonProperty("statuses_url") String statusesUrl,
						@JsonProperty("languages_url") String languagesUrl,
						@JsonProperty("stargazers_url") String stargazersUrl,
						@JsonProperty("contributors_url") String contributorsUrl,
						@JsonProperty("subscribers_url") String subscribersUrl,
						@JsonProperty("subscription_url") String subscriptionUrl,
						@JsonProperty("commits_url") String commitsUrl,
						@JsonProperty("git_commits_url") String gitCommitsUrl,
						@JsonProperty("comments_url") String commentsUrl,
						@JsonProperty("issue_comment_url") String issueCommentUrl,
						@JsonProperty("contents_url") String contentsUrl,
						@JsonProperty("compare_url") String compareUrl,
						@JsonProperty("merges_url") String mergesUrl,
						@JsonProperty("archive_url") String archiveUrl,
						@JsonProperty("downloadsUrl") String downloadsUrl,
						@JsonProperty("issuesUrl") String issuesUrl,
						@JsonProperty("pullsUrl") String pullsUrl,
						@JsonProperty("milestonesUrl") String milestonesUrl,
						@JsonProperty("notificationsUrl") String notificationsUrl,
						@JsonProperty("labelsUrl") String labelsUrl,
						@JsonProperty("releasesUrl") String releasesUrl,
						@JsonProperty("deploymentsUrl") String deploymentsUrl,
						@JsonProperty("created_at") String createdAt,
						@JsonProperty("updated_at") LocalDateTime updatedAt,
						@JsonProperty("pushed_at") String pushedAt,
						@JsonProperty("git_url") String gitUrl,
						@JsonProperty("ssh_url") String sshUrl,
						@JsonProperty("clone_url") String cloneUrl,
						@JsonProperty("svn_url") String svnUrl,
						@JsonProperty("homepage") String homepage,
						@JsonProperty("size") String size,
						@JsonProperty("stargazers_count") String stargazersCount,
						@JsonProperty("watchers_count") String watchersCount,
						@JsonProperty("language") String language,
						@JsonProperty("has_issues") String hasIssues,
						@JsonProperty("has_projects") String hasProjects,
						@JsonProperty("has_downloads") String hasDownloads,
						@JsonProperty("has_wiki") String hasWiki,
						@JsonProperty("has_pages") String hasPages,
						@JsonProperty("has_discussions") String hasDiscussions,
						@JsonProperty("forks_count") String forksCount,
						@JsonProperty("mirror_url") String mirrorUrl,
						@JsonProperty("archived") String archived,
						@JsonProperty("disabled") String disabled,
						@JsonProperty("open_issues_count") String openIssuesCount,
						@JsonProperty("allow_forking") String allowForking,
						@JsonProperty("is_template") String isTemplate,
						@JsonProperty("web_commit_signoff_required") String webCommitSignoffRequired,
						@JsonProperty("visibility") String visibility,
						@JsonProperty("forks") String forks,
						@JsonProperty("open_issues") String openIssues,
						@JsonProperty("watchers") String watchers,
						@JsonProperty("default_branch") String defaultBranch)
{
//	TODO: ADD TOPICS!
		this.id = id;
		this.nodeId = nodeId;
		this.name = name;
		this.fullName = fullName;
		this.isPrivate = isPrivate;
//		this.owner = owner;
		this.htmlUrl = htmlUrl;
		this.description = description;
		this.isFork = isFork;
		this.url = url;
		this.forksUrl = forksUrl;
		this.keysUrl = keysUrl;
		this.collaboratorsUrl = collaboratorsUrl;
		this.teamsUrl = teamsUrl;
		this.hooksUrl = hooksUrl;
		this.issueEventsUrl = issueEventsUrl;
		this.eventsUrl = eventsUrl;
		this.assigneesUrl = assigneesUrl;
		this.branchesUrl = branchesUrl;
		this.tagsUrl = tagsUrl;
		this.blobsUrl = blobsUrl;
		this.gitTagsUrl = gitTagsUrl;
		this.gitRefsUrl = gitRefsUrl;
		this.treesUrl = treesUrl;
		this.statusesUrl = statusesUrl;
		this.languagesUrl = languagesUrl;
		this.stargazersUrl = stargazersUrl;
		this.contributorsUrl = contributorsUrl;
		this.subscribersUrl = subscribersUrl;
		this.subscriptionUrl = subscriptionUrl;
		this.commitsUrl = commitsUrl;
		this.gitCommitsUrl = gitCommitsUrl;
		this.commentsUrl = commentsUrl;
		this.issueCommentUrl = issueCommentUrl;
		this.contentsUrl = contentsUrl;
		this.compareUrl = compareUrl;
		this.mergesUrl = mergesUrl;
		this.archiveUrl = archiveUrl;
		this.downloadsUrl = downloadsUrl;
		this.issuesUrl = issuesUrl;
		this.pullsUrl = pullsUrl;
		this.milestonesUrl = milestonesUrl;
		this.notificationsUrl = notificationsUrl;
		this.labelsUrl = labelsUrl;
		this.releasesUrl = releasesUrl;
		this.deploymentsUrl = deploymentsUrl;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.pushedAt = pushedAt;
		this.gitUrl = gitUrl;
		this.sshUrl = sshUrl;
		this.cloneUrl = cloneUrl;
		this.svnUrl = svnUrl;
		this.homepage = homepage;
		this.size = size;
		this.stargazersCount = stargazersCount;
		this.watchersCount = watchersCount;
		this.language = language;
		this.hasIssues = hasIssues;
		this.hasProjects = hasProjects;
		this.hasDownloads = hasDownloads;
		this.hasWiki = hasWiki;
		this.hasPages = hasPages;
		this.hasDiscussions = hasDiscussions;
		this.forksCount = forksCount;
		this.mirrorUrl = mirrorUrl;
		this.archived = archived;
		this.disabled = disabled;
		this.openIssuesCount = openIssuesCount;
		this.allowForking = allowForking;
		this.isTemplate = isTemplate;
		this.webCommitSignoffRequired = webCommitSignoffRequired;
		this.visibility = visibility;
		this.forks = forks;
		this.openIssues = openIssues;
		this.watchers = watchers;
		this.defaultBranch = defaultBranch;
	}

	public Long getId() {
		return id;
	}

	public String getNodeId() {
		return nodeId;
	}

	public String getName() {
		return name;
	}

	public String getFullName() {
		return fullName;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

//	public GithubUser getOwner() {
//		return owner;
//	}

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public String getDescription() {
		return description;
	}

	public boolean isFork() {
		return isFork;
	}

	public String getUrl() {
		return url;
	}

	public String getForksUrl() {
		return forksUrl;
	}

	public String getKeysUrl() {
		return keysUrl;
	}

	public String getCollaboratorsUrl() {
		return collaboratorsUrl;
	}

	public String getTeamsUrl() {
		return teamsUrl;
	}

	public String getHooksUrl() {
		return hooksUrl;
	}

	public String getIssueEventsUrl() {
		return issueEventsUrl;
	}

	public String getEventsUrl() {
		return eventsUrl;
	}

	public String getAssigneesUrl() {
		return assigneesUrl;
	}

	public String getBranchesUrl() {
		return branchesUrl;
	}

	public String getTagsUrl() {
		return tagsUrl;
	}

	public String getBlobsUrl() {
		return blobsUrl;
	}

	public String getGitTagsUrl() {
		return gitTagsUrl;
	}

	public String getGitRefsUrl() {
		return gitRefsUrl;
	}

	public String getTreesUrl() {
		return treesUrl;
	}

	public String getStatusesUrl() {
		return statusesUrl;
	}

	public String getLanguagesUrl() {
		return languagesUrl;
	}

	public String getStargazersUrl() {
		return stargazersUrl;
	}

	public String getContributorsUrl() {
		return contributorsUrl;
	}

	public String getSubscribersUrl() {
		return subscribersUrl;
	}

	public String getSubscriptionUrl() {
		return subscriptionUrl;
	}

	public String getCommitsUrl() {
		return commitsUrl;
	}

	public String getGitCommitsUrl() {
		return gitCommitsUrl;
	}

	public String getCommentsUrl() {
		return commentsUrl;
	}

	public String getIssueCommentUrl() {
		return issueCommentUrl;
	}

	public String getContentsUrl() {
		return contentsUrl;
	}

	public String getCompareUrl() {
		return compareUrl;
	}

	public String getMergesUrl() {
		return mergesUrl;
	}

	public String getArchiveUrl() {
		return archiveUrl;
	}

	public String getDownloadsUrl() {
		return downloadsUrl;
	}

	public String getIssuesUrl() {
		return issuesUrl;
	}

	public String getPullsUrl() {
		return pullsUrl;
	}

	public String getMilestonesUrl() {
		return milestonesUrl;
	}

	public String getNotificationsUrl() {
		return notificationsUrl;
	}

	public String getLabelsUrl() {
		return labelsUrl;
	}

	public String getReleasesUrl() {
		return releasesUrl;
	}

	public String getDeploymentsUrl() {
		return deploymentsUrl;
	}


	public String getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public String getPushedAt() {
		return pushedAt;
	}

	public String getGitUrl() {
		return gitUrl;
	}

	public String getSshUrl() {
		return sshUrl;
	}

	public String getCloneUrl() {
		return cloneUrl;
	}

	public String getSvnUrl() {
		return svnUrl;
	}

	public String getHomepage() {
		return homepage;
	}

	public String getSize() {
		return size;
	}

	public String getStargazersCount() {
		return stargazersCount;
	}

	public String getWatchersCount() {
		return watchersCount;
	}

	public String getLanguage() {
		return language;
	}

	public String getHasIssues() {
		return hasIssues;
	}

	public String getHasProjects() {
		return hasProjects;
	}

	public String getHasDownloads() {
		return hasDownloads;
	}

	public String getHasWiki() {
		return hasWiki;
	}

	public String getHasPages() {
		return hasPages;
	}

	public String getHasDiscussions() {
		return hasDiscussions;
	}

	public String getForksCount() {
		return forksCount;
	}

	public String getMirrorUrl() {
		return mirrorUrl;
	}

	public String getArchived() {
		return archived;
	}

	public String getDisabled() {
		return disabled;
	}

	public String getOpenIssuesCount() {
		return openIssuesCount;
	}

	public String getAllowForking() {
		return allowForking;
	}

	public String getIsTemplate() {
		return isTemplate;
	}

	public String getWebCommitSignoffRequired() {
		return webCommitSignoffRequired;
	}

	public String getVisibility() {
		return visibility;
	}

	public String getForks() {
		return forks;
	}

	public String getOpenIssues() {
		return openIssues;
	}

	public String getWatchers() {
		return watchers;
	}

	public String getDefaultBranch() {
		return defaultBranch;
	}

}