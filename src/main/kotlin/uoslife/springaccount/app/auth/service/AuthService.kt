package uoslife.springaccount.app.auth.service

import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import uoslife.springaccount.app.auth.dto.param.AuthParam
import uoslife.springaccount.app.auth.dto.request.TokensResponse
import uoslife.springaccount.app.auth.util.TokenIssuedReason
import uoslife.springaccount.app.auth.util.AuthConfig
import uoslife.springaccount.app.auth.util.JwtTypes
import uoslife.springaccount.app.moderator.domain.entity.Moderators
import uoslife.springaccount.common.error.auth.InvalidTokenException
import uoslife.springaccount.common.error.auth.OtpNotFoundException
import uoslife.springaccount.intrastructure.utils.MaskUtil
import kotlin.random.Random
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

@Service
class AuthService (
    private val redisTemplate: RedisTemplate<String, Any>,
    private val jwtService: JwtService,
){
    companion object {
        private val logger = LoggerFactory.getLogger(AuthService::class.java)
        private val maskUtil = MaskUtil()
    }

    fun initiateOtpSession(data : AuthParam.InitiatePhoneAuthSession) : AuthParam.PhoneAuthSession {
        val isMasterPhone=isMasterPhone(data.phoneNumber)

        val phoneNumber = if(isMasterPhone) getOriginalPhone(data.phoneNumber) else data.phoneNumber

        val moderator = checkModerator(phoneNumber)
        val isModerator = moderator != null

        val code = generateCode(phoneNumber, isModerator)

        if(isModerator){
            logger.info("[SMS OTP] Delivered ${maskUtil.maskPhoneNumber(phoneNumber)} - $code - Slack Moderator")
        }
        else{
            sendMessage(code, phoneNumber)
            logger.info("[SMS OTP] Delivered ${maskUtil.maskPhoneNumber(phoneNumber)} - $code")
        }

        val effectiveSeconds = AuthConfig.PHONE_AUTH_SESSION_TTL
        val expiresAt = LocalDateTime.now().plusSeconds(effectiveSeconds)

        val ops = redisTemplate.opsForValue()
        ops.set(getSessionCache(phoneNumber), code, effectiveSeconds, TimeUnit.SECONDS) //Key, Value, TTL

        return AuthParam.PhoneAuthSession(
            effectiveSeconds=effectiveSeconds,
            expiresAt = expiresAt
        )
    }

    private fun getSessionCache(phoneNumber: String): String {
        return listOf("uoslife", "auth", "sms", "code", phoneNumber).joinToString(":")

    }

    private fun sendMessage(code: String, phoneNumber: String) :Boolean {
        return true
        TODO("Not yet implemented")
    }

    private fun checkModerator(phoneNumber: String): Moderators? {
        return null
        TODO("userService 도입 후")
    }

    private fun getOriginalPhone(phoneNumber: String): String {
        return "010"+phoneNumber.substring(3)
    }

    private fun isMasterPhone(phoneNumber: String): Boolean {
        return phoneNumber.startsWith("020")
    }

    fun generateCode(phoneNumber: String, isModerator:Boolean):String{
        val isEligibleForStaticCode = isMasterPhone(phoneNumber) && isModerator

        val code = if(isEligibleForStaticCode) "00"+phoneNumber.substring(7)
                        else Random.nextInt(100000,1000000).toString()

        logger.info("[SMS OTP] Generated ${maskUtil.maskPhoneNumber(phoneNumber)} - $code")

        return code
    }

    fun verifyPhoneAuthSession(
        data: AuthParam.VerifyPhoneAuthSession
    ): TokensResponse {
        val isMasterPhone=isMasterPhone(data.phoneNumber)

        val phoneNumber = if(isMasterPhone) getOriginalPhone(data.phoneNumber) else data.phoneNumber
        logger.info("[SMS OTP] Challenged ${maskUtil.maskPhoneNumber(phoneNumber)} - ${data.otpCode}")

        val ops = redisTemplate.opsForValue()
        val code = ops.get(getSessionCache(phoneNumber))

        if(code==null){
            logger.info("[SMS OTP] No Session ${maskUtil.maskPhoneNumber(phoneNumber)}")
            throw OtpNotFoundException()
        }
        if(code != data.otpCode){
            logger.info("[SMS OTP] Mismatch ${maskUtil.maskPhoneNumber(phoneNumber)}")
            throw OtpNotFoundException()
        }

        logger.info("[SMS OTP] Succeed ${maskUtil.maskPhoneNumber(phoneNumber)}")

        try{
            TODO("User find 로직 추가")

            throw Exception()

        }catch (e:Exception){
            if (e is Exception){} //유저 에러 생성 후 추가
            val token = jwtService.generateToken(JwtTypes.REGISTER,phoneNumber)

            return TokensResponse(
                reason = TokenIssuedReason.REGISTERING,
                accessToken = token,
                refreshToken = null
            )
        }
        finally {
            ops.getAndDelete(getSessionCache(phoneNumber))
        }
    }

    fun refreshTokens(data: AuthParam.RefreshTokensRequest): TokensResponse {
        val payload = jwtService.validToken(data.refreshToken)

        if (payload.aud != JwtTypes.REFRESH.typeName) throw InvalidTokenException()

        val (accessToken,refreshToken) = issueTokensForUser(payload.sub)

        return TokensResponse(
            reason = TokenIssuedReason.REFRESHED,
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    private fun issueTokensForUser(userId: String) : Pair<String, String> {
        return Pair(
            jwtService.generateToken(JwtTypes.ACCESS, userId),
            jwtService.generateToken(JwtTypes.REFRESH, userId)
        )
    }
}