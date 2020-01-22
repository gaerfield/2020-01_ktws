package org.planet_express.domain

import java.io.Serializable
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import javax.persistence.Version

@MappedSuperclass
abstract class BaseEntity<T : Serializable>(
        @Id
        val id: T,
        @Version
        val version: Long? = null
) {
    override fun equals(other: Any?) = when {
        this === other -> true
        javaClass != other?.javaClass -> false
        id != (other as BaseEntity<*>).id -> false
        else -> true
    }

    override fun hashCode(): Int = id.hashCode()
}