package com.example.myapplication

object FileReader {

    fun readTestResFile(fileName: String): String {
        val inputStream = javaClass.classLoader?.getResourceAsStream(fileName)
        return inputStream?.bufferedReader()?.readText().orEmpty()
    }

}