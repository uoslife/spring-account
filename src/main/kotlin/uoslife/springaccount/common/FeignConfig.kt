package uoslife.springaccount.common

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients(basePackages = ["uoslife.springaccount.intrastructure.externalservice"])
class FeignConfig
