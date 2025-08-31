package com.example.mvvmsongagain.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mvvmsongagain.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private val loginViewModel: LoginViewModel by viewModels() // Reusing your existing ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.backArrow.setOnClickListener {
            findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToLoginFragment())

        }

        binding.loginButton.setOnClickListener {
            val username: String = binding.nameInput.text.toString().trim()
            val email: String = binding.emailInput.text.toString().trim()
            val password: String = binding.passwordInput.text.toString().trim()

            if (username.isEmpty()) {
                binding.nameErrorText.visibility = View.VISIBLE
            } else {
                binding.nameErrorText.visibility = View.INVISIBLE
            }

            if (email.isEmpty()) {
                binding.emailErrorText.visibility = View.VISIBLE
            } else {
                binding.emailErrorText.visibility = View.INVISIBLE
            }

            if (password.isEmpty()) {
                binding.passwordErrorText.visibility = View.VISIBLE
            } else {
                binding.passwordErrorText.visibility = View.INVISIBLE
            }


            if (!password.isEmpty() && !email.isEmpty() && !username.isEmpty()) {
                loginViewModel.SignupFirebase(username, email, password)
                findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToSearchFragment())
                Toast.makeText(requireContext() , "Account successfully created" , Toast.LENGTH_LONG).show()
            }


        }

    }


}