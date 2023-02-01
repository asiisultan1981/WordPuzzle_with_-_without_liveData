package com.sultan.gamepuzzle.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.sultan.gamepuzzle.R
import com.sultan.gamepuzzle.databinding.FragmentGameOverBinding

class FragmentGameOver : Fragment() {
    lateinit var binding: FragmentGameOverBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_over, container, false)
        arguments?.let {
            val args = FragmentGameOverArgs.fromBundle(it)
            binding.textFinalScore.text = args.score.toString()
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
            Navigation.findNavController(requireView()).navigate(R.id.action_fragmentGameOver_to_titleFragment)
        }

    }




}