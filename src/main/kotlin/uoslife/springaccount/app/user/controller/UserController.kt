package uoslife.springaccount.app.user.controller

import org.springframework.web.bind.annotation.RestController
import uoslife.springaccount.app.user.service.UserService

@RestController
class UserController(private val userService: UserService) {
}