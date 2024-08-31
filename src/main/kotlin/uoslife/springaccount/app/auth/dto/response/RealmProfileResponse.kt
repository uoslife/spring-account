package uoslife.springaccount.app.auth.dto.response

data class RealmProfileResponse(
    val code: String,
    val name: String,
    val allowedDomains: List<String>,
)
