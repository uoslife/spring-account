package uoslife.springaccount.common.error.response

import uoslife.springaccount.common.error.ErrorCode

data class ErrorResponse(
    val message: String,
    val status: Int,
    val code: String,
) {
    constructor(
        errorCode: ErrorCode
    ) : this(
        message = errorCode.message,
        status = errorCode.status,
        code = errorCode.code,
    )
}
