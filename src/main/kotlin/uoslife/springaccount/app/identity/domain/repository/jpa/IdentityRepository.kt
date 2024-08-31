package uoslife.springaccount.app.identity.domain.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import uoslife.springaccount.app.identity.domain.entity.Identity

interface IdentityRepository : JpaRepository<Identity, String> {
    fun findIdentitiesByUserId(userId: Long): List<Identity>?

    // 추후 queryDsl로 변경 예정.
    @Modifying
    @Query("UPDATE Identity i SET i.isActive = CASE WHEN i.id = :identityId THEN true ELSE false END WHERE i.userId = :userId")
    fun updateActiveIdentityList(identityId:String)
}