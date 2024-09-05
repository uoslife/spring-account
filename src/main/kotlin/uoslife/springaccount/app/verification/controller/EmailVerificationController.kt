package uoslife.springaccount.app.verification.controller

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import uoslife.springaccount.app.verification.dto.param.EmailParamDto
import uoslife.springaccount.app.verification.dto.request.EmailRequestDto
import uoslife.springaccount.app.verification.dto.response.EmailResponseDto
import uoslife.springaccount.app.verification.service.EmailVerificationService

@RestController
@RequestMapping("/v2/verification/email")
class EmailVerificationController(
    private val emailVerificationService: EmailVerificationService
) {
    companion object {
        private val logger = LoggerFactory.getLogger(EmailVerificationController::class.java)
    }


    @PostMapping
    fun createEmailVerification(
        @RequestBody data: EmailRequestDto.EmailVerificationRequest
    ): EmailResponseDto.EmailAuthSentResponse {
        try{
            val result=emailVerificationService.createEmailVerification(data.toParam(1))

            return EmailResponseDto.EmailAuthSentResponse(
                result.effectiveSeconds,
                result.expiresAt
            )
        }
        catch (e:Exception){
            throw Exception()
        }
    }


    @GetMapping("/verify")
    fun verifyEmail(
        @RequestParam("email") email:String,
        @RequestParam("code") code:String
    ){
        try{
            emailVerificationService.checkEmailVerification(EmailParamDto.CheckEmailVerification(
                1L, email, code)
            )
        }catch (e : Exception){

        }

    }
}