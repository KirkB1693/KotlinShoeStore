package com.example.shoestore.shoeList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentShoeListBinding
import com.example.shoestore.databinding.ShoeListItemBinding

class ShoeListFragment : Fragment() {

    private val viewModel: ShoeListViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate view and obtain an instance of the binding class.
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false
        )

        // Set the viewmodel for databinding - this allows the bound layout access to all of the
        // data in the VieWModel
        binding.shoeListViewModel = viewModel

        // Specify the current activity as the lifecycle owner of the binding. This is used so that
        // the binding can observe LiveData updates
        binding.lifecycleOwner = this

        // Sets up event listening to display the shoes in the list when the list changes
        viewModel.shoeListForDisplay.observe(viewLifecycleOwner, { shoeList ->
            if (shoeList.isNotEmpty()) {
                // Iterate through list and display shoes
                    var oddItem = true
                for (shoe in shoeList) {
                    displayShoe(oddItem, shoe)
                    oddItem = !oddItem
                }
            }
        })

        binding.addShoeFAB.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

        return binding.root
    }

    private fun displayShoe(oddItem: Boolean, shoe: Shoe) {
        val itemBinding: ShoeListItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.shoe_list_item, binding.shoeListLinearLayout,false)

        itemBinding.itemShoeNameText.text = shoe.shoeName
        itemBinding.itemShoeCompanyText.text = shoe.shoeCompany
        itemBinding.itemShoeSizeText.text = shoe.shoeSize
        itemBinding.itemShoeDescriptionText.text = shoe.shoeDescription

        if (oddItem) {
            itemBinding.root.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.light_grey_background))
        } else {
            itemBinding.root.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.dark_grey_background))
        }

        binding.shoeListLinearLayout.addView(itemBinding.root)
    }
}