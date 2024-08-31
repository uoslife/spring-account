package uoslife.springaccount.app.identity.service

import uoslife.springaccount.app.identity.domain.entity.Identity
import uoslife.springaccount.app.identity.domain.repository.jpa.IdentityRepository
import uoslife.springaccount.app.identity.dto.response.IdentityDto

class IdentityService(
    private val identityRepository : IdentityRepository,
) {
    fun getIdentityList(uuid: String):List<IdentityDto.IdentityReponse>? {
        val identityList =  identityRepository.findIdentityListByUuid(uuid);

        return identityList?.map { identity: Identity -> IdentityDto.toResponse(identity) }
    }
}
