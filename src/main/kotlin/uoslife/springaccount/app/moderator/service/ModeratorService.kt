package uoslife.springaccount.app.moderator.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import uoslife.springaccount.app.moderator.domain.repository.reader.ModeratorReader
import uoslife.springaccount.app.moderator.dto.response.ModeratorProfileResponse

@Service
@Transactional(readOnly = true)
class ModeratorService(private val moderatorReader: ModeratorReader) {

    fun getAllModerators(): List<ModeratorProfileResponse> {
        return moderatorReader.findAll().map { ModeratorProfileResponse.of(it) }
    }
}
