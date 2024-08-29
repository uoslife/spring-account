package uoslife.springaccount.app.user.dto.request

import jakarta.validation.constraints.NotEmpty

data class RegisterDto(
    @field:NotEmpty(message = "닉네임은 비워둘 수 없습니다.")
    val nickname: String
)