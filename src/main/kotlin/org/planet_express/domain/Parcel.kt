package org.planet_express.domain

import java.time.Instant
import java.util.*
import javax.persistence.Embeddable
import javax.persistence.Entity

@Embeddable
class ParcelTrackingId(id: String? = null) : BaseEntityId(id)

@Embeddable
data class InterstellarAddress(
        val addressee : String,
        val street: String,
        val number: Int,
        val country: String,
        val planet: String,
        val starSystem: String,
        val galaxy: String
)

@Embeddable
data class Weight(val weightInKilo: Double)
val Number.kilos get() = Weight(this.toDouble())
val Number.grams get() = Weight(this.toDouble()*0.001)
val Number.tons get() = Weight(this.toDouble()*1000)

enum class Size { XS, S, M, L, XL, XXL }

@Entity
internal class Parcel(
        val destination: InterstellarAddress,
        val size: Size = Size.M,
        val weight: Weight = 5.kilos,
        val dipatched: Instant = Instant.now(),
        val delivered: Instant? = null
) : BaseEntity<ParcelTrackingId>(ParcelTrackingId())

ID
VERSION
DELIVERED
ADDRESSEE
COUNTRY
GALAXY
NUMBER
PLANET
STAR_SYSTEM
STREET
