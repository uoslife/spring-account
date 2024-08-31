package uoslife.springaccount.app.moderator.domain.repository.reader

import org.springframework.stereotype.Repository
import uoslife.springaccount.app.moderator.domain.entity.Moderators
import uoslife.springaccount.app.moderator.domain.repository.jpa.ModeratorRepository

@Repository
class ModeratorReader(private val moderatorRepository: ModeratorRepository) {

    fun findAll(): List<Moderators> = moderatorRepository.findAll()
}
