package uoslife.springaccount.app.auth.dto.request

import jakarta.validation.constraints.NotEmpty
import org.hibernate.validator.constraints.Length
import org.springframework.format.annotation.NumberFormat

data class PhoneAuthVerifyRequest (
    @NotEmpty
    @Length(min = 11, max = 11)
    val phoneNumber:String,

    @NotEmpty
    val code:String
)