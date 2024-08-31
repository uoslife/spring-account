package uoslife.springaccount.app.moderator.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import uoslife.springaccount.app.moderator.dto.response.ModeratorProfileResponse
import uoslife.springaccount.app.moderator.service.ModeratorService

@RestController
@RequestMapping("/v2/moderator")
class ModeratorController(private val moderatorService: ModeratorService) {

    @GetMapping
    fun getAllModerators(): ResponseEntity<List<ModeratorProfileResponse>> {
        return ResponseEntity.ok(moderatorService.getAllModerators())
    }
}
