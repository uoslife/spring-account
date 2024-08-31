package uoslife.springaccount.app.moderator.domain.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import uoslife.springaccount.app.moderator.domain.entity.Moderators

interface ModeratorRepository : JpaRepository<Moderators, Long>
