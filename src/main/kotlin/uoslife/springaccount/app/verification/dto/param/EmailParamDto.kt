package uoslife.springaccount.app.verification.dto.param

import java.time.LocalDateTime
import java.util.Date

class EmailParamDto {
    data class CreateEmailVerification(
        val userId:Long,
        val email:String,
    )

    data class EmailVerifySession(
        val expiresAt:LocalDateTime,
        val effectiveSeconds:Long,
    )

    data class CheckEmailVerification(
        val userId:Long,
        val email :String,
        val code :String
    )
}