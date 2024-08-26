package uoslife.springaccount.app.user.service

import org.springframework.cache.CacheManager
import org.springframework.stereotype.Service
import uoslife.springaccount.app.user.domain.repository.jpa.UserRepository
import uoslife.springaccount.app.user.dto.response.UserProfileDto
import uoslife.springaccount.common.error.user.UserNotFoundException

@Service
class UserService(
    private val userRepository: UserRepository,
    private val cacheManager: CacheManager,
) {
    fun getProfile(userId: Long): UserProfileDto.UserProfileResponse {
        val cacheKey = "uoslife:user:profile:$userId"
        val cache = cacheManager.getCache("userProfileCache")

        // 캐시에서 데이터 가져오기
        val cachedData = cache?.get(cacheKey, UserProfileDto.UserProfileResponse::class.java)
        if (cachedData != null) {
            return cachedData
        }

        // DB에서 유저 조회
        val user =
            userRepository.findByIdAndDeletedAtIsNull(userId) ?: throw UserNotFoundException()

        // 유저 엔티티를 UserProfileResponse DTO로 변환
        val userProfileResponse = UserProfileDto.toUserProfileResponse(user)

        // 캐시에 저장
        cache?.put(cacheKey, userProfileResponse)

        return userProfileResponse
    }
}
