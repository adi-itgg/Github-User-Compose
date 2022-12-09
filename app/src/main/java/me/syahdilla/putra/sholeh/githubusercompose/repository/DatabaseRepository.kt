package me.syahdilla.putra.sholeh.githubusercompose.repository

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext
import me.syahdilla.putra.sholeh.githubusercompose.data.UserDetails
import me.syahdilla.putra.sholeh.githubusercompose.repository.database.FavoriteDatabase
import me.syahdilla.putra.sholeh.githubusercompose.utils.loggerFactory

class DatabaseRepository(
    val favoriteDatabase: FavoriteDatabase,
    isTestMode: Boolean = false
) {

    private val logger by loggerFactory(isTestMode = isTestMode)

    suspend fun favoriteInsert(user: UserDetails) = withContext(IO) { favoriteDatabase.dao.insert(user = user) }

    suspend fun favoriteRemove(userId: Long) = withContext(IO) {
        favoriteDatabase.dao.remove(id = userId).also {
            if (it == 1)
                logger.debug { "Removed user id $userId from favorite" }
            else
                logger.error { "Failure remove user id $userId from favorite" }
        }
    }

    fun favoriteGetUsersAsFlow() = favoriteDatabase.dao.getAllAsFlow()

    fun favoriteGetUsers() = favoriteDatabase.dao.getAll()

    suspend fun observe(userId: Long, lifecycle: Lifecycle, onUpdate: (UserDetails?) -> Unit) {
        logger.debug { "Observe user id $userId" }
        favoriteGetUsersAsFlow().flowWithLifecycle(lifecycle = lifecycle).collectLatest {
            val user = it.firstOrNull { user -> user.id == userId }
            onUpdate(user)
            logger.debug { "Updated user ${user?.name ?: userId}" }
        }
    }

}