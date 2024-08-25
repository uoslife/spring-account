package uoslife.springaccount.app.user.controller

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import uoslife.springaccount.app.user.service.UserService

@RestController
class UserController(private val userService: UserService) {

    @GetMapping("/me")
    fun getMyProfile(@AuthenticationPrincipal userDetails: UserDetails) {
        val userId = userDetails.username.toLong()
    }
}
