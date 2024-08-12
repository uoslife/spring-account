package uoslife.springaccount.common.security.jwt

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component
import uoslife.springaccount.common.security.authentication.SimpleJwtAuthenticationConverter

@Component
class MemberJwtAuthenticationConverter : SimpleJwtAuthenticationConverter() {
    override fun convert(
        jwt: Jwt,
        authorities: Collection<GrantedAuthority?>?
    ): AbstractAuthenticationToken {
        return MemberJwtToken(jwt, authorities)
    }
}
