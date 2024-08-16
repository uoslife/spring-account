package uoslife.springaccount.app.auth.dto.request

import jakarta.validation.constraints.NotEmpty
import org.hibernate.validator.constraints.Length
import uoslife.springaccount.app.auth.dto.param.AuthParam

data class PhoneAuthInitiateRequest (
    @NotEmpty
    @Length(min = 11, max = 11)
    val phoneNumber:String
){
    companion object{
        fun toParam(data:PhoneAuthInitiateRequest): AuthParam.InitiatePhoneAuthSession{
            return AuthParam.InitiatePhoneAuthSession(phoneNumber = data.phoneNumber)
        }
    }

}