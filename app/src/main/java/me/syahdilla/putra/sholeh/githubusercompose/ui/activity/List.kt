package me.syahdilla.putra.sholeh.githubusercompose.ui.activity

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.syahdilla.putra.sholeh.githubusercompose.data.User

@Composable
fun AddSpacerList(
    modifier: Modifier = Modifier,
    index: Int, total: Int,
    block: @Composable () -> Unit
) {
    if (index == 0)
        Spacer(modifier = modifier.height(12.dp))
    block()
    if (index == (total - 1))
        Spacer(modifier = modifier.height(12.dp))
}


@Composable
fun ListUser(
    isLoading: Boolean,
    userList: List<User>,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        LazyColumn {
            if (isLoading) {
                val total = 6
                items(count = total) { index ->
                    AddSpacerList(index = index, total = total) {
                        ShimmerAnimation {
                            ShimmerCardUserItem(brush = it)
                        }
                    }
                }
            } else
                itemsIndexed(items = userList, key = { _, user -> user.id }) { index, user ->
                    AddSpacerList(index = index, total = userList.size) {
                        CardUser(user = user, modifier = Modifier.fillMaxWidth())
                    }
                }
        }
    }
}
