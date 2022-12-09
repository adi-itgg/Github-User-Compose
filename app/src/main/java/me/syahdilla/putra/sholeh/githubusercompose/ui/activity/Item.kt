@file:OptIn(ExperimentalMaterial3Api::class)

package me.syahdilla.putra.sholeh.githubusercompose.ui.activity

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import me.syahdilla.putra.sholeh.githubusercompose.R
import me.syahdilla.putra.sholeh.githubusercompose.ui.activity.user.UserActivity
import me.syahdilla.putra.sholeh.githubusercompose.data.User
import me.syahdilla.putra.sholeh.githubusercompose.data.UserDetails
import me.syahdilla.putra.sholeh.githubusercompose.repository.DatabaseRepository
import org.koin.androidx.compose.get

@Composable
fun CardUser(
    modifier: Modifier = Modifier,
    user: User = User(),
    userDetails: UserDetails = UserDetails(),
    databaseRepository: DatabaseRepository = get()
) {
    val isNotUserDetails = user.id != -1L
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        onClick = {
            context.startActivity(Intent(context, UserActivity::class.java).apply {
                if (isNotUserDetails)
                    putExtra(User::class.java.simpleName, user)
                else
                    putExtra(UserDetails::class.java.simpleName, userDetails)
            })
        }
    ) {
        Box {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(12.dp)
            ) {
                AsyncImage(
                    model = if (isNotUserDetails) user.avatarUrl else userDetails.avatarUrl,
                    contentDescription = stringResource(id = R.string.content_desc_photo),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(2.dp)
                        .size(60.dp)
                        .clip(CircleShape),
                    error = painterResource(id = R.drawable.ic_round_person_24)
                )
                Text(
                    text = if (isNotUserDetails) user.name else userDetails.name ?: userDetails.login,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = if (isNotUserDetails) 12.dp else 54.dp)
                )
            }
            if (user.id == -1L)
                IconButton(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 14.dp),
                    onClick = {
                        scope.launch {
                            databaseRepository.favoriteRemove(userDetails.id)
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = stringResource(id = R.string.content_desc_btn_remove_favorite),
                    )
                }
        }
    }
}