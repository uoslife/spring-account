package uoslife.springaccount.common.security.authentication

import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter

abstract class SimpleJwtAuthenticationConverter : Converter<Jwt, AbstractAuthenticationToken> {
    private val jwtAuthenticationConverter = JwtAuthenticationConverter()

    override fun convert(jwt: Jwt): AbstractAuthenticationToken {
        val token = jwtAuthenticationConverter.convert(jwt)
        val authorities = token!!.authorities
        return convert(jwt, authorities)
    }

    abstract fun convert(
        jwt: Jwt,
        authorities: Collection<GrantedAuthority?>?
    ): AbstractAuthenticationToken
}
