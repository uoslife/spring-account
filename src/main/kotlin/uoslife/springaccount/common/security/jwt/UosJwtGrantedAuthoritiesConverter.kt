package uoslife.springaccount.common.security.jwt

import org.springframework.core.convert.converter.Converter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt

class UosJwtGrantedAuthoritiesConverter : Converter<Jwt, Collection<GrantedAuthority>> {

    override fun convert(jwt: Jwt): Collection<GrantedAuthority> {
        val authClaim = jwt.getClaimAsString(JwtConfig.AUTH_PAYLOAD) // "aud" 필드 추출
        val authorities = mutableListOf<GrantedAuthority>()
        val userAuthorization = removeClaimPrefix(authClaim)

        if (userAuthorization == JwtConfig.SCOPE_ACCESS) {
            authorities.add(SimpleGrantedAuthority("ROLE_ACCESS"))
        }
        if (userAuthorization == JwtConfig.SCOPE_REGISTER) {
            authorities.add(SimpleGrantedAuthority("ROLE_REGISTER"))
        }

        return authorities
    }

    private fun removeClaimPrefix(
        authClaim:String
    ):String {
        val authClaimString = authClaim.removePrefix("[").removeSuffix("]")
        return authClaimString.drop("${JwtConfig.ISSUER_PREFIX}/".length)
    }
}
