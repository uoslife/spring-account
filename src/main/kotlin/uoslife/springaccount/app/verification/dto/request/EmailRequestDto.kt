package uoslife.springaccount.app.verification.dto.request

import jakarta.validation.constraints.Email
import uoslife.springaccount.app.verification.dto.param.EmailParamDto

class EmailRequestDto {
    data class EmailVerificationRequest(
        @Email
        val email:String
    ){
        fun toParam(userId : Long):EmailParamDto.CreateEmailVerification{
            return EmailParamDto.CreateEmailVerification(
                userId = userId,
                email = this.email
            )
        }
    }

}