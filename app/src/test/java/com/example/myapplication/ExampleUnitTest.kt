package com.example.myapplication

import com.example.myapplication.data.ApiEndpoint
import com.example.myapplication.data.RemoteDataSource
import com.example.myapplication.data.RemoteDataSourceImpl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.json.JSONObject
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.HttpsURLConnection


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private lateinit var mMockWebServer: MockWebServer
    private lateinit var mApiEndpoint: ApiEndpoint
    private lateinit var mRemoteMockDataSource: RemoteDataSource

    @Before
    fun setUp() {
        mMockWebServer = MockWebServer()
        mApiEndpoint = Retrofit
            .Builder()
            .baseUrl(mMockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiEndpoint::class.java)
        mRemoteMockDataSource = RemoteDataSourceImpl(mApiEndpoint)
    }

    @After
    fun down() {
        mMockWebServer.shutdown()
    }

    @Test
    fun `test call api login success`() {
        enqueueRequest(HttpsURLConnection.HTTP_OK, true, "api_login.json")

        val result = mRemoteMockDataSource.login("khoiron", "ntab")

        assertThat(result.first, `is`(notNullValue()))
        assertThat(result.second, `is`(nullValue()))

//        val response = JSONObject(result.first!!)
//        assertThat(response.getJSONObject("data"), `is`(notNullValue()))
//
//        val data = response.getJSONObject("data")
//        assertThat(data.getString("user_token"), `is`("opoopoopo1234"))
    }

    @Test
    fun `test call api login failed`() {
        enqueueRequest(HttpsURLConnection.HTTP_UNAUTHORIZED)

        val result = mRemoteMockDataSource.login("khoiron", "ntab")

        assertThat(result.first, `is`(nullValue()))
        assertThat(result.second, `is`(notNullValue()))

//        val response = JSONObject(result.first!!)
//        assertThat(response.getJSONObject("data"), `is`(notNullValue()))
//
//        val data = response.getJSONObject("data")
//        assertThat(data.getString("user_token"), `is`("opoopoopo1234"))
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    private fun enqueueRequest(
        responseCode: Int,
        shouldGiveResponse: Boolean = false,
        fileName: String = ""
    ) {
        val response = MockResponse().setResponseCode(responseCode)
        if (shouldGiveResponse) response.setBody(FileReader.readTestResFile(fileName))
        mMockWebServer.enqueue(response)
    }
}
