package org.planet_express.domain

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
        val countryCode: String,
        val planetCode: String,
        val starSystem: String,
        val galaxy: String
)

@Entity
internal class Parcel(
        val destination: InterstellarAddress
) : BaseEntity<ParcelTrackingId>(ParcelTrackingId())