@file:OptIn(ExperimentalCoroutinesApi::class)

package me.syahdilla.putra.sholeh.githubusercompose

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import me.syahdilla.putra.sholeh.githubusercompose.data.UserDetails
import me.syahdilla.putra.sholeh.githubusercompose.repository.DatabaseRepository
import me.syahdilla.putra.sholeh.githubusercompose.repository.database.FavoriteDatabaseImpl
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class DatabaseFavoriteTest {

    private lateinit var db: DatabaseRepository

    private val testUsers by lazy {
        listOf(
            UserDetails(
                id = 1,
                name = "TestUser"
            ),
            UserDetails(
                id = 2,
                name = "TestUser2"
            ),
            UserDetails(
                id = 3,
                name = "TestUser3"
            ),
            UserDetails(
                id = 4,
                name = "TestUser4"
            )
        )
    }

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = DatabaseRepository(
            favoriteDatabase = FavoriteDatabaseImpl(
                context,
                isTestMode = true
            ),
            isTestMode = true
        )
    }

    @After
    fun releases() {
        db.favoriteDatabase.database.close()
    }

    @Test
    fun insertAndOther() = runTest {
        testUsers.forEach { db.favoriteInsert(it) }
        getAllTest()
        removeTest()
    }

    private fun getAllTest() {
        assertTrue { db.favoriteGetUsers().size >= 4 }
    }

    private suspend fun removeTest() {
        db.favoriteGetUsers().forEach { user ->
            assertTrue { db.favoriteRemove(user.id) == 1 }
        }
    }

}