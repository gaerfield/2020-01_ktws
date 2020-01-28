package org.movies.domain

import org.springframework.format.annotation.DateTimeFormat
import java.util.*
import javax.persistence.Embeddable
import javax.persistence.Entity


@Embeddable
class MovieId(id: String? = null) : BaseEntityId(id)

@Entity
internal class Movie (
        val budget : Double,
        val company : String,
        val country : String,
        val director : String,
        val genre : String,
        val gross : Double,
        val name : String,
        val rating : String,
//        @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
//        val released : Date,
        val released : String,
        val runtimeInMin : Int,
        val score : Double,
        val star : String,
        val votes : Int,
        val writer : String,
        val year : Int
) : BaseEntity<MovieId>(MovieId())