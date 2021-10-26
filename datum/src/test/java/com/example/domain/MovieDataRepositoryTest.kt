package com.example.domain

import com.example.domain.models.Dates
import com.example.domain.models.MovieDto
import com.example.domain.models.MoviesResponse
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock

class MovieDataRepositoryTest {

    // SUT
    private lateinit var movieDataRepository: com.example.repository.sources.MovieDataRepository
    // Helpers
    private lateinit var moviesNetworkService: com.example.network.MovieNetworkService
    private var movieDto: MovieDto = MovieDto(
        adult = true,
        backdrop_path = "",
        genre_ids = listOf(),
        id = 8,
        original_language = "en",
        original_title = "mocked movie",
        overview = "",
        popularity = 0.0,
        poster_path = " ",
        release_date = " ",
        title = " ",
        video = false,
        vote_average = 0.2,
        vote_count = 142
    )
    private val dates = Dates("", "")
    private val moviesResponse: MoviesResponse = MoviesResponse(dates, 1, listOf(movieDto), 1, 1)

    @Before
    fun setup() {
        moviesNetworkService = mock()
        moviesNetworkService.stub {
            apply {
                onBlocking { this.fetchUpcomingMovies() }.doReturn(moviesResponse)
                onBlocking { this.fetchPopularMovies() }.doReturn(moviesResponse)
                onBlocking { this.fetchTopRatedMovies() }.doReturn(moviesResponse)
                onBlocking { this.fetchMovieDetails(8) }.doReturn(movieDto)
                onBlocking { this.fetchSimilarMovies(8) }.doReturn(moviesResponse)
                onBlocking { this.fetchNowPlayingMovies() }.doReturn(moviesResponse)
            }
        }
        movieDataRepository =
            com.example.repository.sources.MovieDataRepository(moviesNetworkService)
    }

    @Test
    fun `fetch upcoming movies should return a movies response`() {
        val taskResult = movieDataRepository.fetchUpcomingMovies()
        assertThat(taskResult).isEqualTo(moviesResponse)
    }

    @Test
    fun `fetch popular movies should return a movies response`() {
        val taskResult = movieDataRepository.fetchPopularMovies()
        assertThat(taskResult).isEqualTo(moviesResponse)
    }

    @Test
    fun `fetch top-rated movies should return a movies response`() {
        val taskResult = movieDataRepository.fetchTopRatedMovies()
        assertThat(taskResult).isEqualTo(moviesResponse)
    }

    @Test
    fun `fetching movie details  should return a movie dto`() {
        val taskResult = movieDataRepository.fetchMovieDetails(8)
        assertThat(taskResult).isEqualTo(movieDto)
    }

    @Test
    fun `fetching similar movies should return a movies response`() {
        val taskResult = movieDataRepository.fetchSimilarMovies(8)
        assertThat(taskResult).isEqualTo(moviesResponse)
    }

    @Test
    fun `fetching now playing movies should return a movies response`() {
        val taskResult = movieDataRepository.getNowPlaying()
        assertThat(taskResult).isEqualTo(moviesResponse)
    }
}
