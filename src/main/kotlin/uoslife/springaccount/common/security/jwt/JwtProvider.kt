package uoslife.springaccount.common.security.jwt

import com.nimbusds.jose.JOSEObjectType
import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.JWSHeader
import com.nimbusds.jose.JWSSigner
import com.nimbusds.jose.crypto.MACSigner
import com.nimbusds.jwt.JWTClaimsSet
import com.nimbusds.jwt.SignedJWT
import java.time.Duration
import java.time.Instant
import java.util.*

class JwtProvider(private val secretKey: String) {
    companion object{
        const val ISSUER_PREFIX = "uoslife/account"
        const val SCOPE_ACCESS: String = "access_token"
        const val SCOPE_REFRESH: String = "refresh_token"
        const val SCOPE_REGISTER: String = "register_token"
    }

    @Throws(Exception::class)
    fun generateAccessToken(sub:String, expirationTime: Duration?): String {
        return generateToken(sub, expirationTime, SCOPE_ACCESS)
    }

    @Throws(Exception::class)
    fun generateRefreshToken(sub:String, expirationTime: Duration?): String {
        return generateToken(sub, expirationTime, SCOPE_REFRESH)
    }

    @Throws(Exception::class)
    fun generateRegisterToken(sub:String, expirationTime: Duration?):String{
        return generateToken(sub, expirationTime, SCOPE_REGISTER)
    }

    @Throws(Exception::class)
    private fun generateToken(
        subClaim:String,
        expirationTime: Duration?,
        jti: String
    ): String {
        val signer: JWSSigner = MACSigner(secretKey)

        val builder = JWTClaimsSet.Builder()


        val claimsSet =
            builder
                .expirationTime(
                    if (expirationTime == null) null
                    else Date.from(Instant.now().plus(expirationTime))
                )
                .issueTime(Date())
                .claim("iss", ISSUER_PREFIX)
                .claim("aud", "$ISSUER_PREFIX/$jti")
                .claim("sub", subClaim)
                .build()
        val signedJWT =
            SignedJWT(
                JWSHeader.Builder(JWSAlgorithm.HS256).type(JOSEObjectType.JWT).build(),
                claimsSet
            )
        signedJWT.sign(signer)

        return signedJWT.serialize()
    }
}
