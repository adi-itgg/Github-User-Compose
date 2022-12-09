package me.syahdilla.putra.sholeh.githubusercompose.repository.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.syahdilla.putra.sholeh.githubusercompose.data.UserDetails

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM user")
    fun getAllAsFlow(): Flow<List<UserDetails>>

    @Query("SELECT * FROM user")
    fun getAll(): List<UserDetails>

    @Insert(onConflict = REPLACE)
    fun insert(user: UserDetails)

    /**
     * @return 1 when successfully, 0 otherwise
     */
    @Query("DELETE FROM user WHERE id =:id")
    fun remove(id: Long): Int

}