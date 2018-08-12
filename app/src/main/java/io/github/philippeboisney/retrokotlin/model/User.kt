package io.github.philippeboisney.retrokotlin.model

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("id") val id: String,
                @SerializedName("login") val login: String,
                @SerializedName("avatar_url") val avatarUrl: String,
                @SerializedName("name") val name: String,
                @SerializedName("company") val company: String,
                @SerializedName("blog") val blog: String)