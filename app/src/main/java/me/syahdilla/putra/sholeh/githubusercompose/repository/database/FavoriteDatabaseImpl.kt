package me.syahdilla.putra.sholeh.githubusercompose.repository.database

import android.content.Context
import androidx.room.Room

class FavoriteDatabaseImpl(
    private val appContext: Context,
    private val isTestMode: Boolean = false
): FavoriteDatabase {

    companion object {
        private const val DATABASE_NAME = "favorite"
    }

    override val database by lazy {
        if (isTestMode)
            Room.databaseBuilder(appContext, FavoriteDatabaseRoom::class.java, DATABASE_NAME).build()
        else
            Room.inMemoryDatabaseBuilder(appContext, FavoriteDatabaseRoom::class.java).build()
    }

    override val dao: FavoriteDao
    get() = database.dao()

}