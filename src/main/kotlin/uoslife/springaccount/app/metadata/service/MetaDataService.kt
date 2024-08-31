package uoslife.springaccount.app.metadata.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import uoslife.springaccount.app.metadata.domain.Realm
import uoslife.springaccount.app.metadata.dto.response.RealmProfileResponse

@Service
@Transactional(readOnly = true)
class MetaDataService {

    fun getRealms(): List<RealmProfileResponse> {
        return Realm.entries.map {
            RealmProfileResponse(
                code = it.code,
                name = it.koreanName,
                allowedDomains = it.allowedDomains
            )
        }
    }
}
