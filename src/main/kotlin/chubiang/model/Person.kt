package chubiang.model

import java.time.LocalDateTime

data class Person (
        val personno: Int? = null,
        val username: String? = null,
        val personage: Short? = null,
        val email: String? = null,
        val password: String? = null,
        val personimg: String? = null,
        val petfamilyno: Int? = null,
        val etc: String? = null,
        val makedate: LocalDateTime? = null,
        val updatedate: LocalDateTime? = null
)
