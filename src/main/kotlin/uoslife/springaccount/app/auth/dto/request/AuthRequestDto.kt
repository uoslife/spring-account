package uoslife.springaccount.app.auth.dto.request

import jakarta.validation.constraints.NotEmpty
import org.hibernate.validator.constraints.Length
import uoslife.springaccount.app.auth.dto.param.AuthParamDto

class AuthRequestDto {
    data class PhoneAuthInitiateRequest(
        @NotEmpty @Length(min = 11, max = 11) val phoneNumber: String
    ) {
        fun toParam(): AuthParamDto.InitiatePhoneAuthSession {
            return AuthParamDto.InitiatePhoneAuthSession(phoneNumber = this.phoneNumber)
        }
    }

    data class PhoneAuthVerifyRequest(
        @NotEmpty @Length(min = 11, max = 11) val phoneNumber: String,
        @NotEmpty val code: String
    ) {
        fun toParam(): AuthParamDto.VerifyPhoneAuthSession {
            return AuthParamDto.VerifyPhoneAuthSession(
                phoneNumber = this.phoneNumber,
                otpCode = this.code
            )
        }
    }

    data class RefreshTokensRequest(
        val refreshToken: String,
    ) {
        fun toParam(): AuthParamDto.RefreshTokensRequest {
            return AuthParamDto.RefreshTokensRequest(refreshToken = this.refreshToken)
        }
    }
}
