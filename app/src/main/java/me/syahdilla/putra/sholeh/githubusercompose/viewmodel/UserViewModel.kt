package me.syahdilla.putra.sholeh.githubusercompose.viewmodel

import androidx.lifecycle.ViewModel
import me.syahdilla.putra.sholeh.githubusercompose.data.User
import me.syahdilla.putra.sholeh.githubusercompose.data.UserDetails

class UserViewModel: ViewModel() {

    lateinit var user: User
    val isUserInitialized
    get() = this::user.isInitialized

    lateinit var userDetails: UserDetails
    val isUserDetailsInitialized
    get() = this::userDetails.isInitialized

    val getUserId: Long
    get() = if (isUserDetailsInitialized)
        userDetails.id
    else
        user.id

}