package uoslife.springaccount.app.moderator.jobs

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import uoslife.springaccount.app.moderator.dto.response.ModeratorProfileResponse
import uoslife.springaccount.common.distributelock.DistributedLock
import uoslife.springaccount.intrastructure.externalservice.notion.NotionClient
import uoslife.springaccount.intrastructure.externalservice.notion.Sort

private val logger = KotlinLogging.logger {}

@Service
class NotionJobService(
    @Value("\${spring.profiles.default:dev}") private val appEnv: String,
    @Value("\${notion.api.token}") private val apiToken: String,
    @Value("\${notion.moderators.database.id:7bd5dd6961c5451eb44acba427fd7670}")
    private val moderatorsDatabaseId: String,
    private val notionClient: NotionClient
) {

    @Scheduled(cron = "0 */15 * * * *")
    @DistributedLock("syncModeratorsFromNotion")
    fun syncModerators() {
        // TODO 노션에서 업데이트 된 정보를 가져와서 DB에 반영
        logger.info { "Sync Moderators" }
    }

    fun getModeratorsFromNotion(): ModeratorProfileResponse {
        return notionClient.queryDatabase(
            databaseId = moderatorsDatabaseId,
            sorts = listOf(Sort("Name", "ascending")),
            token = "Bearer $apiToken"
        )
    }

    fun updateModerators() {
        val moderators = getModeratorsFromNotion()
        logger.info { "Moderators: $moderators" }
    }
}
