package me.syahdilla.putra.sholeh.githubusercompose.data


import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
@Entity(tableName = "user")
data class UserDetails(
    @PrimaryKey
    @SerialName("id")
    val id: Long = -1,
    @SerialName("avatar_url")
    val avatarUrl: String = "",
    @SerialName("company")
    val company: String? = null,
    @SerialName("followers")
    val followers: Int = -1,
    @SerialName("following")
    val following: Int = -1,
    @SerialName("location")
    val location: String? = null,
    @SerialName("login")
    val login: String = "",
    @SerialName("name")
    val name: String? = "",
    @SerialName("twitter_username")
    val twitterUsername: String? = null,
    @SerialName("url")
    val url: String = "",
    @SerialName("email")
    val email: String? = null,
    @DrawableRes
    val drawable: Int = -1,
    val isUserAboutMe: Boolean = false
) : Parcelable