package com.example.shoestore.shoeDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentShoeDetailBinding
import com.example.shoestore.shoeList.Shoe
import com.example.shoestore.shoeList.ShoeListViewModel


class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding

    private val viewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate view and obtain an instance of the binding class.
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_detail,
            container,
            false
        )

        binding.shoeListViewModel = viewModel

        binding.cancelButton.setOnClickListener {
            findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }

        binding.saveButton.setOnClickListener {
            addShoeToListIfValid()
        }

        return binding.root
    }

    private fun addShoeToListIfValid() {
        val shoeName = binding.shoeNameEditText.text.toString()
        val shoeCompany = binding.shoeCompanyEditText.text.toString()
        val shoeSize = binding.shoeSizeEditText.text.toString()
        val shoeDescription = binding.shoeDescriptionEditText.text.toString()

        if (shoeName.isNotEmpty()) {
            if (shoeCompany.isNotEmpty()) {
                if (shoeSize.isNotEmpty()) {
                    if (shoeDescription.isNotEmpty()) {
                        val newShoe = Shoe(shoeName, shoeCompany, shoeSize.toInt(), shoeDescription)
                        viewModel.addShoeToList(newShoe)
                        findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
                    } else {
                        Toast.makeText(
                            context,
                            "Please enter a shoe description before saving!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        context,
                        "Please enter a shoe size before saving!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                Toast.makeText(
                    context,
                    "Please enter a shoe company before saving!",
                    Toast.LENGTH_LONG
                ).show()
            }
        } else {
            Toast.makeText(context, "Please enter a shoe name before saving!", Toast.LENGTH_LONG)
                .show()
        }
    }
}