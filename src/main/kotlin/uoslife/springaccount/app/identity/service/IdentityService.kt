package uoslife.springaccount.app.identity.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import uoslife.springaccount.app.identity.domain.entity.Identity
import uoslife.springaccount.app.identity.domain.repository.jpa.IdentityRepository
import uoslife.springaccount.app.identity.dto.response.IdentityDto

@Service
@Transactional(readOnly = true)
class IdentityService(
    private val identityRepository : IdentityRepository,
) {

    // 유저의 신분 정보를 가져옵니다.
    // 리빌드 이전의 시대생 유저인 경우, 복수 신분이 존재할 수 있습니다.
    fun getIdentityList(userId: Long):List<IdentityDto.IdentityResponse>? {
        val identityList =  identityRepository.findByUserId(userId);

        return IdentityDto.toResponseIdentityList(identityList);
    }

    // 여러 신분 중 대표 신분 설정합니다.
    fun selectRepresentativeIdentityFromList(userId: Long) :List<IdentityDto.IdentityResponse>?{
        val identityList =  identityRepository.findByUserId(userId);

        // 신분의 개수가 1개인 경우 업데이트 로직을 실행하지 않습니다.
        if (identityList?.size == 1) return null;

        // 여러 신분 중 대표 신분으로 설정하는 로직입니다.
        identityList?.forEach { identity: Identity ->
            identityRepository.updateActiveIdentityList(identity.id, userId)
        }

        return IdentityDto.toResponseIdentityList(identityList);
    }
}
