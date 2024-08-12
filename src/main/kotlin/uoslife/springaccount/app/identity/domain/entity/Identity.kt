package uoslife.springaccount.app.identity.domain.entity

import jakarta.persistence.*
import java.util.*
import uoslife.springaccount.app.user.domain.entity.User
import uoslife.springaccount.common.domain.config.TimeStamp

@Entity
@Table(name = "`identities`")
class Identity(
    user: User,
    type: String,
    status: String,
    idNumber: String,
    university: String? = null,
    universityCode: String? = null,
    department: String? = null,
    departmentCode: String? = null,
    major: String? = null,
    majorCode: String? = null,
    isActive: Boolean = false,
) : TimeStamp() {

    @Id
    var id: String = UUID.randomUUID().toString()
        protected set

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User = user
        protected set

    @Column(nullable = false)
    var type: String = type
        protected set

    @Column(nullable = false)
    var status: String = status
        protected set

    @Column(name = "id_number", nullable = false)
    var idNumber: String = idNumber
        protected set

    var university: String? = university
        protected set

    @Column(name = "university_code")
    var universityCode: String? = universityCode
        protected set

    var department: String? = department
        protected set

    @Column(name = "department_code")
    var departmentCode: String? = departmentCode
        protected set

    var major: String? = major
        protected set

    @Column(name = "major_code")
    var majorCode: String? = majorCode
        protected set

    @Column(name = "is_active", nullable = false)
    var isActive: Boolean = isActive
        protected set

    init {
        user.addIdentity(this)
    }
}
