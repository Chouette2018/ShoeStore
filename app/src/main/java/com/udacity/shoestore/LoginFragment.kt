package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding : FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login, container, false)
        setClickFunctions()
        return binding.root
    }

    private fun setClickFunctions(){
        binding.btnLogin.setOnClickListener {
            Utility.signIn(binding.email.editText?.text.toString(),
                binding.password.editText?.text.toString(),
                activity as MainActivity
            )
            findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
        }

        binding.btnSignup.setOnClickListener {
            Utility.createAccount(binding.email.editText?.text.toString(),
                binding.password.editText?.text.toString(),
                activity as MainActivity
            )
            findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
        }
    }
}