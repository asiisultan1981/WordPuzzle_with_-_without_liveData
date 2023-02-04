package com.sultan.gamepuzzle.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameOverViewModel(finalScore:Int): ViewModel() {
    private var _score = MutableLiveData<Int>()

    val score : LiveData<Int>
    get() = _score

    init {
        _score.value = finalScore
    }
}