package me.syahdilla.putra.sholeh.githubusercompose.ui.activity

import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import me.syahdilla.putra.sholeh.githubusercompose.R
import me.syahdilla.putra.sholeh.githubusercompose.ui.activity.favorite.FavoriteActivity
import me.syahdilla.putra.sholeh.githubusercompose.ui.activity.user.UserActivity
import me.syahdilla.putra.sholeh.githubusercompose.data.UserDetails

@Composable
fun AddBackIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(id = R.string.content_desc_btn_back),
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
fun AddFavoriteIcon(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    IconButton(
        modifier = modifier,
        onClick = {
            context.startActivity(
                Intent(context, FavoriteActivity::class.java)
            )
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = stringResource(id = R.string.content_desc_btn_favorite),
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}


@Composable
fun AddAboutIcon(
    modifier: Modifier = Modifier
) {
    val me = UserDetails(
        name = stringResource(id = R.string.about_me_user_name),
        email = stringResource(id = R.string.about_me_user_email),
        drawable = R.drawable.my_photo,
        isUserAboutMe = true
    )
    val context = LocalContext.current
    IconButton(
        modifier = modifier,
        onClick = {
            context.startActivity(
                Intent(context, UserActivity::class.java)
                    .putExtra(UserDetails::class.java.simpleName, me)
            )
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = stringResource(id = R.string.content_desc_btn_about_me),
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
fun AddSearchClickableIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(id = R.string.content_desc_btn_search),
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
fun AddSearchIcon(
    modifier: Modifier = Modifier
) {
    IconButton(
        modifier = modifier.alpha(0.4f),
        onClick = {}
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = stringResource(id = R.string.content_desc_btn_search),
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
fun AddCloseIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = stringResource(id = R.string.content_desc_btn_close_search),
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}