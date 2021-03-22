package com.example.shoestore.instructions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentInstructionsBinding

class InstructionsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate view and obtain an instance of the binding class.
        val binding: FragmentInstructionsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_instructions,
            container,
            false
        )

        return binding.root
    }


}