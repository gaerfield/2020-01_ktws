package org.planet_express.domain

import org.hibernate.annotations.GenericGenerator
import java.io.Serializable
import java.util.*
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import javax.persistence.Version

@MappedSuperclass
open class StandardEntityId(
        @GeneratedValue(generator = "uuid2")
        @GenericGenerator(name = "uuid2", strategy = "uuid2")
        val id: String = UUID.randomUUID().toString()
) : Serializable {
    override fun equals(other: Any?) = when {
        this === other -> true
        javaClass != other?.javaClass -> false
        id != (other as StandardEntityId).id -> false
        else -> true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "${this::class.simpleName}=[$id]"
    }
}

@MappedSuperclass
internal abstract class BaseEntity<T : Serializable>(
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