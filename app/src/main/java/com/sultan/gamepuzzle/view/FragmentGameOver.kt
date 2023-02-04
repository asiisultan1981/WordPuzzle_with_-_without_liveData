package com.sultan.gamepuzzle.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.sultan.gamepuzzle.R
import com.sultan.gamepuzzle.databinding.FragmentGameOverBinding
import com.sultan.gamepuzzle.viewmodel.GameOverViewModel
import com.sultan.gamepuzzle.viewmodel.ScoreViewModelFactory

class FragmentGameOver : Fragment() {
    lateinit var binding: FragmentGameOverBinding

    lateinit var gameOverViewModel: GameOverViewModel

    lateinit var scoreViewModelFactory: ScoreViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_over, container, false)



        arguments?.let {
            val args = FragmentGameOverArgs.fromBundle(it)


            scoreViewModelFactory = ScoreViewModelFactory(args.score)

            gameOverViewModel =
                ViewModelProvider(this, scoreViewModelFactory).get(GameOverViewModel::class.java)

            gameOverViewModel.score.observe(viewLifecycleOwner, Observer {
                binding.textFinalScore.text = it.toString()

            })

        }

        binding.btnPlayAgain.setOnClickListener {
//            val action= FragmentGameOverDirections.actionFragmentGameOverToTitleFragment()
//            requireView().findNavController().navigate(action)
            Navigation.findNavController(it).navigate(R.id.action_fragmentGameOver_to_titleFragment)

        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This callback will only be called when MyFragment is at least Started.
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_fragmentGameOver_to_titleFragment)
        }

    }


}