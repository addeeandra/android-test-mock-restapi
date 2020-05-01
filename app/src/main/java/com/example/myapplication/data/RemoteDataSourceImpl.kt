package com.example.myapplication.data

import org.json.JSONObject
import javax.net.ssl.HttpsURLConnection

class RemoteDataSourceImpl(
    private val mEndpoint: ApiEndpoint
) : RemoteDataSource {

    override fun register(
        name: String,
        username: String,
        password: String
    ): Pair<JSONObject?, Exception?> {
        val response = mEndpoint.register(name, username, password).execute()
        return Pair(response.body(), null) // true if registered, else error
    }

    override fun login(username: String, password: String): Pair<String?, Exception?> {
        val call = mEndpoint.login(username, password).execute()
        val response = call.body()

        if (call.code() == HttpsURLConnection.HTTP_OK) {
            return Pair(response?.string(), null) // true if logged in, else false
        }

        return Pair(null, Exception("Unauthorized"))
    }

}