package chubiang.model

import org.springframework.stereotype.Component
import java.time.LocalDateTime
import javax.persistence.Column

@Component
data class Person (
        @Column(name = "personno")
        var personno: Int? = null,
        @Column(name = "username")
        var username: String? = null,
        @Column(name = "personage")
        var personage: Short? = null,
        @Column(name = "email")
        var email: String? = null,
        @Column(name = "password")
        var password: String? = null,
        @Column(name = "personimg")
        var personimg: String? = null,
        @Column(name = "petfamilyno")
        var petfamilyno: Int? = null,
        @Column(name = "etc")
        var etc: String? = null,
        @Column(name = "makedate")
        var makedate: LocalDateTime? = null,
        @Column(name = "updatedate")
        var updatedate: LocalDateTime? = null,
        @Column(name = "role")
        var role: String? = null,
        @Column(name = "isenabled")
        var enabled: String? = null
)