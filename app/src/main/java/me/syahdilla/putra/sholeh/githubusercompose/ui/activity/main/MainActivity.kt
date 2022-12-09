package me.syahdilla.putra.sholeh.githubusercompose.ui.activity.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.syahdilla.putra.sholeh.githubusercompose.R
import me.syahdilla.putra.sholeh.githubusercompose.ui.theme.GithubUserComposeTheme
import me.syahdilla.putra.sholeh.githubusercompose.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubUserComposeTheme {
                MainScreen(mainViewModel = viewModel)
            }
        }

        handleOnBackPressed()
    }

    private fun handleOnBackPressed() {
        var backJob: Job? = null
        onBackPressedDispatcher.addCallback {
            if (backJob?.isActive == true) return@addCallback finish()
            Toast.makeText(this@MainActivity, resources.getString(R.string.toast_single_back_pressed), Toast.LENGTH_SHORT).show()
            backJob = lifecycleScope.launch {
                delay(2_000)
            }
        }
    }

}
