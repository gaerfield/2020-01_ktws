package org.movies

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("movies")
class AppConfig(
        var filterAdultRating : Boolean = false,
        val evenEnumsArePossible : Option = Option.ONE,
        val complexConfig: ComplexConfig = ComplexConfig()
) {
    enum class Option { ONE, TWO }
    data class ComplexConfig( val id: String = "", val token: String = "" )
}