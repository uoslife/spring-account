package uoslife.springaccount.app.auth.service

import java.time.LocalDateTime
import java.util.concurrent.TimeUnit
import kotlin.random.Random
import org.redisson.api.RBucket
import org.redisson.api.RedissonClient
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import uoslife.springaccount.app.auth.dto.param.AuthParamDto
import uoslife.springaccount.app.auth.util.AuthConfig
import uoslife.springaccount.app.auth.util.JwtTypes
import uoslife.springaccount.app.auth.util.TokenIssuedReason
import uoslife.springaccount.app.moderator.domain.entity.Moderators
import uoslife.springaccount.common.error.auth.InvalidTokenException
import uoslife.springaccount.common.error.auth.OtpNotFoundException
import uoslife.springaccount.intrastructure.externalservice.slack.SlackService
import uoslife.springaccount.intrastructure.externalservice.sms.service.SmsService
import uoslife.springaccount.intrastructure.utils.MaskUtil

@Service
class AuthService (
    private val redisClient: RedissonClient,
    private val jwtService: JwtService,
    private val slackService: SlackService,
    private val smsService:SmsService
) {
    companion object {
        private val logger = LoggerFactory.getLogger(AuthService::class.java)
        private val maskUtil = MaskUtil()
    }

    fun initiateOtpSession(
        data: AuthParamDto.InitiatePhoneAuthSession
    ): AuthParamDto.PhoneAuthSession {
        val isMasterPhone = isMasterPhone(data.phoneNumber)

        val phoneNumber =
            if (isMasterPhone) getOriginalPhone(data.phoneNumber) else data.phoneNumber

        val moderator = checkModerator(phoneNumber)
        val isModerator = moderator != null

        val code = generateCode(phoneNumber, isModerator)

        if (isModerator) {
            sendSlackMessage(code, moderator?.slackId!!)
            logger.info(
                "[SMS OTP] Delivered ${maskUtil.maskPhoneNumber(phoneNumber)} - $code - Slack Moderator"
            )
        } else {
            sendMessage(code, phoneNumber)
            logger.info("[SMS OTP] Delivered ${maskUtil.maskPhoneNumber(phoneNumber)} - $code")
        }

        val effectiveSeconds = AuthConfig.PHONE_AUTH_SESSION_TTL
        val expiresAt = LocalDateTime.now().plusSeconds(effectiveSeconds)

        val bucket: RBucket<String> = redisClient.getBucket(getSessionCache(phoneNumber))
        bucket.set(code, effectiveSeconds, TimeUnit.SECONDS)

        return AuthParamDto.PhoneAuthSession(
            effectiveSeconds = effectiveSeconds,
            expiresAt = expiresAt
        )
    }

    private fun sendSlackMessage(code: String, channel: String): Boolean {
        slackService.sendMessage(
            text = "시대생 인증번호는 `${code}` 입니다.\n@uoslife.com #${code}",
            channel=channel
        )
        return true
    }

    private fun getSessionCache(phoneNumber: String): String {
        return listOf("uoslife", "auth", "sms", "code", phoneNumber).joinToString(":")
    }

    private fun sendMessage(code: String, phoneNumber: String): Boolean {
        return smsService.sendMessage(
            phoneNumber,
            "시대생 인증번호",
            "시대생 인증번호는 [${code}] 입니다.\n@uoslife.com #${code}",
        )
    }

    private fun checkModerator(phoneNumber: String): Moderators? {
        try {
            throw Exception()
        } catch (e: Exception) {
            return null
        }
        TODO("userService 도입 후")
    }

    private fun getOriginalPhone(phoneNumber: String): String {
        return "010" + phoneNumber.substring(3)
    }

    private fun isMasterPhone(phoneNumber: String): Boolean {
        return phoneNumber.startsWith("020")
    }

    fun generateCode(phoneNumber: String, isModerator: Boolean): String {
        val isEligibleForStaticCode = isMasterPhone(phoneNumber) && isModerator

        val code =
            if (isEligibleForStaticCode) "00" + phoneNumber.substring(7)
            else Random.nextInt(100000, 1000000).toString()

        logger.info("[SMS OTP] Generated ${maskUtil.maskPhoneNumber(phoneNumber)} - $code")

        return code
    }

    fun verifyPhoneAuthSession(
        data: AuthParamDto.VerifyPhoneAuthSession
    ): AuthParamDto.TokensResponse {
        val isMasterPhone = isMasterPhone(data.phoneNumber)

        val phoneNumber =
            if (isMasterPhone) getOriginalPhone(data.phoneNumber) else data.phoneNumber
        logger.info(
            "[SMS OTP] Challenged ${maskUtil.maskPhoneNumber(phoneNumber)} - ${data.otpCode}"
        )

        val bucket: RBucket<String> = redisClient.getBucket(getSessionCache(phoneNumber))
        val code = bucket.get()

        if (code == null) {
            logger.info("[SMS OTP] No Session ${maskUtil.maskPhoneNumber(phoneNumber)}")
            throw OtpNotFoundException()
        }
        if (code != data.otpCode) {
            logger.info("[SMS OTP] Mismatch ${maskUtil.maskPhoneNumber(phoneNumber)}")
            throw OtpNotFoundException()
        }

        logger.info("[SMS OTP] Succeed ${maskUtil.maskPhoneNumber(phoneNumber)}")

        try {
            throw Exception()
        } catch (e: Exception) {
            if (e is Exception) {} // 유저 에러 생성 후 추가
            val token = jwtService.generateToken(JwtTypes.REGISTER, phoneNumber)

            return AuthParamDto.TokensResponse(
                reason = TokenIssuedReason.REGISTERING,
                accessToken = token,
                refreshToken = null
            )
        } finally {
            bucket.delete()
        }
    }

    fun refreshTokens(data: AuthParamDto.RefreshTokensRequest): AuthParamDto.TokensResponse {
        val payload = jwtService.validToken(data.refreshToken)

        if (payload.aud != JwtTypes.REFRESH.typeName) throw InvalidTokenException()

        val (accessToken, refreshToken) = issueTokensForUser(payload.sub)

        return AuthParamDto.TokensResponse(
            reason = TokenIssuedReason.REFRESHED,
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    private fun issueTokensForUser(userId: String): Pair<String, String> {
        return Pair(
            jwtService.generateToken(JwtTypes.ACCESS, userId),
            jwtService.generateToken(JwtTypes.REFRESH, userId)
        )
    }
}
