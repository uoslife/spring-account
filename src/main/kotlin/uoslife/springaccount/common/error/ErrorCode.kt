package uoslife.springaccount.common.error

import org.springframework.http.HttpStatus

enum class ErrorCode(val code: String, val message: String, var status: Int) {
    // Common
    INVALID_INPUT_VALUE("C01", "Invalid Input Value.", HttpStatus.BAD_REQUEST.value()),
    METHOD_NOT_ALLOWED("C02", "Invalid Method Type.", HttpStatus.METHOD_NOT_ALLOWED.value()),
    ENTITY_NOT_FOUND("C03", "Entity Not Found.", HttpStatus.BAD_REQUEST.value()),
    INTERNAL_SERVER_ERROR(
        "C04",
        "Internal Server Error.",
        HttpStatus.INTERNAL_SERVER_ERROR.value()
    ),

    // Auth
    INVALID_TOKEN_VALUE("A01", "Invalid Token Value.", HttpStatus.BAD_REQUEST.value()),
    TOKEN_EXPIRED_ERROR("A02", "Expired Token.", HttpStatus.UNAUTHORIZED.value()),

    // User
    USER_NOT_FOUND("U01", "User Not Found.", HttpStatus.NOT_FOUND.value()),
    USER_ID_NULL("U02", "User ID is Null", HttpStatus.INTERNAL_SERVER_ERROR.value()),
}
