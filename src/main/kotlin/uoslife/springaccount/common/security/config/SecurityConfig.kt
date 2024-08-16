package uoslife.springaccount.common.security.config

import javax.crypto.spec.SecretKeySpec
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.oauth2.jose.jws.MacAlgorithm
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.web.SecurityFilterChain
import uoslife.springaccount.common.security.authentication.CustomJwtAuthenticationConverter
import uoslife.springaccount.common.security.authentication.JwtAccessDeniedHandler
import uoslife.springaccount.common.security.authentication.JwtEntryPoint
import uoslife.springaccount.common.security.jwt.JwtProvider

@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@Configuration
@EnableWebSecurity
class SecurityConfig(
    @Value("\${security.jwt.secret}") private val secret: String,
) {

    @Bean
    fun jwtChain(
        httpSecurity: HttpSecurity,
        customJwtAuthenticationConverter: CustomJwtAuthenticationConverter
    ): SecurityFilterChain {
        httpSecurity.invoke {
            csrf{disable()}
            authorizeRequests {
                authorize("/v2/auth/**", permitAll)
                authorize("/v2/users", hasRole("REGISTER"))
                authorize(anyRequest, hasRole("ACCESS"))
            }
            oauth2ResourceServer {
                accessDeniedHandler = JwtAccessDeniedHandler()
                authenticationEntryPoint = JwtEntryPoint()
                jwt { jwtAuthenticationConverter = customJwtAuthenticationConverter }
            }
        }
        return httpSecurity.build()
    }

    @Bean
    fun jwtDecoder(): JwtDecoder {
        val algorithm: MacAlgorithm = MacAlgorithm.HS256

        return NimbusJwtDecoder.withSecretKey(
                SecretKeySpec(secret.toByteArray(), algorithm.getName())
            )
            .macAlgorithm(algorithm)
            .build()
    }

    @Bean
    fun jwtProvider(): JwtProvider {
        return JwtProvider(secret)
    }
}
