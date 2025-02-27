package dev.koga.vitamin.repository

import dev.koga.vitamin.error.NotFoundException
import dev.koga.vitamin.model.Fruit
import dev.koga.vitamin.model.Result
import dev.koga.vitamin.network.FruitApi
import kotlinx.coroutines.CoroutineScope

class FruitRepository(
    private val api: FruitApi,
) {
    suspend fun getAll(): Result<List<Fruit>> {
        return try {
            Result.Success(api.getAll())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun getByName(name: String): Result<Fruit> {
        return try {
            val response = api.getByName(name)
                ?: throw NotFoundException("Fruit with name '$name' not found")

            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}