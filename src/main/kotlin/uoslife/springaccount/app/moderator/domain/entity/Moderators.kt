package uoslife.springaccount.app.moderator.domain.entity

import jakarta.persistence.*
import uoslife.springaccount.app.user.domain.entity.User
import uoslife.springaccount.common.domain.config.TimeStamp

//
// CREATE TABLE "moderators" (
// "id" BIGSERIAL NOT NULL,
// "user_id" BIGINT NOT NULL,
// "real_name" TEXT NOT NULL,
// "email" TEXT NOT NULL,
// "generation" TEXT NOT NULL,
// "phone_number" TEXT NOT NULL,
// "student_number" TEXT NOT NULL,
// "chapter" TEXT NOT NULL,
// "role" TEXT NOT NULL,
// "updated_at" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
// "created_at" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
//
// CONSTRAINT "moderators_pkey" PRIMARY KEY ("id")
// );
@Entity
@Table(name = "`moderators`")
class Moderators(
    user: User? = null,
    realName: String,
    email: String,
    generation: String,
    phoneNumber: String,
    studentNumber: String,
    chapter: String,
    role: String,
    notionId: String,
    slackId: String? = null,
) : TimeStamp() {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "user_id", unique = true, nullable = true)
    var user: User? = user
        protected set

    @Column(name = "real_name", nullable = false)
    var realName: String = realName
        protected set

    @Column(nullable = false)
    var email: String = email
        protected set

    @Column(nullable = false)
    var generation: String = generation
        protected set

    @Column(name = "phone_number", nullable = false)
    var phoneNumber: String = phoneNumber
        protected set

    @Column(name = "student_number", nullable = false)
    var studentNumber: String = studentNumber
        protected set

    @Column(nullable = false)
    var chapter: String = chapter
        protected set

    @Column(nullable = false)
    var role: String = role
        protected set

    @Column(name = "notion_id", nullable = false, unique = true)
    var notionId: String = notionId
        protected set

    @Column(name = "slack_id", nullable = true)
    var slackId: String? = slackId
        protected set

    init {
        user?.addModerator(this)
    }
}
