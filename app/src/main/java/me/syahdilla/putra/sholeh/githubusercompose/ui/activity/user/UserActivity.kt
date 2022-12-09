package me.syahdilla.putra.sholeh.githubusercompose.ui.activity.user

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import me.syahdilla.putra.sholeh.githubusercompose.data.User
import me.syahdilla.putra.sholeh.githubusercompose.data.UserDetails
import me.syahdilla.putra.sholeh.githubusercompose.ui.theme.GithubUserComposeTheme
import me.syahdilla.putra.sholeh.githubusercompose.utils.getParcel
import me.syahdilla.putra.sholeh.githubusercompose.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserActivity : ComponentActivity() {

    private val viewModel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val onBackPressed = {
            onBackPressedDispatcher.onBackPressed()
        }

        intent.getParcel<UserDetails>()?.let {
            viewModel.userDetails = it
        } ?: intent.getParcel<User>()?.let {
            viewModel.user = it
        } ?: return onBackPressed()

        setContent {
            GithubUserComposeTheme {
                if (viewModel.isUserDetailsInitialized && viewModel.userDetails.id == -1L)
                    AboutMeView(onBackPressed = onBackPressed)
                else
                    SearchUserView(onBackPressed = onBackPressed)
            }
        }
    }

}
