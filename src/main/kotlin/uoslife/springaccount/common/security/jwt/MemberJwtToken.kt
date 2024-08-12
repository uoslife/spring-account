package uoslife.springaccount.common.security.jwt

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken

class MemberJwtToken(jwt: Jwt, authorities: Collection<GrantedAuthority?>?) :
    JwtAuthenticationToken(jwt, authorities) {

    val memberId: Long = jwt.getClaimAsString("member_id")!!.toLong()
}
