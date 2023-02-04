package com.sultan.gamepuzzle.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ScoreViewModelFactory (private val finalScore:Int) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameOverViewModel::class.java)){
            return GameOverViewModel(finalScore) as T
        }

        throw IllegalArgumentException("wrong dependencies")
    }
}