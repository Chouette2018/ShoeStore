package com.udacity.shoestore.ui.shoes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MyBaseFragment
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding

class ShoeDetailFragment : MyBaseFragment() {
    private val shoesViewModel: ShoesViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initMyFragments(getString(R.string.new_shoes_bar_title),true)

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentShoeDetailBinding>(inflater, R.layout.fragment_shoe_detail, container, false)

        binding.apply {
            shoeDetailFrag = this@ShoeDetailFragment
            shoesViewModel.resetValues()
            viewModel = shoesViewModel
            lifecycleOwner = viewLifecycleOwner

            return root
        }
    }

    fun onSave(){
        shoesViewModel.addShoe()
        findNavController().popBackStack()
    }

    fun onCancel(){
        findNavController().popBackStack()
    }
}