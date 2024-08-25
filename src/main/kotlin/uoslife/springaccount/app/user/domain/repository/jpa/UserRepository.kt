package uoslife.springaccount.app.user.domain.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import uoslife.springaccount.app.user.domain.entity.User
import java.util.*

interface UserRepository : JpaRepository<User, Long> {
    fun findByIdAndDeletedAtIsNull(id: Long): Optional<User>
}
