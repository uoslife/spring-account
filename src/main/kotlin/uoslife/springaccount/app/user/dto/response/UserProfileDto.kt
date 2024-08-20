package uoslife.springaccount.app.user.dto.response

import uoslife.springaccount.app.verification.domain.entity.VerificationMethod

class UserProfileDto {

    data class UserRealm(
        val code: String,
        val name: String,
    )

    data class UserProfileIdentity(
        val id: String,
        val type: String,
        val status: String,
        val idNumber: String,
        val university: String?,
        val department: String?,
        val major: String?,
    )

    data class UserProfileModerator(
        val generation: String,
        val chapter: String,
        val role: String,
    )

    data class UserProfileResponse(
        val id: Long,
        val nickname: String,
        val phone: String,
        val name: String?,
        val email: String?,
        val realm: UserRealm?,
        val identity: UserProfileIdentity?,
        val moderator: UserProfileModerator?,
        val isLinkedPortal: Boolean,
        val isVerified: Boolean,
        val verificationMethod: VerificationMethod?,
    )
}
