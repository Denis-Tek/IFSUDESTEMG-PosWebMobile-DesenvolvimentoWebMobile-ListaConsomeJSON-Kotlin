package com.fotos

import android.os.AsyncTask
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class HttpService() : AsyncTask<Void?, Void?, List<Foto>>() {

    override fun doInBackground(vararg params: Void?): List<Foto> {

        val resposta = StringBuilder()
        try {
            val url = URL("https://jsonplaceholder.typicode.com/photos?_page=1&_limit=10")
            val httpClient = url.openConnection() as HttpURLConnection
            httpClient.requestMethod = "GET"
            httpClient.setRequestProperty("Content-type", "application/json");
            httpClient.setRequestProperty("Accept", "application/json");
            if (httpClient.responseCode == HttpURLConnection.HTTP_OK) {
                try {
                    val stream = BufferedInputStream(httpClient.inputStream)
                    val bufferedReader = BufferedReader(InputStreamReader(stream))
                    bufferedReader.forEachLine { resposta.append(it) }
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    httpClient.disconnect()
                }
            } else {
                println("ERROR ${httpClient.responseCode}")
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val sType = object : TypeToken<List<Foto>>() { }.type
        return Gson().fromJson<List<Foto>>(resposta.toString(), sType)
    }
}
