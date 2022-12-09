package me.syahdilla.putra.sholeh.githubusercompose.di

import me.syahdilla.putra.sholeh.githubusercompose.repository.ApiRepository
import me.syahdilla.putra.sholeh.githubusercompose.repository.DatabaseRepository
import me.syahdilla.putra.sholeh.githubusercompose.repository.database.FavoriteDatabase
import me.syahdilla.putra.sholeh.githubusercompose.repository.database.FavoriteDatabaseImpl
import me.syahdilla.putra.sholeh.githubusercompose.utils.createDefaultHttpClient
import me.syahdilla.putra.sholeh.githubusercompose.viewmodel.MainViewModel
import me.syahdilla.putra.sholeh.githubusercompose.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val mainFeatures by lazy {
    listOf(
        singletonModule,
        viewModelModule
    )
}

val singletonModule = module {
    single { false }
    single { createDefaultHttpClient() }
    singleOf(::ApiRepository)
    singleOf(::FavoriteDatabaseImpl) { bind<FavoriteDatabase>() }
    singleOf(::DatabaseRepository)
}

val viewModelModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::UserViewModel)
}