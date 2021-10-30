package com.example.flick.ui.fragments.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.domain.models.Cast
import com.example.domain.models.Movie
import com.example.domain.models.reviews.Review
import com.example.domain.models.state.UiState
import com.example.flick.databinding.FragmentMovieDetailsBinding
import com.example.flick.ui.adapters.CastAdapter
import com.example.flick.ui.adapters.MovieReviewAdapter
import com.example.flick.ui.adapters.SimilarMoviesAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsFragment : Fragment() {
    private val args: MovieDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentMovieDetailsBinding

    private val movieReviewAdapter = MovieReviewAdapter()
    private val similarMoviesAdapter = SimilarMoviesAdapter()
    private val movieCastAdapter = CastAdapter()
    private val viewModel: MovieDetailsViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailsBinding.inflate(layoutInflater)

        val movie by lazy {
            args.movie
        }

        with(movie.id) {
            viewModel.apply {
                getMovieCast(this@with)
                    .getMovieDetails(this@with)
                    .getMovieReviews(this@with)
                    .getSimilarMovies(this@with)
            }
        }

        initUI()
        return binding.root
    }

    private fun initUI() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.movieDetails.collect {
                        when (it) {
                            is UiState.Success -> {
                                val configuration = "https://image.tmdb.org/t/p/w500"
                                val mov = it.data as Movie
                                val movieImage = configuration + mov.posterPath
                                Glide.with(requireContext()).load(movieImage)
                                    .into(binding.ivSpecificMovieImage)
                                binding.tvMovieSynopsis.text = mov.overview.take(200)
                                binding.tvSpecificMovieRating.text = mov.voteAverage.toString()
                                binding.tvSpecificMovieName.text = mov.title
                                binding.tvSpecificMovieYear.text = mov.releaseDate
                            }
                            is UiState.Loading -> {
                            }
                            is UiState.Error -> {
                            }
                        }
                    }
                }
                launch {
                    viewModel.movieCast.collect {
                        when (it) {
                            is UiState.Error -> {
                            }
                            is UiState.Loading -> {
                            }
                            is UiState.Success -> initCasts(it.data as List<Cast>)
                        }
                    }
                }
                launch {
                    viewModel.movieReviews.collect {
                        when (it) {
                            is UiState.Success -> initReviews(it.data as List<Review>)
                            is UiState.Loading -> {
                            }
                            is UiState.Error -> {
                            }
                        }
                    }
                }
                launch {
                    viewModel.similarMovies.collect {
                        when (it) {
                            is UiState.Error -> {
                            }
                            is UiState.Loading -> {
                            }
                            is UiState.Success -> initSimilarMovies(it.data as List<Movie>)
                        }
                    }
                }
            }
        }
    }

    private fun initCasts(casts: List<Cast>) {
        movieCastAdapter.differ.submitList(casts)
        binding.rvMovieCast.adapter = movieCastAdapter
    }

    private fun initReviews(reviews: List<Review>) {
        movieReviewAdapter.differ.submitList(
            reviews.filter {
                it.author_details.avatar_path != null
            }
        )
        binding.rvReviews.adapter = movieReviewAdapter
        if (reviews.isEmpty()) {
            binding.tvReviews.visibility = GONE
        }
    }

    private fun initSimilarMovies(similarMovies: List<Movie>) {
        similarMoviesAdapter.differ.submitList(similarMovies)
        binding.rvSimilarMovies.adapter = similarMoviesAdapter
    }
}
