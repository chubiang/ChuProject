package chubiang.model

import org.springframework.stereotype.Component

@Component
data class Role (
    var user_role_id: Int? = null,
    var email: String? = null,
    var role: String? = null
)