package org.planet_express.services

import org.planet_express.domain.InterstellarAddress
import org.planet_express.domain.Parcel
import org.planet_express.domain.ParcelTrackingId
import org.springframework.web.bind.annotation.*

@RestController
internal class ShipmentService(
        val parcelRepository: ParcelRepository
) {
    // https://spring.io/guides/tutorials/spring-boot-kotlin/

    @PostMapping("/shipment-orders")
    fun dispatch(@RequestBody destination: String) = parcelRepository.save(
            Parcel(parseAddress(destination))
    ).id

    private fun parseAddress(address: String) : InterstellarAddress {
        return InterstellarAddress(
                addressee = "little Prince",
                street = "",
                number = 12,
                countryCode = "",
                planetCode = "",
                starSystem = "",
                galaxy = "Alpha Centauri"
        )
    }

    @GetMapping("/shipment-orders/{trackingId}")
    fun track(@PathVariable trackingId: ParcelTrackingId) = parcelRepository.findById(trackingId)
}