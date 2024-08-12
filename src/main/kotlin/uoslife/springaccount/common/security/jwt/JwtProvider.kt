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

    @Throws(Exception::class)
    fun generateAccessToken(claims: Map<String, Any>, expirationTime: Duration?): String {
        return generateToken(claims, expirationTime, SCOPE_ACCESS)
    }

    @Throws(Exception::class)
    fun generateRefreshToken(expirationTime: Duration?): String {
        return generateToken(emptyMap(), expirationTime, SCOPE_REFRESH)
    }

    @Throws(Exception::class)
    private fun generateToken(
        claims: Map<String, Any>,
        expirationTime: Duration?,
        jti: String
    ): String {
        val signer: JWSSigner = MACSigner(secretKey)

        val builder = JWTClaimsSet.Builder()

        for (key in claims.keys) {
            builder.claim(key, claims[key])
        }

        val claimsSet =
            builder
                .expirationTime(
                    if (expirationTime == null) null
                    else Date.from(Instant.now().plus(expirationTime))
                )
                .issueTime(Date())
                .claim("scp", jti)
                .build()
        val signedJWT =
            SignedJWT(
                JWSHeader.Builder(JWSAlgorithm.HS256).type(JOSEObjectType.JWT).build(),
                claimsSet
            )
        signedJWT.sign(signer)

        return signedJWT.serialize()
    }

    companion object {
        const val SCOPE_ACCESS: String = "acc"
        const val SCOPE_REFRESH: String = "ref"
    }
}
