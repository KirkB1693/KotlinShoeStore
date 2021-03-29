package com.example.shoestore.shoeDetail

import android.graphics.Color
import android.os.Bundle
import android.text.Layout
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AlignmentSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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
    ): View {

        // Inflate view and obtain an instance of the binding class.
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_detail,
            container,
            false
        )

        binding.lifecycleOwner = this

        binding.shoeListViewModel = viewModel

        binding.newShoe = Shoe()

        binding.cancelButton.setOnClickListener {
            findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }

        binding.saveButton.setOnClickListener {
            addShoeToListIfValid()
        }

        return binding.root
    }

    private fun addShoeToListIfValid() {

        if (binding.newShoe?.shoeName?.isNotEmpty() == true) {
            if (binding.newShoe?.shoeCompany?.isNotEmpty() == true) {
                if (binding.newShoe?.shoeSize?.isNotEmpty() == true) {
                    if (binding.newShoe?.shoeDescription?.isNotEmpty() == true) {
                        viewModel.addShoeToList(binding.newShoe!!)
                        findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
                    } else {
                        var formattedText =
                            SpannableString("Please enter a shoe description before saving!")
                        formattedText.setSpan(
                            AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),
                            0,
                            formattedText.length - 1,
                            Spannable.SPAN_INCLUSIVE_INCLUSIVE
                        )
                        formattedText.setSpan(
                            ForegroundColorSpan(Color.RED),
                            0,
                            formattedText.length,
                            Spannable.SPAN_INCLUSIVE_INCLUSIVE
                        )
                        Toast.makeText(
                            context,
                            formattedText,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    var formattedText = SpannableString("Please enter a shoe size before saving!")
                    formattedText.setSpan(
                        AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),
                        0,
                        formattedText.length - 1,
                        Spannable.SPAN_INCLUSIVE_INCLUSIVE
                    )
                    formattedText.setSpan(
                        ForegroundColorSpan(Color.RED),
                        0,
                        formattedText.length,
                        Spannable.SPAN_INCLUSIVE_INCLUSIVE
                    )
                    Toast.makeText(
                        context,
                        formattedText,
                        Toast.LENGTH_LONG
                    ).show()

                }
            } else {
                var formattedText = SpannableString("Please enter a shoe company before saving!")
                formattedText.setSpan(
                    AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),
                    0,
                    formattedText.length - 1,
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE
                )
                formattedText.setSpan(
                    ForegroundColorSpan(Color.RED),
                    0,
                    formattedText.length,
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE
                )
                Toast.makeText(
                    context,
                    formattedText,
                    Toast.LENGTH_LONG
                ).show()

            }
        } else {
            var formattedText = SpannableString("Please enter a shoe name before saving!")
            formattedText.setSpan(
                AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),
                0,
                formattedText.length - 1,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
            formattedText.setSpan(
                ForegroundColorSpan(Color.RED),
                0,
                formattedText.length,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
            Toast.makeText(
                context,
                formattedText,
                Toast.LENGTH_LONG
            ).show()

        }
    }
}