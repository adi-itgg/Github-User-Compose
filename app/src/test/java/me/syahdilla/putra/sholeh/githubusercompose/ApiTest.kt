@file:OptIn(ExperimentalCoroutinesApi::class)

package me.syahdilla.putra.sholeh.githubusercompose


import io.ktor.client.call.*
import io.ktor.client.plugins.resources.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import me.syahdilla.putra.sholeh.githubusercompose.data.User
import me.syahdilla.putra.sholeh.githubusercompose.repository.ApiRepository
import me.syahdilla.putra.sholeh.githubusercompose.utils.createDefaultHttpClient
import org.junit.After
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.Test
import kotlin.test.assertFails
import kotlin.test.assertTrue

/**
 * Api local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ApiTest: KoinTest {

    private val api: ApiRepository by inject()

    private val testModules by lazy {
        listOf(
            module {
                single { true }
                single { createDefaultHttpClient(isTestMode = true) }
                singleOf(::ApiRepository)
            }
        )
    }

    @Before
    fun setUp() {
        startKoin { modules(testModules) }
    }

    @After
    fun releases() {
        unloadKoinModules(testModules)
        stopKoin()
    }

    @Test
    fun apiSearchTest() = runTest {
        val result = api.searchUser(query = "jake") {
            assertFails(message = it) {}
        }
        assertTrue { result.isNotEmpty() }
    }

    @Test
    fun apiGetUserDetailsTest() = runTest {
        val testUser = User(name = "Test", url = "https://api.github.com/users/JakeWharton")
        api.getUserDetails(user = testUser).onFailure {
            throw it
        }.onSuccess {
            assertTrue { it.id != -1L }
        }
    }

}