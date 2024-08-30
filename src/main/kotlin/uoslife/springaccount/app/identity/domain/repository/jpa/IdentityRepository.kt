package uoslife.springaccount.app.identity.domain.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import uoslife.springaccount.app.identity.domain.entity.Identity

interface IdentityRepository : JpaRepository<Identity, String> {
    fun findIdentityListByUuid(uuid: String): List<Identity>?
}