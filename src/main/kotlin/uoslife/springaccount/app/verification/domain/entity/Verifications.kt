package uoslife.springaccount.app.verification.domain.entity

import jakarta.persistence.*
import java.util.*
import uoslife.springaccount.app.user.domain.entity.User
import uoslife.springaccount.common.domain.config.TimeStamp

//
// -- CreateTable
// CREATE TABLE "verifications" (
//     "id" TEXT NOT NULL,
//     "user_id" BIGINT NOT NULL,
//     "method" "VerificationMethod" NOT NULL,
//     "code" TEXT,
//     "verified_at" TIMESTAMP(3),
//     "created_at" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
//
//     CONSTRAINT "verifications_pkey" PRIMARY KEY ("id")
// );
@Entity
@Table(name = "`verifications`")
@AttributeOverride(name = "updatedAt", column = Column(name = "verified_at"))
class Verifications(
    user: User,
    method: VerificationMethod,
    code: String? = null,
) : TimeStamp() {
    @Id
    var id: String = UUID.randomUUID().toString()
        protected set

    @Enumerated(EnumType.STRING)
    var method: VerificationMethod = method
        protected set

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User = user
        protected set

    var code: String? = code
        protected set

    init {
        user.addVerification(this)
    }
}
