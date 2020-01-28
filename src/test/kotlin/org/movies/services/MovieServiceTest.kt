package org.movies.services

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.movies.domain.Movie

internal class MovieServiceTest {

    @Test
    fun `assert mapping is succesful`() {

        val movie1 = mockk<Movie> {
            every { name } returns "Film1"
            every { score } returns 1.0
        }
        val movie2 = mockk<Movie> {
            every { name } returns "Film2"
            every { score } returns 2.0
        }
        val repoMock = mockk<MovieRepository> {
            every { findAll() } returns listOf(movie2, movie1)
        }

        val actual = MovieService(repoMock).getTopRated()
        assertAll(
            { assertEquals(MovieService.TitleAndScore("Film1", 1.0), actual[0]) },
            { assertEquals(MovieService.TitleAndScore("Film2", 2.0), actual[1]) }
        )
    }
}