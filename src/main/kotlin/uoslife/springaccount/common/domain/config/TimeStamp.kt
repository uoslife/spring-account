package uoslife.springaccount.common.domain.config

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import java.time.LocalDateTime
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class TimeStamp(
    @CreatedDate var createdAt: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate var updatedAt: LocalDateTime = LocalDateTime.now(),
)
