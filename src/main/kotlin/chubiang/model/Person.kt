package chubiang.model

import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
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
        val updatedate: LocalDateTime? = null,
        val role: String? = null,
        val enabled: String? = null
)
