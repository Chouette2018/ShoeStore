package com.udacity.shoestore.ui.shoes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.size
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoesBinding
import com.udacity.shoestore.databinding.SimpleShoeItemBinding
import com.udacity.shoestore.models.Shoe

/**
 * A simple [Fragment] subclass.
 * Use the [ShoesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoesFragment : Fragment() {
    private val viewModel: ShoesViewModel by activityViewModels()
    private lateinit var binding : FragmentShoesBinding
    private lateinit var linearLayout : LinearLayout
    private lateinit var inflater: LayoutInflater
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentShoesBinding>(inflater, R.layout.fragment_shoes, container, false)
        this.inflater = inflater
        linearLayout = binding.llView
        viewModel.shoes.observe(viewLifecycleOwner, Observer {
                addMissingShoe(it)
            }
        )
        setClickFunctions()
        return binding.root
    }

    private fun setClickFunctions(){
        binding.btnAddShoe.setOnClickListener {
            findNavController().navigate(R.id.action_shoesFragment_to_shoeDetailFragment)
        }
    }

    private fun addMissingShoe(shoes:List<Shoe>){
        if(shoes.size > linearLayout.size) {
            val newView : View = createGenericShoeView(shoes.last())
            linearLayout.addView(newView,
                //-1,
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ))
        }
    }

    private fun createGenericShoeView(shoe: Shoe): View {
        //val view = layoutInflater.inflate(R.layout.simple_shoe_item, null)

        val binding = DataBindingUtil.inflate<SimpleShoeItemBinding>(inflater, R.layout.simple_shoe_item,linearLayout, false)
        binding.shoeName.text = shoe.name
        binding.companyName.text = shoe.company
        binding.sizeValue.text = shoe.size.toString()
        binding.executePendingBindings()
        return binding.root
    }
}