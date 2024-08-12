package uoslife.springaccount.common.security.authentication

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.nio.charset.Charset
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.oauth2.core.OAuth2AuthenticationException
import org.springframework.security.oauth2.server.resource.BearerTokenErrors
import org.springframework.security.web.AuthenticationEntryPoint

class JwtEntryPoint : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        val error =
            if (authException is OAuth2AuthenticationException)
                BearerTokenErrors.invalidToken(authException.error.description)
            else BearerTokenErrors.invalidToken(authException?.message)

        response!!.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = HttpStatus.UNAUTHORIZED.value()
        response.characterEncoding = Charset.defaultCharset().name()

        response.writer.write(error.errorCode)
        response.writer.flush()
    }
}
