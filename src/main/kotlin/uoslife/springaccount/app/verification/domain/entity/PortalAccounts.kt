package uoslife.springaccount.app.verification.domain.entity

import jakarta.persistence.*
import uoslife.springaccount.app.user.domain.entity.User

@Entity
@Table(name = "`portal_accounts`")
class PortalAccounts(
    user: User,
    username: String,
    password: String,
    status: PortalAccountStatus = PortalAccountStatus.ACTIVE,
    reason: String? = null,
) {
    @Id var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User = user
        protected set

    var username: String = username
        protected set

    var password: String = password
        protected set

    @Enumerated(EnumType.STRING)
    var status: PortalAccountStatus = status
        protected set

    var reason: String? = reason
        protected set

    init {
        user.addPortalAccount(this)
    }
}
