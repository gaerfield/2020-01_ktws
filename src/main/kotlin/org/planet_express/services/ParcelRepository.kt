package org.planet_express.services

import org.planet_express.domain.Parcel
import org.planet_express.domain.ParcelTrackingId
import org.springframework.data.jpa.repository.JpaRepository

internal interface ParcelRepository : JpaRepository<Parcel, ParcelTrackingId>