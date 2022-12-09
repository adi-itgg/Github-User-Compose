package me.syahdilla.putra.sholeh.githubusercompose.ui.activity.favorite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import me.syahdilla.putra.sholeh.githubusercompose.ui.theme.GithubUserComposeTheme

class FavoriteActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GithubUserComposeTheme {
                FavoriteScreen {
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        }

    }

}