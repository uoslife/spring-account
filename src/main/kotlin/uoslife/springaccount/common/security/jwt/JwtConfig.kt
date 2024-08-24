package uoslife.springaccount.common.security.jwt

class JwtConfig {
    companion object{
        const val ISSUER_PREFIX = "uoslife/account"
        const val SCOPE_ACCESS: String = "access_token"
        const val SCOPE_REFRESH: String = "refresh_token"
        const val SCOPE_REGISTER: String = "register_token"
        const val AUTH_PAYLOAD: String = "aud"
    }
}