package uoslife.springaccount.app.auth.util

import java.time.Duration

enum class JwtTypes(
    val typeName : String,
    val description:String,
    val ttl: Duration

) {
    ACCESS(
        "access_token",
        "서비스 사용을 위한 토큰",
        Duration.ofHours(1)
    ),
    REFRESH(
        "refresh_token",
        "액세스 토큰 갱신을 위한 토큰",
        Duration.ofDays(14)
    ),
    REGISTER(
        "register_token",
        "가입 과정에서 사용하기 위한 토큰",
        Duration.ofHours(1),
    )
}