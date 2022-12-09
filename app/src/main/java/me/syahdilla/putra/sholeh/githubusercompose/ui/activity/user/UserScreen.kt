@file:OptIn(ExperimentalMaterial3Api::class)

package me.syahdilla.putra.sholeh.githubusercompose.ui.activity.user

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import me.syahdilla.putra.sholeh.githubusercompose.R
import me.syahdilla.putra.sholeh.githubusercompose.ui.activity.ShimmerAnimation
import me.syahdilla.putra.sholeh.githubusercompose.data.UserDetails
import me.syahdilla.putra.sholeh.githubusercompose.repository.ApiRepository
import me.syahdilla.putra.sholeh.githubusercompose.repository.DatabaseRepository
import me.syahdilla.putra.sholeh.githubusercompose.ui.activity.ShimmerUser
import me.syahdilla.putra.sholeh.githubusercompose.viewmodel.UserViewModel
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel

@Composable
fun AboutMeView(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.about_me),
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.content_desc_btn_back),
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            )
        },
    ) { padding ->
        UserScreen(modifier = Modifier.padding(padding), onBackPressed = onBackPressed)
    }
}


@Composable
fun SearchUserView(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        UserScreen(onBackPressed = onBackPressed)
    }
}

@Composable
fun UserScreen(
    modifier: Modifier = Modifier,
    userViewModel: UserViewModel = koinViewModel(),
    apiRepository: ApiRepository = get(),
    databaseRepository: DatabaseRepository = get(),
    onBackPressed: () -> Unit
) {
    var user by remember { mutableStateOf<UserDetails?>(null) }
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    Column {
        Box(
            modifier = modifier.fillMaxWidth()
        ) {
            if ((userViewModel.isUserInitialized && userViewModel.user.id != -1L) || (userViewModel.isUserDetailsInitialized && !userViewModel.userDetails.isUserAboutMe))
                IconButton(
                    modifier = Modifier.padding(start = 14.dp, top = 14.dp),
                    onClick = onBackPressed
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.content_desc_btn_back)
                    )
                }
            if (user?.id != -1L) {
                val scope = rememberCoroutineScope()
                var isFavorited by remember { mutableStateOf(false) }
                var favJob: Job? = null
                androidx.compose.animation.AnimatedVisibility(
                    modifier = Modifier
                        .padding(end = 14.dp, top = 14.dp)
                        .align(alignment = Alignment.TopEnd),
                    visible = user != null,
                    enter = fadeIn(animationSpec = tween(1000))
                ) {
                    // Favorite button icon
                    IconButton(
                        onClick = {
                            if (favJob?.isActive == true) favJob?.cancel()
                            favJob = scope.launch {
                                if (isFavorited)
                                    databaseRepository.favoriteRemove(userViewModel.getUserId)
                                else user?.let { databaseRepository.favoriteInsert(it) }
                            }
                        }
                    ) {
                        Crossfade(targetState = isFavorited) {
                            Icon(
                                imageVector = if (it) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                                contentDescription = stringResource(id = if (isFavorited) R.string.content_desc_btn_remove_favorite else R.string.content_desc_btn_add_favorite)
                            )
                        }
                    }
                }
                // observe data favorite
                LaunchedEffect(Unit) {
                    scope.launch {
                        databaseRepository.observe(
                            userId = userViewModel.getUserId,
                            lifecycle = lifecycle
                        ) {
                            isFavorited = it != null
                        }
                    }
                }
            }
        }
        LaunchedEffect(Unit) {
            if (userViewModel.isUserDetailsInitialized) {
                user = userViewModel.userDetails
                return@LaunchedEffect
            }
            apiRepository.getUserDetails(userViewModel.user).onSuccess {
                user = it
            }.onFailure {
                onBackPressed()
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(1) {
                DisplayUser(user = user)
            }
        }
    }
}


@Composable
fun DisplayUser(
    user: UserDetails?
) {
    if (user == null) {
        ShimmerAnimation {
            ShimmerUser(brush = it)
        }
        return
    }
    if (user.drawable != -1 && user.isUserAboutMe)
        Image(
            painter = painterResource(id = user.drawable),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(id = R.string.content_desc_photo),
            modifier = Modifier
                .padding(top = 64.dp, bottom = 24.dp)
                .size(160.dp)
                .clip(CircleShape)
        )
    else
        AsyncImage(
            model = user.avatarUrl,
            contentDescription = stringResource(id = R.string.content_desc_photo),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 14.dp, bottom = 24.dp)
                .size(160.dp)
                .clip(CircleShape),
            error = painterResource(id = R.drawable.ic_round_person_24)
        )

    Text(
        text = user.name ?: user.login,
        fontSize = MaterialTheme.typography.titleLarge.fontSize,
        fontWeight = FontWeight.Bold
    )
    if (user.login.isNotEmpty())
        Text(text = "@${user.login}")
    if (user.email != null && user.email.isNotEmpty())
        Text(text = user.email)

    if (user.following != -1 && user.followers != -1) {
        Icon(
            modifier = Modifier.padding(top = 24.dp),
            painter = painterResource(id = R.drawable.ic_round_people_alt_24),
            contentDescription = stringResource(id = R.string.content_desc_icon_followers)
        )
        Text(
            text = stringResource(id = R.string.followers_following_format).format(
                user.followers,
                user.following
            )
        )
    }
    user.company?.let {
        Icon(
            modifier = Modifier.padding(top = 14.dp),
            painter = painterResource(id = R.drawable.ic_round_domain_24),
            contentDescription = stringResource(id = R.string.content_desc_icon_company)
        )
        Text(text = it)
    }
    user.location?.let {
        Icon(
            modifier = Modifier.padding(top = 14.dp),
            painter = painterResource(id = R.drawable.ic_round_location_on_24),
            contentDescription = stringResource(id = R.string.content_desc_icon_location)
        )
        Text(text = it)
    }
    user.twitterUsername?.let {
        Icon(
            modifier = Modifier.padding(top = 14.dp),
            painter = painterResource(id = R.drawable.ic_twitter),
            contentDescription = stringResource(id = R.string.content_desc_icon_twitter)
        )
        Text(text = it)
    }

    Spacer(modifier = Modifier.height(32.dp))
}


@Preview(showBackground = true)
@Composable
fun UserDisplayPreview() {
    val fakeUser = UserDetails(
        name = "Syahdilla",
        login = "adisps",
        twitterUsername = "adispstweet"
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DisplayUser(user = fakeUser)
    }
}