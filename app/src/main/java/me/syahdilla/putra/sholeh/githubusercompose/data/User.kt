package me.syahdilla.putra.sholeh.githubusercompose.data


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class User(
    @SerialName("id")
    val id: Long = -1,
    @SerialName("avatar_url")
    val avatarUrl: String = "",
    @SerialName("login")
    val login: String = "",
    @SerialName("name")
    val name: String = login,
    @SerialName("url")
    val url: String = ""
) : Parcelable