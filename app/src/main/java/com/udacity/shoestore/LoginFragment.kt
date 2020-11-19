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
        binding.loginFrag = this
        return binding.root
    }

    fun onLogIn() {
        binding.apply {
            Utility.signIn(
                email.editText?.text.toString(),
                password.editText?.text.toString(),
                activity as MainActivity
            )
            findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
        }
    }

    fun onSignUp() {
        binding.apply {
            Utility.createAccount(
                email.editText?.text.toString(),
                password.editText?.text.toString(),
                activity as MainActivity
            )
            findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
        }
    }
}