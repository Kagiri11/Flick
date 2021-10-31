package com.example.flick.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.domain.models.Movie
import com.example.domain.models.state.UiState
import com.example.flick.R
import com.example.flick.databinding.FragmentMoviesBinding
import com.example.flick.ui.adapters.NowPlayingMoviesAdapter
import com.example.flick.ui.adapters.PopularMoviesAdapter
import com.example.flick.ui.adapters.UpcomingMoviesAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    private val popularMoviesAdapter = PopularMoviesAdapter()
    private val upcomingMoviesAdapter = UpcomingMoviesAdapter()
    private val nowPlayingMoviesAdapter = NowPlayingMoviesAdapter()
    private val viewModel: MoviesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(layoutInflater)

        nowPlayingMoviesAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("movie", it)
            }
            findNavController().navigate(R.id.action_moviesFragment_to_movieDetailsFragment, bundle)
        }
        upcomingMoviesAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("movie", it)
            }
            findNavController().navigate(R.id.action_moviesFragment_to_movieDetailsFragment, bundle)
        }
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.popularMovies.collect {
                        when (it) {
                            is UiState.Success -> initPopularMovies(it.data as List<Movie>)
                            is UiState.Loading -> print("")
                            is UiState.Error -> {
                                binding.popularProgressBar.visibility = VISIBLE
                                binding.tvPopError.text = it.error.toString()
                            }
                        }
                    }
                }

                launch {
                    viewModel.nowPlayingMovies.collect {
                        when (it) {
                            is UiState.Success -> initNowPlayingMovies(it.data as List<Movie>)
                            is UiState.Loading -> println("")
                            is UiState.Error -> println("")
                        }
                    }
                }

                launch {
                    viewModel.upcomingMovies.collect {
                        when (it) {
                            is UiState.Success -> initUpcomingMovies(it.data as List<Movie>)
                            is UiState.Loading -> println("")
                            is UiState.Error -> println("")
                        }
                    }
                }
            }
        }

        return binding.root
    }

    private fun initNowPlayingMovies(nowPlayingMovies: List<Movie>) {

        nowPlayingMoviesAdapter.differ.submitList(nowPlayingMovies)
        binding.rvNowPlayingMovies.adapter = nowPlayingMoviesAdapter
    }

    private fun initPopularMovies(popularMovies: List<Movie>) {
        popularMoviesAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("movie", it)
            }
        }
        popularMoviesAdapter.differ.submitList(popularMovies)
        binding.rvPopularMovies.adapter = popularMoviesAdapter
    }

    private fun initUpcomingMovies(upcomingMovies: List<Movie>) {

        upcomingMoviesAdapter.differ.submitList(upcomingMovies)
        binding.rvUpcomingMovies.adapter = upcomingMoviesAdapter
    }
}
