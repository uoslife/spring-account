package uoslife.springaccount.app.verification.service

import org.slf4j.LoggerFactory
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import software.amazon.awssdk.services.ses.model.*
import uoslife.springaccount.app.user.domain.entity.User
import uoslife.springaccount.app.user.domain.repository.jpa.UserRepository
import uoslife.springaccount.app.verification.VerificationConfig
import uoslife.springaccount.app.verification.domain.entity.VerificationMethod
import uoslife.springaccount.app.verification.domain.entity.Verifications
import uoslife.springaccount.app.verification.domain.repository.jpa.VerificationRepository
import uoslife.springaccount.app.verification.dto.param.EmailParamDto
import uoslife.springaccount.common.configs.Realm
import uoslife.springaccount.intrastructure.externalservice.aws.AwsConfig
import uoslife.springaccount.intrastructure.externalservice.aws.service.SesService
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.time.LocalDateTime
import java.util.*


@Service
class EmailVerificationService (
    private val verificationRepository: VerificationRepository,
    private val userRepository: UserRepository,
    private val sesService: SesService,
){
    companion object {
        private val logger = LoggerFactory.getLogger(EmailVerificationService::class.java)
    }
    @Transactional
    fun createEmailVerification(
        data:EmailParamDto.CreateEmailVerification
    ) : EmailParamDto.EmailVerifySession {
        val realm = Realm.findByDomain(data.email.split("@")[1]) ?: throw Exception()

        val dummyUser = User("user","01090068420")
        println(dummyUser)
        val user = userRepository.save(dummyUser)
        println(user)
        val v = Verifications(
            user = dummyUser,
            method = VerificationMethod.EMAIL,
            code = generateRandomCode(6)
        )

        val verification = verificationRepository.save(v)

        logger.info("Generated ${data.email} - ${verification.code}")

        sendVerificationEmail(data.email, verification)
        logger.info("Sent email to ${data.email} - ${verification.code}")
        return EmailParamDto.EmailVerifySession(
            expiresAt = LocalDateTime.now().plusSeconds(VerificationConfig.EMAIL_VERIFY.SESSION_TTL),
            effectiveSeconds = VerificationConfig.EMAIL_VERIFY.SESSION_TTL
        )

    }

    fun sendVerificationEmail(
        email:String,
        verification : Verifications
    ){
        val resource = ClassPathResource("/static/email_templates/email_verification.mjml")

        val template = resource.inputStream.bufferedReader(StandardCharsets.UTF_8).use { it.readText() }

        // 템플릿에서 코드 치환
        val replacedTemplate = template.replace("{{code}}", verification.code)

        val sendRequest = SendEmailRequest.builder()
            .source(AwsConfig.SES.EMAIL.FROM)
            .replyToAddresses(AwsConfig.SES.EMAIL.REPLY_TO)
            .destination(
                Destination.builder()
                    .toAddresses(email)
                    .build()
            )
            .message(
                Message.builder()
                    .subject(Content.builder()
                        .data(AwsConfig.SES.EMAIL.TITLE)
                        .build()
                    )
                    .body(Body.builder()
                        .html(Content.builder()
                            .data(convertMjmlToHtml(replacedTemplate))
                            .build()
                        )
                        .build()
                    )
                    .build()
            )
            .build()


        println(sendRequest)
        sesService.getAssumedClient().sendEmail(sendRequest)
    }

    fun generateRandomCode(length: Int): String {
        val chars = ('A'..'Z') + ('0'..'9') // 대문자 영문자와 숫자의 조합
        return (1..length)
            .map { chars.random() }
            .joinToString("")
    }

    fun convertMjmlToHtml(mjmlContent: String): String? {
        val processBuilder = ProcessBuilder("C:\\\\Users\\\\dnwls\\\\AppData\\\\Roaming\\\\npm\\\\mjml.cmd", "-i")
        processBuilder.redirectErrorStream(true)
        val process = processBuilder.start()
        // Write MJML content to the process's input stream
        process.outputStream.write(mjmlContent.toByteArray())
        process.outputStream.close()

        // Read the HTML result from the process's output stream
        val reader = BufferedReader(InputStreamReader(process.inputStream))
        val htmlBuilder = StringBuilder()
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            htmlBuilder.append(line).append("\n")
        }
        process.waitFor()
        println(htmlBuilder.toString())
        return htmlBuilder.toString()
    }

    fun checkEmailVerification(
        checkEmailVerification: EmailParamDto.CheckEmailVerification
    ){

    }


}