package org.planet_express.domain

import java.util.*
import javax.persistence.Embeddable
import javax.persistence.Entity

@Embeddable
class ParcelTrackingId(id: String = UUID.randomUUID().toString()) : StandardEntityId(id)

@Embeddable
data class Address(val street: String, val planet: String)

@Entity
internal class Parcel(
        val sender: Address,
        val destination: Address
) : BaseEntity<ParcelTrackingId>(ParcelTrackingId())