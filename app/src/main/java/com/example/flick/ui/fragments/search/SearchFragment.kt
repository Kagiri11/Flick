package com.example.flick.ui.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.domain.models.Movie
import com.example.domain.models.state.UiState
import com.example.flick.R
import com.example.flick.databinding.FragmentSearchBinding
import com.example.flick.ui.adapters.SearchAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    val searchAdapter by lazy { SearchAdapter() }
    private val viewModel: SearchViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        searchAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("movie", it)
            }
            findNavController().navigate(
                R.id.action_searchFragment2_to_movieDetailsFragment,
                bundle
            )
        }

        var job: Job? = null
        binding.searchView.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                editable.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.searchMovies(editable.toString())
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchedMovies.collect {
                when (it) {
                    is UiState.Success -> {
                        searchAdapter.differ.submitList(it.data as List<Movie>)
                        binding.rvSearchedMovies.adapter = searchAdapter
                    }
                    is UiState.Error -> {}
                    is UiState.Loading -> {
                    }
                }
            }
        }

        return binding.root
    }
}
