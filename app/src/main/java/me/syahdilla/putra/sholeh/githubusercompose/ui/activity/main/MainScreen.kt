@file:OptIn(ExperimentalMaterial3Api::class)

package me.syahdilla.putra.sholeh.githubusercompose.ui.activity.main

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import me.syahdilla.putra.sholeh.githubusercompose.R
import me.syahdilla.putra.sholeh.githubusercompose.data.SearchResultStatus
import me.syahdilla.putra.sholeh.githubusercompose.data.User
import me.syahdilla.putra.sholeh.githubusercompose.repository.ApiRepository
import me.syahdilla.putra.sholeh.githubusercompose.ui.activity.*
import me.syahdilla.putra.sholeh.githubusercompose.viewmodel.MainViewModel
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = koinViewModel(),
    apiRepository: ApiRepository = get()
) {

    val coroutineScope = rememberCoroutineScope()

    val searchViewState by mainViewModel.searchViewState
    val searchTextState by mainViewModel.searchTextState
    val loadingState by mainViewModel.loadingState
    val userList by mainViewModel.userListState

    var searchJob: Job? = null
    val context = LocalContext.current
    val noUserMessage = stringResource(id = R.string.toast_search_no_user)

    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
        MainAppBar(
            searchViewState = searchViewState,
            searchTextState = searchTextState,
            onTextChange = {
                mainViewModel.updateSearchTextState(newValue = it)
            },
            onCloseClicked = {
                mainViewModel.updateSearchTextState(newValue = "")
                mainViewModel.updateSearchState(newValue = SearchViewState.CLOSE)
            },
            onSearchClicked = { query ->
                if (searchJob?.isActive == true) searchJob?.cancel()
                mainViewModel.updateLoadingState(true)
                searchJob = coroutineScope.launch {
                    val result = apiRepository.searchUser(query = query)
                    when(result.status) {
                        SearchResultStatus.SUCCESS -> {}
                        SearchResultStatus.NO_USER -> Toast.makeText(context, noUserMessage, Toast.LENGTH_SHORT).show()
                        SearchResultStatus.ERROR -> Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
                    }
                    mainViewModel.updateUserList(result.items)
                    mainViewModel.updateLoadingState(false)
                }
            }
        ) {
            mainViewModel.updateSearchState(newValue = SearchViewState.OPEN)
        }
    }) {

        ListUser(
            isLoading = loadingState,
            userList = userList,
            modifier = Modifier.padding(it)
        )

    }

}

@Preview
@Composable
fun PreviewDefaultAppBar() {
    DefaultAppBar(onSearchClicked = {})
}

@Preview
@Composable
fun SearchAppBarPreview() {
    SearchAppbar(
        text = "Apa yaa",
        onTextChange = {},
        onCloseClicked = {},
        onSearchClicked = {}
    )
}

@Preview
@Composable
fun CardUserPreview() {
    CardUser(user = User())
}