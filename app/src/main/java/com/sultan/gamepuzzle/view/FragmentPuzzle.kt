package com.sultan.gamepuzzle.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.sultan.gamepuzzle.R
import com.sultan.gamepuzzle.databinding.FragmentPuzzleBinding
import com.sultan.gamepuzzle.viewmodel.PuzzleViewModel
import java.util.*


class FragmentPuzzle : Fragment() {

    lateinit var binding: FragmentPuzzleBinding
    private val TAG = "puzzle"
    lateinit var puzzleViewModel: PuzzleViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_puzzle, container, false)

        puzzleViewModel = ViewModelProvider(this)[PuzzleViewModel::class.java]

        binding.lifecycleOwner = this

        puzzleViewModel.word.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding.textAnswerBox1.text = puzzleViewModel.word.value?.question_gap_1
            binding.textAnswerBox2.text = puzzleViewModel.word.value?.question_gap_2
        })

        puzzleViewModel.score.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding.textScore.text = it?.toString()
        })

        puzzleViewModel.gameFinished.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it) {
                gameOver()

            }
        })

        binding.btnOK.setOnClickListener {
            checkAnswer()

        }
        binding.btnSkip.setOnClickListener {
            puzzleViewModel.onSkip()
        }
        return binding.root
    }

    private fun checkAnswer() {
        if (binding.textAnsGapPuzzle.text.toString()
                .uppercase(Locale.ROOT) == puzzleViewModel.word.value?.correctAnswer
        ) {
            binding.textAnsGapPuzzle.text = null
            puzzleViewModel.onRightAnswer()
        } else {
            binding.textAnsGapPuzzle.text = null
            puzzleViewModel.onWrongAnswer()
        }
    }

    private fun gameOver() {
        val action =
            FragmentPuzzleDirections.actionFragmentPuzzleToFragmentGameOver(
                puzzleViewModel.score.value ?: 0
            )
        requireView().findNavController().navigate(action)
        puzzleViewModel.onGameOver()
        Toast.makeText(activity, "GameOver", Toast.LENGTH_LONG).show()
        Log.e(TAG, "gameOver called from gameOver Function")

    }


}