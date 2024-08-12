package uoslife.springaccount.common.security.jwt

import org.springframework.security.core.context.SecurityContextHolder

object MemberJwtContextHolder {
    val memberJwtToken: MemberJwtToken
        get() = SecurityContextHolder.getContext().authentication as MemberJwtToken
}
