package uoslife.springaccount.app.verification.domain.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import uoslife.springaccount.app.verification.domain.entity.Verifications

interface VerificationRepository:JpaRepository<Verifications, Long>