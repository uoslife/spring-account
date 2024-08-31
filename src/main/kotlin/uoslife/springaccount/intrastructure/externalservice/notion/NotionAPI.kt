package uoslife.springaccount.intrastructure.externalservice.notion

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import uoslife.springaccount.app.moderator.dto.response.ModeratorProfileResponse

// TODO : 동작 확인 및 노션 api spec 확인
@FeignClient(name = "notion-api", url = "https://api.notion.com")
interface NotionClient {
    @PostMapping("/v1/databases/{databaseId}/query")
    fun queryDatabase(
        @PathVariable databaseId: String,
        @RequestBody sorts: List<Sort>,
        @RequestHeader("Authorization") token: String,
        @RequestHeader("Notion-Version") notionVersion: String = "2022-06-28"
    ): ModeratorProfileResponse
}

data class Sort(
    val property: String,
    val direction: String,
)
