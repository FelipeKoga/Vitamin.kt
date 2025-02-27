package dev.koga.vitamin.network

import dev.koga.vitamin.model.Fruit
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode

class FruitApi(
    private val httpClient: HttpClient
) {
    suspend fun getAll(): List<Fruit> {
        return httpClient.get("$BASE_URL/fruits").body<List<Fruit>>()
    }

    suspend fun getByName(name: String): Fruit? {
        val response = httpClient.get("$BASE_URL/fruits") {
            parameter("name", name)
        }

        if (response.status != HttpStatusCode.OK) {
            return null
        }

        return response.body<List<Fruit>>().firstOrNull()
    }

    companion object {
        private const val BASE_URL = "https://614c99f03c438c00179faa84.mockapi.io"
    }
}