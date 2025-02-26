package dev.koga.vitamin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.koga.vitamin.model.Fruit
import dev.koga.vitamin.model.Result
import dev.koga.vitamin.model.asResult
import dev.koga.vitamin.network.FruitApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class FruitViewModel(
    private val api: FruitApi
) : ViewModel() {

    val fruitsStream = flow<List<Fruit>> { api.getFruits() }
        .asResult()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = Result.Loading
        )
}