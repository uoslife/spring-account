package uoslife.springaccount.app.auth.util

data class JwtPayload(
    val iss: String,
    val aud: String,
    //    val scp: String,
    val sub: String,
    //    val nbf: Int,
    val iat: Long,
    val exp: Long,
) {
    companion object {
        fun toPayload(claims: MutableMap<String, Any>): JwtPayload {
            val audList = claims["aud"] as List<String>
            val aud = audList[0].split("/").last()
            val iat = claims["iat"] as java.time.Instant
            val exp = claims["exp"] as java.time.Instant

            return JwtPayload(
                iss = claims["iss"] as String,
                aud = aud,
                sub = claims["sub"] as String,
                iat = iat.epochSecond,
                exp = exp.epochSecond
            )
        }
    }
}
