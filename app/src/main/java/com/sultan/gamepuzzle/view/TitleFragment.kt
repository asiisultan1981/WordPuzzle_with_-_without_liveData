package com.sultan.gamepuzzle.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.sultan.gamepuzzle.R
import com.sultan.gamepuzzle.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {
    lateinit var binding: FragmentTitleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_title, container, false)

        binding.btnPlay.setOnClickListener {

            requireView().findNavController()
                .navigate(TitleFragmentDirections.actionTitleFragmentToFragmentPuzzle())
        }
        return binding.root
    }


}