package uoslife.springaccount.app.auth.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import uoslife.springaccount.app.auth.dto.response.RealmProfileResponse
import uoslife.springaccount.app.auth.service.MetaDataService

@RestController
@RequestMapping("v2/meta-data")
class MetaDataController(private val metaDataService: MetaDataService) {

    @GetMapping fun getRealms(): List<RealmProfileResponse> = metaDataService.getRealms()
}
