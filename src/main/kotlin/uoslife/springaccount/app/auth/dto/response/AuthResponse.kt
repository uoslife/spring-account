package uoslife.springaccount.app.auth.dto.response

import uoslife.springaccount.app.auth.util.TokenIssuedReason
import java.time.LocalDateTime

class AuthResponse {
    data class PhoneAuthInitiateResponse(
        val expiresAt: LocalDateTime,
        val effectiveSeconds: Long
    )

    data class IssuedTokensResponse(
        val reason: TokenIssuedReason,
        val accessToken:String,
        val refreshToken:String?
    )
}