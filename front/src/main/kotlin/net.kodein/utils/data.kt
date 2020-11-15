package net.kodein.utils

sealed class SocialMedia(val service: String, val url: String, val handler: String)
class Twitter(handler: String) : SocialMedia("twitter", "https://twitter.com/", handler)
class LinkedIn(handler: String) : SocialMedia("linkedin", "https://www.linkedin.com/in/", handler)
class Github(handler: String) : SocialMedia("github", "https://github.com/", handler)
