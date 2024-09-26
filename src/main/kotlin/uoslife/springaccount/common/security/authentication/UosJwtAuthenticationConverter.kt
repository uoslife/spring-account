package uoslife.springaccount.common.security.authentication

import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import uoslife.springaccount.common.security.jwt.MemberJwtToken
import uoslife.springaccount.common.security.jwt.UosJwtGrantedAuthoritiesConverter

class UosJwtAuthenticationConverter : Converter<Jwt, AbstractAuthenticationToken> {
    private val uosJwtGrantedAuthoritiesConverter = UosJwtGrantedAuthoritiesConverter()

    override fun convert(jwt: Jwt): AbstractAuthenticationToken {
        return convert(jwt, uosJwtGrantedAuthoritiesConverter.convert(jwt))
    }

    private fun convert(
        jwt: Jwt,
        authorities: Collection<GrantedAuthority?>?
    ): AbstractAuthenticationToken {
        return MemberJwtToken(jwt, authorities)
    }
}
