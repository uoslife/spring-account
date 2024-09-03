package uoslife.springaccount.app.user.controller

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import uoslife.springaccount.app.user.dto.param.UpdateUserDto
import uoslife.springaccount.app.user.dto.request.UpdateProfileRequestDto
import uoslife.springaccount.app.user.dto.response.UserProfileDto
import uoslife.springaccount.app.user.service.UserService

@RestController
@RequestMapping("/v2/user")
class UserController(private val userService: UserService) {

    @GetMapping("/me")
    fun getMyProfile(
        @AuthenticationPrincipal userDetails: UserDetails
    ): ResponseEntity<UserProfileDto.UserProfileResponse> {
        val userId = userDetails.username.toLong()
        return ResponseEntity.ok(userService.getProfile(userId))
    }

    @GetMapping("/{userId}")
    fun getProfile(
        @PathVariable userId: Long,
    ): ResponseEntity<UserProfileDto.UserProfileResponse> {
        return ResponseEntity.ok(userService.getProfile(userId))
    }

    @PatchMapping("/me")
    fun updateMyProfile(
        @AuthenticationPrincipal userDetails: UserDetails,
        @Valid request: UpdateProfileRequestDto
    ): ResponseEntity<UserProfileDto.UserProfileResponse> {

        val data = UpdateUserDto(
            userId = userDetails.username.toLong(),
            nickname = request.nickname,
        )
        return ResponseEntity.ok(userService.updateProfile(data))
    }
}
