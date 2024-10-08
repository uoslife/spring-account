package uoslife.springaccount.app.user.domain.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import uoslife.springaccount.app.user.domain.entity.User

interface UserRepository : JpaRepository<User, Long>
