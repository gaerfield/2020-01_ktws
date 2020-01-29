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


    data class PersonAndHisMovies(val name: String) {
        val director = mutableSetOf<String>()
        val writer = mutableSetOf<String>()
        val star = mutableSetOf<String>()

        override fun toString() = """
            name: $name
              directed: ${director.sortedBy { it }}
              writer:   ${writer.sortedBy { it }}
              star:     ${star.sortedBy { it }}
        """.trimIndent()

    }

    val persons = mutableMapOf<String, PersonAndHisMovies>()

    @GetMapping("/multiTalents")
    fun getStuff(): List<PersonAndHisMovies> {
        movieRepository.findAll().forEach {movie ->
            persons.getOrPut(movie.director, {PersonAndHisMovies(movie.director)}).director.add(movie.name)
            persons.getOrPut(movie.writer, {PersonAndHisMovies(movie.writer)}).writer.add(movie.name)
            persons.getOrPut(movie.star, {PersonAndHisMovies(movie.star)}).star.add(movie.name)
        }
        return persons.values.filter {
            var cnt = 3
            if (it.director.isEmpty()) --cnt
            if (it.star.isEmpty()) --cnt
            if (it.writer.isEmpty()) --cnt
            cnt >1
        }.sortedByDescending { it.director.size + it.star.size + it.writer.size }
    }

}