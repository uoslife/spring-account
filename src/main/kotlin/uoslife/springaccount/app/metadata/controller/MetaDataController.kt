package uoslife.springaccount.app.metadata.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import uoslife.springaccount.app.metadata.dto.response.RealmProfileResponse
import uoslife.springaccount.app.metadata.service.MetaDataService

@RestController
@RequestMapping("v2/meta-data")
class MetaDataController(private val metaDataService: MetaDataService) {

    @GetMapping
    fun getRealms(): ResponseEntity<List<RealmProfileResponse>> =
        ResponseEntity.ok(metaDataService.getRealms())
}
