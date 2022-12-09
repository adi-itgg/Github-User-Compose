package me.syahdilla.putra.sholeh.githubusercompose.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import me.syahdilla.putra.sholeh.githubusercompose.data.User
import me.syahdilla.putra.sholeh.githubusercompose.ui.activity.SearchViewState
import me.syahdilla.putra.sholeh.githubusercompose.utils.loggerFactory

class MainViewModel: ViewModel() {

    private val logger by loggerFactory()

    private val _userList = mutableStateOf(emptyList<User>())
    val userListState: State<List<User>>
    get() = _userList

    private val _isLoading = mutableStateOf(false)
    val loadingState: State<Boolean>
    get() = _isLoading

    private val _searchViewState = mutableStateOf(value = SearchViewState.CLOSE)
    val searchViewState: State<SearchViewState>
    get() = _searchViewState

    private val _searchTextState = mutableStateOf(value = "")
    val searchTextState: State<String>
    get() = _searchTextState

    fun updateSearchState(newValue: SearchViewState) {
        _searchViewState.value = newValue
        logger.debug { "Updated search view state to $newValue" }
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
        logger.debug { "Updated search view text state to $newValue" }
    }

    fun updateLoadingState(newValue: Boolean) {
        _isLoading.value = newValue
        logger.debug { "Updated search loading state to $newValue" }
    }

    fun updateUserList(newValue: List<User>) {
        _userList.value = newValue
        logger.debug { "Updated new data list with size ${newValue.size}" }
    }

}