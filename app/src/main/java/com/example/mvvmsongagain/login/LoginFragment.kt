package com.example.mvvmsongagain.login


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mvvmsongagain.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        Pref.init(requireContext())

        loginViewModel.loadRememberMe()


        if (loginViewModel.savedRem.value == true) {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSearchFragment())
        }

        binding.loginButton.setOnClickListener {


            val email: String = binding.emailInput.text.toString().trim()
            val password: String = binding.passwordInput.text.toString().trim()

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

            if (!email.isEmpty() && !password.isEmpty()) {
                loginViewModel.LoginFirebase(email, password)
            }
        }


        loginViewModel.isLoading.observe(viewLifecycleOwner) { loading ->

            if (loading) {
                binding.progressBar.visibility = View.VISIBLE
                binding.blockerView.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
                binding.blockerView.visibility = View.GONE
            }

        }

        loginViewModel.isSuccessful.observe(viewLifecycleOwner) { success ->
            if (success == true) {
                val isChecked = binding.rememberMeCheckBox.isChecked
                loginViewModel.setRememberMe(isChecked)
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSearchFragment())
                Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(requireContext(), "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }

        binding.signupText.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
        }
    }


}