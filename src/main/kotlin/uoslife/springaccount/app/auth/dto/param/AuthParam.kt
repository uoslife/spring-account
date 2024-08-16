package uoslife.springaccount.app.auth.dto.param

import java.time.LocalDateTime

class AuthParam {
    data class InitiatePhoneAuthSession(
        val phoneNumber: String
    )
    data class PhoneAuthSession(
        val expiresAt:LocalDateTime,
        val effectiveSeconds:Long
    )

    data class VerifyPhoneAuthSession(
        val phoneNumber: String,
        val otpCode: String
    )

    data class RefreshTokensRequest(
        val refreshToken:String
    )
}