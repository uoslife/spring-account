package uoslife.springaccount.app.user.util

class UserConfig {
    companion object {
        const val USER_PROFILE_CACHE_TTL: Long = 60 * 60 // 유저 정보 저장 시간. 1시간
    }
}
