package io.github.philippeboisney.retrokotlin

import android.app.Activity
import io.github.philippeboisney.retrokotlin.base.BaseTest
import org.junit.Rule
import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.ViewModelProviders
import android.os.Build
import android.support.v4.app.FragmentActivity
import io.github.philippeboisney.retrokotlin.model.User
import io.github.philippeboisney.retrokotlin.viewmodel.UserViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.net.HttpURLConnection
import java.net.HttpURLConnection.HTTP_BAD_GATEWAY
import java.net.HttpURLConnection.HTTP_OK
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class UserUnitTest : BaseTest() {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule() // Force tests to be executed synchronously

    // FOR DATA
    private lateinit var activity: FragmentActivity
    private lateinit var viewModel: UserViewModel
    private val EXPECTED_USER = User("66577", "JakeWharton", "https://avatars0.githubusercontent.com/u/66577?v=4", "Jake Wharton", "Google, Inc.", "http://jakewharton.com")


    // OVERRIDING
    override fun isMockServerEnabled(): Boolean = true

    @Before
    override fun setUp(){
        super.setUp()
        this.activity = Robolectric.setupActivity(FragmentActivity::class.java)
        this.viewModel = ViewModelProviders.of(this.activity, viewModelFactory)[UserViewModel::class.java]
    }

    // TESTS
    @Test
    fun getUser_whenSuccess() {
        // Prepare data
        this.mockHttpResponse("getUser_whenSuccess.json", HttpURLConnection.HTTP_OK)
        // Pre-test
        assertEquals(null, this.viewModel.user.value, "User should be null because stream not started yet")
        // Execute View Model
        this.viewModel.getUser()
        // Checks
        assertEquals(EXPECTED_USER, this.viewModel.user.value, "User must be fetched")
        assertEquals(false, this.viewModel.isLoading.value, "Should be reset to 'false' because stream ended")
        assertEquals(null, this.viewModel.errorMessage.value, "No error must be founded")
    }

    @Test
    fun getUser_whenError(){
        // Prepare data
        this.mockHttpResponse("getUser_whenSuccess.json", HttpURLConnection.HTTP_BAD_GATEWAY)
        // Pre-test
        assertEquals(null, this.viewModel.user.value, "User should be null because stream not started yet")
        // Execute View Model
        this.viewModel.getUser()
        // Checks
        assertEquals(null, this.viewModel.user.value, "User must be null because of http error")
        assertEquals(false, this.viewModel.isLoading.value, "Should be reset to 'false' because stream ended")
        assertNotEquals(null, this.viewModel.errorMessage.value, "Error value must not be empty")
    }
}