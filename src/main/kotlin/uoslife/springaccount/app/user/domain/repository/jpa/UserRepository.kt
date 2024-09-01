package uoslife.springaccount.app.user.domain.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import uoslife.springaccount.app.user.domain.entity.User

interface UserRepository : JpaRepository<User, Long> {
    fun findByIdAndDeletedAtIsNull(id: Long): User?
    fun findByPhoneNumberAndDeletedAtIsNotNull(phoneNumber: String): User?
    fun existsByPhoneNumber(phoneNumber: String): Boolean
    fun existsByNicknameAndDeletedAtIsNull(nickname: String): Boolean
}
