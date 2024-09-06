package uoslife.springaccount.app.auth.util

enum class TokenIssuedReason(val tokenIssuedReason: String) {
    LOGGED_IN("logged_in"),
    REGISTERING("registering"),
    REGISTERED("registered"),
    REFRESHED("refreshed"),
}
