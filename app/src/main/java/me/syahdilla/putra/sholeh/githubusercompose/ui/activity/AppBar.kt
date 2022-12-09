@file:OptIn(ExperimentalMaterial3Api::class)

package me.syahdilla.putra.sholeh.githubusercompose.ui.activity

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import me.syahdilla.putra.sholeh.githubusercompose.R

@Composable
fun MainAppBar(
    modifier: Modifier = Modifier,
    searchViewState: SearchViewState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit
) {
    when(searchViewState) {
        SearchViewState.CLOSE -> DefaultAppBar(
            modifier = modifier,
            onSearchClicked = onSearchTriggered
        )
        SearchViewState.OPEN -> {
            SearchAppbar(
                text = searchTextState,
                modifier = modifier,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked
            )
        }
    }
}

@Composable
fun SearchAppbar(
    text: String,
    modifier: Modifier = Modifier,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        SearchView(
            text = text,
            onTextChanged = onTextChange,
            onCloseClicked = onCloseClicked,
            onSearchClicked = onSearchClicked
        )
    }
}

@Composable
fun DefaultAppBar(
    modifier: Modifier = Modifier,
    onSearchClicked: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.titleLarge
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        actions = {
            AddSearchClickableIcon(onClick = onSearchClicked)
            AddFavoriteIcon()
            AddAboutIcon()
        }
    )
}

@Composable
fun DefaultCenteredActionBar(
    title: String,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = { AddBackIcon(onClick = onBackPressed) }
    )
}