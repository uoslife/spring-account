package uoslife.springaccount.common.security.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import uoslife.springaccount.common.security.annotation.UserIdArgumentResolver

@Configuration
class WebConfig(private val userIdArgumentResolver: UserIdArgumentResolver) : WebMvcConfigurer {

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(userIdArgumentResolver)
    }
}
