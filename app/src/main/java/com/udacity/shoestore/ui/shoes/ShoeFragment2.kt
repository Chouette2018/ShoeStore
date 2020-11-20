package com.udacity.shoestore.ui.shoes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MyBaseFragment
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoes2Binding
import com.udacity.shoestore.databinding.SimpleShoeItem2Binding
import com.udacity.shoestore.models.Shoe

class ShoesFragment2 : MyBaseFragment() {
    private val viewModel: ShoesViewModel by activityViewModels()
    private lateinit var binding : FragmentShoes2Binding
    private lateinit var inflater :LayoutInflater

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initMyFragments(getString(R.string.shoes_bar_title), false)

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentShoes2Binding>(inflater, R.layout.fragment_shoes2, container, false)
        this.inflater = inflater
        binding.apply{
            viewModel.shoes.observe(viewLifecycleOwner, Observer {
                    addShoesItem(it)
                }
            )
            lifecycleOwner = this@ShoesFragment2

            shoesFrag = this@ShoesFragment2

            return root
        }
    }

    private fun addShoesItem(shoes:List<Shoe>){
        for(shoe in shoes) {
            val bindingItem = DataBindingUtil.inflate<SimpleShoeItem2Binding>(
                inflater,
                R.layout.simple_shoe_item2,
                binding.llView,
                false
            )

            bindingItem.shoe = shoe
            bindingItem.executePendingBindings()
            binding.llView.addView(bindingItem.root)
        }
    }

    fun addShoes(){
        findNavController().navigate(R.id.action_shoesFragment_to_shoeDetailFragment)
    }
}