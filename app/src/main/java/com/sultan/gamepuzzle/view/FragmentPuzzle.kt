package com.sultan.gamepuzzle.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.sultan.gamepuzzle.R
import com.sultan.gamepuzzle.databinding.FragmentPuzzleBinding
import com.sultan.gamepuzzle.model.WordPuzzleData
import java.util.*
import kotlin.collections.ArrayList

class FragmentPuzzle : Fragment() {

    lateinit var binding: FragmentPuzzleBinding
    var score = 0
    var word : WordPuzzleData? = null
    lateinit var wordsList : ArrayList<WordPuzzleData>
    private val TAG = "puzzle"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_puzzle, container,false)
        loadData()
        nextWord()
        binding.btnOK.setOnClickListener { checkAnswer() }
        binding.btnSkip.setOnClickListener { onSkip() }

        return binding.root

    }

    private fun updateScore(){
        binding.textScore.text = score.toString()
    }

    private fun onSkip() {
        score--
        nextWord()
    }

    private fun checkAnswer() {
        if(binding.textAnsGapPuzzle.text.toString().uppercase(Locale.ROOT) == word?.correctAnswer){
            binding.textAnsGapPuzzle.text  = null
            score++
            nextWord()
        }
        else{
            binding.textAnsGapPuzzle.text  = null
            score--
            nextWord()
        }
    }

    private fun nextWord() {
        if (wordsList.isEmpty()){
            gameOver()
        }else{
           word = wordsList.removeAt(0)
            Log.e(TAG, "nextWord: $word" )
        }
        updateWord()
        updateScore()
        binding.invalidateAll()
    }

    private fun updateWord() {
        binding.textAnswerBox1.text=word?.question_gap_1
        binding.textAnswerBox2.text = word?.question_gap_2
    }

    private fun gameOver() {
        requireView().findNavController().navigate(
        FragmentPuzzleDirections.actionFragmentPuzzleToFragmentGameOver(score))

    }

    private fun loadData() {
        wordsList = arrayListOf(
            WordPuzzleData("BOT","LE", "T"),
            WordPuzzleData("EX","EPTION","C"),
            WordPuzzleData("PROCA","TINATION","S"),
            WordPuzzleData("INF","LTRATE","I"),
            WordPuzzleData("REC","NCILE","O")
        )
        for (i in wordsList){

            Log.d(TAG, "loadData: $i")
        }

        wordsList.shuffle()

    }


}