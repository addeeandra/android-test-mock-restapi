package com.example.myapplication.data

import org.json.JSONObject

interface RemoteDataSource {

    fun register(name: String, username: String, password: String): Pair<JSONObject?, Exception?>

    fun login(username: String, password: String): Pair<String?, Exception?>

}