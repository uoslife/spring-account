package uoslife.springaccount.app.auth.controller

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import uoslife.springaccount.app.auth.dto.param.AuthParam
import uoslife.springaccount.app.auth.dto.request.*
import uoslife.springaccount.app.auth.dto.response.AuthResponse
import uoslife.springaccount.app.auth.service.AuthService
import uoslife.springaccount.common.error.auth.InvalidTokenException
import java.util.*

@RestController
@RequestMapping("/v2/auth")
class AuthController(
    private val authService: AuthService,
) {
    companion object {
        private val logger = LoggerFactory.getLogger(AuthController::class.java)
    }
    @PostMapping("/request")
    fun requestPhoneAuthentication(
        @RequestBody data: PhoneAuthInitiateRequest
    ): AuthResponse.PhoneAuthInitiateResponse {
        try{
            val result = authService.initiateOtpSession(PhoneAuthInitiateRequest.toParam(data))

            return AuthResponse.PhoneAuthInitiateResponse(result.expiresAt, result.effectiveSeconds)
        }catch (e:Exception){
            logger.error(e.message)
            throw Exception()
        }
    }

    @PostMapping("/verify")
    fun verifyPhoneAuthentication(
        @RequestBody data: PhoneAuthVerifyRequest,
    ): AuthResponse.IssuedTokensResponse {
        try{
            val result = authService.verifyPhoneAuthSession(AuthParam.VerifyPhoneAuthSession(
                phoneNumber = data.phoneNumber,
                otpCode = data.code
            ))
            return AuthResponse.IssuedTokensResponse(
                reason = result.reason,
                accessToken = result.accessToken,
                refreshToken = result.refreshToken
            )
        }catch (e:Exception){

            logger.error(e.message)
            throw Exception()
        }
    }

    @PostMapping("/refresh")
    fun refreshTokens(
        @RequestBody data : RefreshTokensRequest
    ) : AuthResponse.IssuedTokensResponse {
        try {
            val result = authService.refreshTokens(data.toParam())

            return result.toResponse()
        }catch (e : Exception){
            if (e is InvalidTokenException) throw InvalidTokenException()

            logger.error(e.message)
            throw Exception()
        }
    }
}