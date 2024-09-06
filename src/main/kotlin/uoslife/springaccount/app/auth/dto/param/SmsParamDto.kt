package uoslife.springaccount.app.auth.dto.param

class SmsParamDto {
    data class SmsRequest(
        val sender: String,
        val username: String,
        val key: String,
        val title: String,
        val message: String,
        val receiver: List<Receiver>
    )

    data class Receiver(
        val mobile: String
    )

    data class SmsSendResult(
        val status: String
    ) {
        fun isSuccess(): Boolean {
            return status == "0"
        }
    }
}