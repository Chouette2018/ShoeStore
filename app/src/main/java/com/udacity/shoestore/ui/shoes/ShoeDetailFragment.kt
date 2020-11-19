package com.udacity.shoestore.ui.shoes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.MyBaseFragment
import com.udacity.shoestore.R
import com.udacity.shoestore.Utility
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment : MyBaseFragment() {
    private val viewModel: ShoesViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initMyFragments(getString(R.string.new_shoes_bar_title),true)

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentShoeDetailBinding>(inflater, R.layout.fragment_shoe_detail, container, false)

        binding.shoeDetailFrag = this

        return binding.root
    }

    fun onSave(){
        viewModel.addShoe(getShoeInfo())
        findNavController().popBackStack()
    }

    fun onCancel(){
        findNavController().popBackStack()
    }

    private fun getShoeInfo():Shoe{
        binding.apply {
            val name = edShoeName.text.toString()
            val size = edSize.text.toString()
            val company = edCompanyName.text.toString()
            val description = edDescription.text.toString()
            return Shoe(name, size.toDouble(), company, description)
        }
    }
}