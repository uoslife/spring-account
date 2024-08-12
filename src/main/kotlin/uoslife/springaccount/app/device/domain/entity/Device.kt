package uoslife.springaccount.app.device.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import uoslife.springaccount.app.user.domain.entity.User

// -- CreateTable
// CREATE TABLE "devices" (
//     "id" BIGSERIAL NOT NULL,
//     "user_id" BIGINT NOT NULL,
//     "firebase_push_token" TEXT,
//     "model" TEXT NOT NULL,
//     "os" TEXT NOT NULL,
//     "os_version" TEXT NOT NULL,
//     "app_version" TEXT NOT NULL,
//     "code_push_version" TEXT NOT NULL,
//     "last_used_at" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
//     "updated_at" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
//     "created_at" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
//
//     CONSTRAINT "devices_pkey" PRIMARY KEY ("id")
// );
@Entity
@Table(name = "`device`")
class Device(
    user: User,
    firebasePushToken: String? = null,
    model: String,
    os: String,
    osVersion: String,
    appVersion: String,
    codePushVersion: String,
) {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User = user
        protected set

    @Column(name = "firebase_push_token", nullable = true)
    var firebasePushToken: String? = firebasePushToken
        protected set

    @Column(nullable = false)
    var model: String = model
        protected set

    @Column(nullable = false)
    var os: String = os
        protected set

    @Column(name = "os_version", nullable = false)
    var osVersion: String = osVersion
        protected set

    @Column(name = "app_version", nullable = false)
    var appVersion: String = appVersion
        protected set

    @Column(name = "code_push_version", nullable = false)
    var codePushVersion: String = codePushVersion
        protected set

    @Column(name = "last_used_at", nullable = false)
    var lastUsedAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    val updatedAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.now()

    @PostLoad
    private fun updateLastUsedAt() {
        lastUsedAt = LocalDateTime.now()
    }

    init {
        user.addDevice(this)
    }
}
