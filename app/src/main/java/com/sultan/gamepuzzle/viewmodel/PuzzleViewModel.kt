package com.sultan.gamepuzzle.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sultan.gamepuzzle.model.WordPuzzleData


class PuzzleViewModel : ViewModel() {
    private var _score = MutableLiveData<Int>()
    val score: LiveData<Int>
    get() = _score

    private var _word = MutableLiveData<WordPuzzleData> ()
    val word: LiveData<WordPuzzleData>
    get() = _word

    private var _gameFinished = MutableLiveData<Boolean>()
    val gameFinished: LiveData<Boolean>
    get() = _gameFinished

    lateinit var wordsList: ArrayList<WordPuzzleData>
    private val TAG = "puzzleview"

    init {
        loadData()
        nextWord()
        _score.value = 0
        _gameFinished.value = false
    }

    fun loadData() {
        wordsList = arrayListOf(
            WordPuzzleData("BOT", "LE", "T"),
            WordPuzzleData("EX", "EPTION", "C"),
            WordPuzzleData("PROCA", "TINATION", "S"),
            WordPuzzleData("INF", "LTRATE", "I"),
            WordPuzzleData("REC", "NCILE", "O")
        )
        wordsList.shuffle()
    }

    fun nextWord() {
        if (wordsList.isEmpty()) {
            _gameFinished.value = true
        } else {
            _word.value = wordsList.removeAt(0)
        }
    }

    fun onGameOver(){
        _gameFinished.value = false
    }

    fun onWrongAnswer() {
       _score.value = _score.value?.minus(1)
        nextWord()
    }

    fun onRightAnswer() {
        _score.value = _score.value?.plus(1)
        nextWord()
    }

    fun onSkip() {
        _score.value = _score.value?.minus(1)
        nextWord()
    }

}