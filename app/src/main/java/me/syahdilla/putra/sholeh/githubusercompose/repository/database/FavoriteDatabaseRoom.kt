package me.syahdilla.putra.sholeh.githubusercompose.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import me.syahdilla.putra.sholeh.githubusercompose.data.UserDetails

@Database(entities = [UserDetails::class], version = 1, exportSchema = false)
abstract class FavoriteDatabaseRoom: RoomDatabase() {
    abstract fun dao(): FavoriteDao
}