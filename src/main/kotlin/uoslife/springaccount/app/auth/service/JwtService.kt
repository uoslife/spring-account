package uoslife.springaccount.app.auth.service

import java.time.Instant
import org.redisson.api.RBucket
import org.redisson.api.RedissonClient
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.stereotype.Service
import uoslife.springaccount.app.auth.util.JwtPayload
import uoslife.springaccount.app.auth.util.JwtTypes
import uoslife.springaccount.common.error.auth.ExpiredTokenException
import uoslife.springaccount.common.error.auth.InvalidTokenException
import uoslife.springaccount.common.security.jwt.JwtConfig
import uoslife.springaccount.common.security.jwt.JwtProvider

@Service
class JwtService(
    private val jwtProvider: JwtProvider,
    private val jwtDecoder: JwtDecoder,
    private val redisClient: RedissonClient
) {

    fun validToken(token: String): JwtPayload {
        val cacheKey = "uoslife:auth:token:verified:${token}"

        val cachePayloadBucket: RBucket<JwtPayload> = redisClient.getBucket(cacheKey)
        if (cachePayloadBucket.isExists) return cachePayloadBucket.get()

        val claims = jwtDecoder.decode(token).claims

        val payload = JwtPayload.toPayload(claims)

        if (payload.iss != JwtConfig.ISSUER_PREFIX) throw InvalidTokenException()
        if (payload.exp < Instant.now().epochSecond) throw ExpiredTokenException()

        redisClient.getBucket<JwtPayload>(cacheKey).set(payload)

        return payload
    }

    fun generateToken(type: JwtTypes, userId: String): String {
        if (type == JwtTypes.ACCESS) {
            return jwtProvider.generateAccessToken(userId, type.ttl)
        }
        if (type == JwtTypes.REGISTER) {
            return jwtProvider.generateRegisterToken(userId, type.ttl)
        }
        return jwtProvider.generateRefreshToken(userId, type.ttl)
    }
}
