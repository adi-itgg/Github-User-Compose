package me.syahdilla.putra.sholeh.githubusercompose.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

enum class SearchResultStatus {
    SUCCESS,
    NO_USER,
    ERROR
}

@Serializable
data class SearchResult(
    @SerialName("items")
    val items: List<User> = listOf(),
    @SerialName("total_count")
    val totalCount: Long = -1L,
    @SerialName("message")
    val message: String = "",
    val status: SearchResultStatus = if (items.isEmpty() && totalCount == 0L)
        SearchResultStatus.NO_USER
    else if (items.isEmpty() && totalCount == -1L)
        SearchResultStatus.ERROR else SearchResultStatus.SUCCESS
)