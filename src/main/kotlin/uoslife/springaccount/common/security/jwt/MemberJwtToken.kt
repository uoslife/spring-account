package uoslife.springaccount.common.security.jwt

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken

class MemberJwtToken(jwt: Jwt, authorities: Collection<GrantedAuthority?>?) :
    JwtAuthenticationToken(jwt, authorities) {

    val userId: Long = jwt.getClaimAsString("sub")!!.toLong()
}
