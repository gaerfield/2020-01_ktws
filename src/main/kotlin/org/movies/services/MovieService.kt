package org.movies.services

import org.movies.AppConfig
import org.movies.domain.Movie
import org.movies.domain.MovieId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

internal interface MovieRepository : JpaRepository<Movie, MovieId> {

    //fun findTop10OrderByRating()
}

@RestController
internal class MovieService(
    val movieRepository: MovieRepository,
    val appConfig: AppConfig
) {

    data class TitleAndScore(val title: String, val score: Double)
    @GetMapping("/least10")
    fun getTopRated() = movieRepository.findAll()
            .sortedBy { it.score }
            .take(10)
            .map { TitleAndScore(it.name, it.score) }

    @GetMapping("/config")
    fun getApplicationConfig() = appConfig

    @PutMapping("/config")
    fun updateApplicationConfig(@RequestParam filterAdultRating: Boolean) { appConfig.filterAdultRating = filterAdultRating }

}