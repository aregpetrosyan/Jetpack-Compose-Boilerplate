package com.aregyan.github.network.model


import com.aregyan.github.database.DatabaseUserDetails
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkUserDetails(
    @Json(name = "avatar_url")
    val avatarUrl: String,
    @Json(name = "bio")
    val bio: String? = null,
    @Json(name = "blog")
    val blog: String? = null,
    @Json(name = "company")
    val company: String? = null,
    @Json(name = "created_at")
    val createdAt: String? = null,
    @Json(name = "email")
    val email: String? = null,
    @Json(name = "events_url")
    val eventsUrl: String? = null,
    @Json(name = "followers")
    val followers: Int? = null,
    @Json(name = "followers_url")
    val followersUrl: String? = null,
    @Json(name = "following")
    val following: Int? = null,
    @Json(name = "following_url")
    val followingUrl: String? = null,
    @Json(name = "gists_url")
    val gistsUrl: String? = null,
    @Json(name = "gravatar_id")
    val gravatarId: String? = null,
    @Json(name = "hireable")
    val hireable: Boolean? = null,
    @Json(name = "html_url")
    val htmlUrl: String? = null,
    @Json(name = "id")
    val id: Int,
    @Json(name = "location")
    val location: String? = null,
    @Json(name = "login")
    val login: String,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "node_id")
    val nodeId: String? = null,
    @Json(name = "organizations_url")
    val organizationsUrl: String? = null,
    @Json(name = "public_gists")
    val publicGists: Int? = null,
    @Json(name = "public_repos")
    val publicRepos: Int? = null,
    @Json(name = "received_events_url")
    val receivedEventsUrl: String? = null,
    @Json(name = "repos_url")
    val reposUrl: String? = null,
    @Json(name = "site_admin")
    val siteAdmin: Boolean? = null,
    @Json(name = "starred_url")
    val starredUrl: String? = null,
    @Json(name = "subscriptions_url")
    val subscriptionsUrl: String? = null,
    @Json(name = "twitter_username")
    val twitterUsername: String? = null,
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "updated_at")
    val updatedAt: String? = null,
    @Json(name = "url")
    val url: String? = null
)

fun NetworkUserDetails.asDatabaseModel(): DatabaseUserDetails {
    return DatabaseUserDetails(
        user = login,
        avatar = avatarUrl,
        name = name ?: "",
        userSince = createdAt ?: "",
        location = location ?: ""
    )

}