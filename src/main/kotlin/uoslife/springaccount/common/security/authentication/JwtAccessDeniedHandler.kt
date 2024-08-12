package uoslife.springaccount.common.security.authentication

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler

class JwtAccessDeniedHandler : AccessDeniedHandler {
    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        accessDeniedException: AccessDeniedException?
    ) {
        response?.contentType = "application/json;charset=UTF-8"
        response?.status = HttpServletResponse.SC_FORBIDDEN
        response?.writer?.write("{\"message\":\"Access Denied\"}")
        response?.writer?.flush()
    }
}
