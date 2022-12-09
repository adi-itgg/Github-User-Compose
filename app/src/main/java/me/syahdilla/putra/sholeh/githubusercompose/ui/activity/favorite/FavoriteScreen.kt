@file:OptIn(ExperimentalMaterial3Api::class)

package me.syahdilla.putra.sholeh.githubusercompose.ui.activity.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import me.syahdilla.putra.sholeh.githubusercompose.R
import me.syahdilla.putra.sholeh.githubusercompose.repository.DatabaseRepository
import me.syahdilla.putra.sholeh.githubusercompose.ui.activity.AddSpacerList
import me.syahdilla.putra.sholeh.githubusercompose.ui.activity.CardUser
import me.syahdilla.putra.sholeh.githubusercompose.ui.activity.DefaultCenteredActionBar
import org.koin.androidx.compose.get

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    databaseRepository: DatabaseRepository = get(),
    onBackPressed: () -> Unit
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier,
        topBar = {
            DefaultCenteredActionBar(
                title = stringResource(id = R.string.favorite),
                onBackPressed = onBackPressed
            )
        }
    ) { padding ->
        val userList by databaseRepository.favoriteGetUsersAsFlow().collectAsState(initial = null)

        if (userList?.isEmpty() == true)
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = stringResource(id = R.string.no_user_favorite),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
            }


        userList?.let { users ->
            LazyColumn(
                modifier = Modifier.padding(padding)
            ) {
                itemsIndexed(items = users, key = { _, user -> user.id }) { index, user ->
                    AddSpacerList(index = index, total = users.size) {
                        CardUser(userDetails = user)
                    }
                }
            }
        }
    }
}
