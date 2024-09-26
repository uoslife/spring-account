package uoslife.springaccount.common.security.authentication

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.oauth2.core.OAuth2AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint

class JwtEntryPoint : AuthenticationEntryPoint {
    companion object {
        private val logger = LoggerFactory.getLogger(JwtEntryPoint::class.java)
    }
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        logger.error(authException?.localizedMessage, authException)

        response?.contentType = "application/json"
        response?.characterEncoding = "UTF-8"

        if (authException is OAuth2AuthenticationException) {
            response?.status = HttpServletResponse.SC_UNAUTHORIZED // 401 Unauthorized
            response
                ?.writer
                ?.write("{\"error\": \"Expired token\", \"message\": \"Your token has expired.\"}")
        } else {
            response?.status = HttpServletResponse.SC_UNAUTHORIZED // 401 Unauthorized
            response
                ?.writer
                ?.write("{\"error\": \"Invalid token\", \"message\": \"Your token is invalid.\"}")
        }
    }
}
