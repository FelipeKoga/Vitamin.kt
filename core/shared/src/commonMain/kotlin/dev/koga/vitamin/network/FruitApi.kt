package dev.koga.vitamin.network

import dev.koga.vitamin.model.Fruit
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class FruitApi(
    private val httpClient: HttpClient
) {
    suspend fun getFruits(): List<Fruit> {
        return httpClient.get(
            urlString = "https://614c99f03c438c00179faa84.mockapi.io/fruits"
        ).body<List<Fruit>>()
    }
}