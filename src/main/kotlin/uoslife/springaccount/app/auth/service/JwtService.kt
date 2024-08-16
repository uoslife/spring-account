package uoslife.springaccount.app.auth.service

import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.stereotype.Service
import uoslife.springaccount.app.auth.util.JwtPayload
import uoslife.springaccount.app.auth.util.JwtTypes
import uoslife.springaccount.common.error.auth.ExpiredTokenException
import uoslife.springaccount.common.security.jwt.JwtProvider
import java.time.Instant

@Service
class JwtService (
    private val jwtProvider: JwtProvider,
    private val jwtDecoder: JwtDecoder
){

    fun validToken(refreshToken: String): JwtPayload {
        val claims = jwtDecoder.decode(refreshToken).claims

        val payload = JwtPayload.toPayload(claims)

        if (payload.exp < Instant.now().epochSecond) throw ExpiredTokenException()

        return payload
    }

    fun generateToken(type: JwtTypes, userId: String): String {
        if(type==JwtTypes.ACCESS) {
            return jwtProvider.generateAccessToken(userId, type.ttl)
        }
        if(type==JwtTypes.REGISTER){
            return jwtProvider.generateRegisterToken(userId, type.ttl)
        }
        return jwtProvider.generateRefreshToken(userId, type.ttl)
    }
}