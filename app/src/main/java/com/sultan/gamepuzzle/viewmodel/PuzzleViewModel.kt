package com.sultan.gamepuzzle.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sultan.gamepuzzle.model.WordPuzzleData

class PuzzleViewModel: ViewModel() {
    var score = 0
    var _word : MutableLiveData<WordPuzzleData>? = null
    lateinit var _wordsList : MutableLiveData<ArrayList<WordPuzzleData>>
    private val TAG = "puzzleview"
}