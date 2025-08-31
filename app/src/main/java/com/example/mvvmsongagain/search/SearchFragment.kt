package com.example.mvvmsongagain.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmsongagain.adapter.SongsAdapter
import com.example.mvvmsongagain.databinding.FragmentSearchBinding
import com.example.mvvmsongagain.player.PlayerFragment

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.resultsRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.searchButton.setOnClickListener {
            val query = binding.searchView.query.trim().toString()
            if (query.isNotEmpty()) {
                viewModel.searchSong(query)
            }
        }
        viewModel.searchResults.observe(viewLifecycleOwner) { list ->
            binding.resultsRecycler.adapter = SongsAdapter(list)
            binding.resultsRecycler.visibility = View.VISIBLE
        }
        viewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }
    }

}