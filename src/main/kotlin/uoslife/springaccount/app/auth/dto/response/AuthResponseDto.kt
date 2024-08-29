package uoslife.springaccount.app.auth.dto.response

import java.time.LocalDateTime
import uoslife.springaccount.app.auth.dto.param.AuthParamDto
import uoslife.springaccount.app.auth.util.TokenIssuedReason

class AuthResponseDto {
    data class PhoneAuthInitiateResponse(val expiresAt: LocalDateTime, val effectiveSeconds: Long) {
        companion object {
            fun toResponse(
                phoneAuthSession: AuthParamDto.PhoneAuthSession
            ): PhoneAuthInitiateResponse {
                return PhoneAuthInitiateResponse(
                    expiresAt = phoneAuthSession.expiresAt,
                    effectiveSeconds = phoneAuthSession.effectiveSeconds
                )
            }
        }
    }

    data class IssuedTokensResponse(
        val reason: TokenIssuedReason,
        val accessToken: String,
        val refreshToken: String?
    ) {
        companion object {
            fun toResponse(tokensResponse: AuthParamDto.TokensResponse): IssuedTokensResponse {
                return IssuedTokensResponse(
                    reason = tokensResponse.reason,
                    accessToken = tokensResponse.accessToken,
                    refreshToken = tokensResponse.refreshToken
                )
            }
        }
    }
}
