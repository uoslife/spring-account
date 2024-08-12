package uoslife.springaccount

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication class SpringAccountApplication

fun main(args: Array<String>) {
    runApplication<SpringAccountApplication>(*args)
}
