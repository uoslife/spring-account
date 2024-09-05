package uoslife.springaccount.app.verification.dto.response

import java.time.LocalDateTime

class EmailResponseDto {
    data class EmailAuthSentResponse(
        val effectiveSeconds : Long,
        val expiresAt : LocalDateTime
    )
}
