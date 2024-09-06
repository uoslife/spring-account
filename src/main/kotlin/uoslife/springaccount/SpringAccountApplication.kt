package uoslife.springaccount

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication @EnableFeignClients class SpringAccountApplication

fun main(args: Array<String>) {
    runApplication<SpringAccountApplication>(*args)
}
