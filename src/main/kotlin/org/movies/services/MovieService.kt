package org.movies.services

import org.movies.domain.Movie
import org.movies.domain.MovieId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

internal interface MovieRepository : JpaRepository<Movie, MovieId> {

    //fun findTop10OrderByRating()
}

@RestController
internal class MovieService(
    val movieRepository: MovieRepository
) {

    data class TitleAndScore(val title: String, val score: Double)
    @GetMapping("/top10")
    fun getTopRated() = movieRepository.findAll()
            .sortedBy { it.score }
            .take(10)
            .map { TitleAndScore(it.name, it.score) }

    // gruppieren nach rating (0-1, 1-2, 1-3, usw.) und Anzahl anzeigen
    // welches Studio hatte die meisten Produktionen
    // welcher Regisseur hatte meisten Produktionen
    // welcher Star hatte in welchen Filmen mitgespielt

}