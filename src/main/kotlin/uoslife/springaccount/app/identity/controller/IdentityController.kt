package uoslife.springaccount.app.identity.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import uoslife.springaccount.app.identity.dto.response.IdentityDto
import uoslife.springaccount.app.identity.service.IdentityService


@RestController
@RequestMapping("/v2/identities")
class IdentityController(private val identityService: IdentityService) {
    @GetMapping
    fun getMyIdentities(
        @AuthenticationPrincipal userDetails: UserDetails
    ): ResponseEntity<List<IdentityDto.IdentityResponse>> {
        val userId = userDetails.username.toLong();

        return ResponseEntity.ok(identityService.getIdentityList(userId))
    }

    @PatchMapping("/{identityId}")
    fun selectRepresentativeIdentity(@AuthenticationPrincipal userDetails: UserDetails, @PathVariable identityId: String
    ) :ResponseEntity<List<IdentityDto.IdentityResponse>> {
        val userId = userDetails.username.toLong();

        val result = identityService.selectRepresentativeIdentityFromList(userId)

        //TODO: identityList가 1개인 경우, 응답값 반환에 대한 고민 필요.
        return if (result == null) {
            ResponseEntity.ok("수정할 필요가 없습니다.");
        } else {
            ResponseEntity.ok(result)
        }
    }
}