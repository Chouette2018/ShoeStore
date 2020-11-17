package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentInstructionsBinding

class InstructionsFragment : MyBaseFragment() {

    private lateinit var binding:FragmentInstructionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initMyFragments("Instructions", true)
        // Inflate the layout for this fragment
        binding = FragmentInstructionsBinding.inflate(inflater, container, false)
        setClickFunctions()
        return binding.root
    }

    private fun setClickFunctions(){
        binding.btnInstructions.setOnClickListener {
            findNavController().navigate(R.id.action_instructionsFragment_to_shoesFragment)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.sign_out).isVisible = false
        super.onPrepareOptionsMenu(menu)
    }
}