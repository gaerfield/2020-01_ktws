package org.planet_express.services

import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals

internal class ShipmentServiceTest {

    @Test
    fun parseAddress() {
        val addressee = "Zaphod Beeblebrox"
        val street = "Πρόκειται"
        val number = 7
        val planet = "Betelgeuse"
        val system = "Orions Belt"
        val galaxy = "Milky Way"

        val rawAddress = """
            $addressee
            $street $number
            
            $planet
            $system
            $galaxy
        """.trimIndent()
        with(ShipmentService(mockk<ParcelRepository>()).parseAddress(rawAddress)) {
            assertAll(
                {assertEquals(addressee, this.addressee)},
                {assertEquals(street, this.street)},
                {assertEquals(number, this.number)},
                {assertEquals(planet, this.planet)},
                {assertEquals(system, this.starSystem)},
                {assertEquals(galaxy, this.galaxy)}
            )
        }

    }
}