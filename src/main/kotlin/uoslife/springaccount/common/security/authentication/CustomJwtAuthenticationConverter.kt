package uoslife.springaccount.common.security.authentication

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import org.springframework.stereotype.Component

@Component
class CustomJwtAuthenticationConverter : JwtAuthenticationConverter() {

    init {
        // 권한을 설정하는 로직 추가
        this.setJwtGrantedAuthoritiesConverter { jwt ->
            val audClaim = jwt.getClaimAsString("aud") // "aud" 필드 추출
            val authorities = mutableListOf<GrantedAuthority>()

            if (audClaim.contains("access")){
                authorities.add(SimpleGrantedAuthority("ROLE_ACCESS"))
            }
            if (audClaim.contains("register")){
                authorities.add(SimpleGrantedAuthority("ROLE_REGISTER"))
            }

            // 기본 권한 변환기 사용 가능 (현재는 scope, scp 사용 안함)
            // authorities.addAll(JwtGrantedAuthoritiesConverter().convert(jwt)!!)

            authorities
        }
    }
}
