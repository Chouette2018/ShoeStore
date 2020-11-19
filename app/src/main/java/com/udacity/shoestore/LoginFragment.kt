package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : MyBaseFragment() {
    private lateinit var binding : FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initMyFragments(getString(R.string.log_in_bar_title), true)
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login, container, false)
        setClickFunctions()
        return binding.root
    }

    private fun setClickFunctions(){
        binding.apply {
            val email = email.editText?.text.toString()
            val password = password.editText?.text.toString()
            btnLogin.setOnClickListener {
                Utility.signIn(
                    email,
                    password,
                    activity as MainActivity
                )
                findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
            }

            btnSignup.setOnClickListener {
                Utility.createAccount(
                    email,
                    password,
                    activity as MainActivity
                )
                findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
            }
        }
    }
}