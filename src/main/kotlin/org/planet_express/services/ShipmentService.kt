package org.planet_express.services

import org.planet_express.domain.InterstellarAddress
import org.planet_express.domain.Parcel
import org.planet_express.domain.ParcelTrackingId
import org.springframework.web.bind.annotation.*
import java.lang.IllegalArgumentException

@RestController
internal class ShipmentService(
        val parcelRepository: ParcelRepository
) {
    companion object {
        private val addressRegex = """(?<addressee>.*)\n(?<street>.*)\n.*\n.*\n.*\n.*""".toRegex()
    }


    @PostMapping("/shipment-orders")
    fun dispatch(@RequestBody destination: String) = parcelRepository.save(
            Parcel(parseAddress(destination))
    ).id

    fun parseAddress(address: String) : InterstellarAddress {
        val match = addressRegex.matchEntire(address) ?: throw IllegalArgumentException("invalidAddress")

        return InterstellarAddress(
                addressee = match.groups["addressee"]?.value ?: throw IllegalArgumentException("No addressee given"),
                street = match.groups["street"]?.value ?: "",
                number = 12,
                country = "",
                planet = "",
                starSystem = "",
                galaxy = ""
        )
    }

    @GetMapping("/shipment-orders/{trackingId}")
    fun track(@PathVariable trackingId: ParcelTrackingId) = parcelRepository.findById(trackingId)
}