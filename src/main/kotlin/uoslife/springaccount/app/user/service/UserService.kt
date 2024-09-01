package uoslife.springaccount.app.user.service

import java.util.concurrent.TimeUnit
import org.redisson.api.RBucket
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Service
import uoslife.springaccount.app.user.domain.repository.jpa.UserRepository
import uoslife.springaccount.app.user.dto.response.UserProfileDto
import uoslife.springaccount.app.user.util.UserConfig
import uoslife.springaccount.common.error.user.UserNotFoundException

@Service
class UserService(
    private val userRepository: UserRepository,
    private val redisClient: RedissonClient,
) {

    fun getProfile(userId: Long): UserProfileDto.UserProfileResponse {
        val cacheKey = getProfileCacheKey(userId)

        // 캐시된 데이터 가져오기
        val bucket: RBucket<UserProfileDto.UserProfileResponse> = redisClient.getBucket(cacheKey)
        val cachedData = bucket.get()
        if (cachedData != null) {
            return cachedData
        }

        // DB에서 유저 조회
        val user =
            userRepository.findByIdAndDeletedAtIsNull(userId) ?: throw UserNotFoundException()

        // User 엔티티를 DTO로 변환
        val userProfileResponse = UserProfileDto.toUserProfileResponse(user)

        // 캐시 저장
        bucket.set(userProfileResponse, UserConfig.USER_PROFILE_CACHE_TTL, TimeUnit.SECONDS)

        return userProfileResponse
    }

    private fun getProfileCacheKey(userId: Long): String {
        return "uoslife:user:profile:$userId"
    }

    private fun isPhoneNumberDuplicated(phoneNumber: String): Boolean {
        return userRepository.existsByPhoneNumber(phoneNumber)
    }

    private fun isNicknameDuplicated(nickname: String): Boolean {
        return userRepository.existsByNicknameAndDeletedAtIsNull(nickname)
    }
}
