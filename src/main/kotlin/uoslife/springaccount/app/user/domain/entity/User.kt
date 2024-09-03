package uoslife.springaccount.app.user.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import uoslife.springaccount.app.device.domain.entity.Device
import uoslife.springaccount.app.identity.domain.entity.Identity
import uoslife.springaccount.app.moderator.domain.entity.Moderators
import uoslife.springaccount.app.verification.domain.entity.PortalAccounts
import uoslife.springaccount.app.verification.domain.entity.Verifications
import uoslife.springaccount.common.domain.config.TimeStamp

@Entity
@Table(name = "`user`")
class User(
    name: String,
    phoneNumber: String,
    email: String? = null,
    nickname: String? = null,
    birthday: String? = null,
    avatarUrl: String? = null,
) : TimeStamp() {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null

    @Column(nullable = false)
    var name: String = name
        protected set

    @Column(name = "phone_number", nullable = false)
    var phoneNumber: String = phoneNumber
        protected set

    var email: String? = email
        protected set

    var nickname: String? = nickname
        protected set

    var birthday: String? = birthday
        protected set

    var avatarUrl: String? = avatarUrl
        protected set

    @Column(name = "deleted_at") var deletedAt: LocalDateTime? = null

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    protected val mutableDevices: MutableList<Device> = mutableListOf()
    val devices: List<Device>
        get() = mutableDevices.toList()

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    protected val mutableVerifications: MutableList<Verifications> = mutableListOf()
    val verifications: List<Verifications>
        get() = mutableVerifications.toList()

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    protected val mutablePortalAccounts: MutableList<PortalAccounts> = mutableListOf()
    val portalAccounts: List<PortalAccounts>
        get() = mutablePortalAccounts.toList()

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    protected val mutableModerators: MutableList<Moderators> = mutableListOf()
    val moderators: List<Moderators>
        get() = mutableModerators.toList()

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    protected val mutableIdentities: MutableList<Identity> = mutableListOf()
    val identities: List<Identity>
        get() = mutableIdentities.toList()

    fun addDevice(device: Device) {
        mutableDevices.add(device)
    }

    fun addVerification(verification: Verifications) {
        mutableVerifications.add(verification)
    }

    fun addPortalAccount(portalAccount: PortalAccounts) {
        mutablePortalAccounts.add(portalAccount)
    }

    fun addModerator(moderator: Moderators) {
        mutableModerators.add(moderator)
    }

    fun addIdentity(identity: Identity) {
        mutableIdentities.add(identity)
    }

    fun updateUserProfile(nickname: String) {
        this.nickname = nickname
    }
}
