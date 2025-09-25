package com.example.mvvmsongagain.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mvvmsongagain.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private lateinit var binding : FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.backArrow.setOnClickListener {
            Log.d("SettingsFragment", "Back button clicked")
            findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToSearchFragment())
        }

    }


}