package uoslife.springaccount.app.moderator.dto.param

import org.hibernate.validator.constraints.UUID
import org.springframework.validation.annotation.Validated
import uoslife.springaccount.app.moderator.domain.entity.Moderators

// TODO : 필드 체크 및 user id 동작 확인
@Validated
data class ModeratorFromNotion(
    @field:UUID val notionId: String,
    val realName: String,
    val email: String,
    val generation: String,
    val phoneNumber: String,
    val studentNumber: String,
    val chapter: String,
    val role: String,
    val userId: Long? = null,
    val slackId: String?,
) {
    fun toEntity(): Moderators {
        return Moderators(
            notionId = notionId,
            realName = realName,
            email = email,
            generation = generation,
            phoneNumber = phoneNumber,
            studentNumber = studentNumber,
            chapter = chapter,
            role = role,
            slackId = slackId,
        )
    }
}
