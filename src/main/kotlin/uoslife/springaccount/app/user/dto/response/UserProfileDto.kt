package uoslife.springaccount.app.user.dto.response

import uoslife.springaccount.app.user.domain.entity.User
import uoslife.springaccount.app.verification.domain.entity.VerificationMethod
import uoslife.springaccount.common.error.ErrorCode
import uoslife.springaccount.common.error.baseexception.BusinessException

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
        //        val realm: UserRealm?,
        val identity: UserProfileIdentity?,
        val moderator: UserProfileModerator?,
        val isLinkedPortal: Boolean,
        val isVerified: Boolean,
        val verificationMethod: VerificationMethod?,
    )

    companion object {
        fun toUserProfileResponse(user: User): UserProfileResponse {
            return UserProfileResponse(
                id = user.id ?: throw BusinessException(ErrorCode.USER_ID_NULL),
                nickname = user.nickname ?: "No Nickname",
                phone = user.phoneNumber,
                name = user.name,
                email = user.email,
                identity =
                    user.identities.firstOrNull()?.let { identity ->
                        UserProfileIdentity(
                            id = identity.id,
                            type = identity.type,
                            status = identity.status,
                            idNumber = identity.idNumber,
                            university = identity.university,
                            department = identity.department,
                            major = identity.major
                        )
                    },
                moderator =
                    user.moderators.firstOrNull()?.let {
                        UserProfileModerator(
                            generation = it.generation,
                            chapter = it.chapter,
                            role = it.role
                        )
                    },
                isLinkedPortal = user.portalAccounts.isNotEmpty(),
                isVerified = user.verifications.isNotEmpty(),
                verificationMethod = user.verifications.firstOrNull()?.method
            )
        }
    }
}
