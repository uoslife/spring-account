package uoslife.springaccount.app.auth.dto.param

import java.time.LocalDateTime
import uoslife.springaccount.app.auth.util.TokenIssuedReason

class AuthParamDto {
    data class InitiatePhoneAuthSession(val phoneNumber: String)
    data class PhoneAuthSession(val expiresAt: LocalDateTime, val effectiveSeconds: Long)

    data class VerifyPhoneAuthSession(val phoneNumber: String, val otpCode: String)

    data class RefreshTokensRequest(val refreshToken: String)

    data class TokensResponse(
        val reason: TokenIssuedReason,
        val accessToken: String,
        val refreshToken: String?
    )
}
