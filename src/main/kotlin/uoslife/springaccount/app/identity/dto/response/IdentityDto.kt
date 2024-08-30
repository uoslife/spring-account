package uoslife.springaccount.app.identity.dto.response

import uoslife.springaccount.app.identity.domain.entity.Identity

class IdentityDto {
    data class IdentityReponse (
        val type: String,
        val status: String,
        val idNumber: String,
        val university: String? = null,
        val department: String? = null,
        val major: String? = null, // null 가능성 고려
        val isActive: Boolean
    )

    companion object {
        fun toResponse(identity : Identity) : IdentityReponse{
            return IdentityReponse(
                type = identity.type,
                status = identity.status,
                idNumber = identity.idNumber,
                university = identity.university,
                department = identity.department,
                major = identity.major,
                isActive = identity.isActive,
                )
        }
    }
}