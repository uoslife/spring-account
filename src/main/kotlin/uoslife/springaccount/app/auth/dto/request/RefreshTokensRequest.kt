package uoslife.springaccount.app.auth.dto.request

import uoslife.springaccount.app.auth.dto.param.AuthParam

data class RefreshTokensRequest(
    val refreshToken:String,
)

fun RefreshTokensRequest.toParam():AuthParam.RefreshTokensRequest{
    return AuthParam.RefreshTokensRequest(
        refreshToken = this.refreshToken
    )
}