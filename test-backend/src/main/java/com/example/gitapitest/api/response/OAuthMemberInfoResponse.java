package com.example.gitapitest.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class OAuthMemberInfoResponse {
    @JsonProperty("login")
    String login;
    @JsonProperty("id")
    String id;
    @JsonProperty("node_id")
    String nodeId;
    @JsonProperty("avatar_url")
    String avatarUrl;
    @JsonProperty("gravatar_id")
    String gravatarId;
    @JsonProperty("url")
    String url;
    @JsonProperty("html_url")
    String htmlUrl;
    @JsonProperty("followers_url")
    String followersUrl;
    @JsonProperty("following_url")
    String followingUrl;
    @JsonProperty("gists_url")
    String gistsUrl;
    @JsonProperty("starred_url")
    String starredUrl;
    @JsonProperty("subscriptions_url")
    String subscriptionsUrl;
    @JsonProperty("organizations_url")
    String organizationsUrl;
    @JsonProperty("repos_url")
    String reposUrl;
    @JsonProperty("events_url")
    String eventsUrl;
    @JsonProperty("received_events_url")
    String receivedEventsUrl;
    @JsonProperty("type")
    String type;
    @JsonProperty("site_admin")
    String siteAdmin;
    @JsonProperty("name")
    String name;
    @JsonProperty("company")
    String company;
    @JsonProperty("blog")
    String blog;
    @JsonProperty("location")
    String location;
    @JsonProperty("email")
    String email;
    @JsonProperty("hireable")
    String hireable;
    @JsonProperty("bio")
    String bio;
    @JsonProperty("twitter_username")
    String twitterUsername;
    @JsonProperty("public_repos")
    String publicRepos;
    @JsonProperty("public_gists")
    String publicGists;
    @JsonProperty("followers")
    String followers;
    @JsonProperty("following")
    String following;
    @JsonProperty("created_at")
    String createdAt;
    @JsonProperty("updated_at")
    String updatedAt;
}
