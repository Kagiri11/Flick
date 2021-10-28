package com.example.flick.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.domain.models.Movie
import com.example.domain.models.state.UiState
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

        prepareAdapters()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.apply {
                nowPlayingMovies.collect {
                    when (it) {
                        is UiState.Success -> initNowPlayingMovies(it.data)
                        is UiState.Loading -> println("")
                        is UiState.Error -> println("")
                    }
                }
                popularMovies.collect {
                    when (it) {
                        is UiState.Success -> initNowPlayingMovies(it.data)
                        is UiState.Loading -> println("")
                        is UiState.Error -> println("")
                    }
                }
                upcomingMovies.collect {
                    when (it) {
                        is UiState.Success -> initNowPlayingMovies(it.data)
                        is UiState.Loading -> println("")
                        is UiState.Error -> println("")
                    }
                }
            }
        }

        return binding.root
    }

    private fun prepareAdapters() {

        upcomingMoviesAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("movie", it)
            }
        }
        nowPlayingMoviesAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("movie", it)
            }
        }
    }

    private fun initNowPlayingMovies(nowPlayingMovies: List<Movie>) {
        nowPlayingMoviesAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("movie", it)
            }
        }
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
        upcomingMoviesAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("movie", it)
            }
        }
        upcomingMoviesAdapter.differ.submitList(upcomingMovies)
        binding.rvUpcomingMovies.adapter = upcomingMoviesAdapter
    }
}
