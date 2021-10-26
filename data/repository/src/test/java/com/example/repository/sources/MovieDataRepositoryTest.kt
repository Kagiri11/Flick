package com.example.repository.sources

import com.example.domain.models.MoviesResponse
import com.example.network.models.DatesDto
import com.example.network.models.MovieDto
import com.example.network.models.MoviesResponseDto
import com.example.repository.mappers.toDomain
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub

class MovieDataRepositoryTest {

    // SUT
    private lateinit var movieDataRepository: MovieDataRepository
    // Helpers
    private lateinit var moviesNetworkService: com.example.network.MovieNetworkService
    private var movie: MovieDto = MovieDto(
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
    private val dates = DatesDto("", "")
    private val moviesResponse: MoviesResponse = MoviesResponse(1, listOf(movie.toDomain()))
    private val moviesResponseDto: MoviesResponseDto = MoviesResponseDto(dates = dates, page = 1, results = listOf(movie), 1, 1)

    @Before
    fun setup() {
        moviesNetworkService = mock()
        moviesNetworkService.stub {
            apply {
                onBlocking { this.fetchUpcomingMovies() }.doReturn(moviesResponseDto)
                onBlocking { this.fetchPopularMovies() }.doReturn(moviesResponseDto)
                onBlocking { this.fetchTopRatedMovies() }.doReturn(moviesResponseDto)
                onBlocking { this.fetchMovieDetails(8) }.doReturn(movie)
                onBlocking { this.fetchSimilarMovies(8) }.doReturn(moviesResponseDto)
                onBlocking { this.fetchNowPlayingMovies() }.doReturn(moviesResponseDto)
            }
        }
        movieDataRepository =
            MovieDataRepository(moviesNetworkService)
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
    fun `fetching movie details  should return a movie`() {
        val taskResult = movieDataRepository.fetchMovieDetails(8)
        assertThat(taskResult).isEqualTo(movie.toDomain())
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
