package uoslife.springaccount.app.auth.dto.request

import uoslife.springaccount.app.auth.dto.response.AuthResponse
import uoslife.springaccount.app.auth.util.TokenIssuedReason

data class TokensResponse(
    val reason: TokenIssuedReason,
    val accessToken: String,
    val refreshToken: String?
){

    fun toResponse():AuthResponse.IssuedTokensResponse{
        return AuthResponse.IssuedTokensResponse(
            reason = this.reason,
            accessToken = this.accessToken,
            refreshToken = this.refreshToken
        )
    }
}
