package uoslife.springaccount.app.auth.controller

import java.util.*
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import uoslife.springaccount.app.auth.dto.request.*
import uoslife.springaccount.app.auth.dto.response.AuthResponseDto
import uoslife.springaccount.app.auth.service.AuthService

@RestController
@RequestMapping("/v2/auth")
class AuthController(
    private val authService: AuthService,
) {
    @PostMapping("/request")
    fun requestPhoneAuthentication(
        @RequestBody data: AuthRequestDto.PhoneAuthInitiateRequest
    ): AuthResponseDto.PhoneAuthInitiateResponse {
        val result = authService.initiateOtpSession(data.toParam())

        return AuthResponseDto.PhoneAuthInitiateResponse.toResponse(result)
    }

    @PostMapping("/verify")
    fun verifyPhoneAuthentication(
        @RequestBody data: AuthRequestDto.PhoneAuthVerifyRequest,
    ): AuthResponseDto.IssuedTokensResponse {
        val result = authService.verifyPhoneAuthSession(data.toParam())

        return AuthResponseDto.IssuedTokensResponse.toResponse(result)
    }

    @PostMapping("/refresh")
    fun refreshTokens(
        @RequestBody data: AuthRequestDto.RefreshTokensRequest
    ): AuthResponseDto.IssuedTokensResponse {
        val result = authService.refreshTokens(data.toParam())

        return AuthResponseDto.IssuedTokensResponse.toResponse(result)
    }
}
