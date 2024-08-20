package uoslife.springaccount.app.user.dto.param

import uoslife.springaccount.app.identity.domain.entity.Identity
import uoslife.springaccount.app.moderator.domain.entity.Moderators
import uoslife.springaccount.app.verification.domain.entity.PortalAccounts
import uoslife.springaccount.app.verification.domain.entity.Verifications

data class UserWithInfoDto(
    val identities: List<Identity>,
    val portalAccounts: List<PortalAccounts>,
    val verifications: List<Verifications>,
    val moderator: Moderators?,
)
