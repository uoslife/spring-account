package uoslife.springaccount.error

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import uoslife.springaccount.common.error.ErrorCode

@RestController
class ErrorCodeController {

    @GetMapping("/error-code", consumes = ["application/json"], produces = ["application/json"])
    fun findEnums(): Map<String, ErrorResponse> {
        val map = HashMap<String, ErrorResponse>()
        for (errorCode in ErrorCode.entries) {
            map[errorCode.name] = ErrorResponse(errorCode)
        }
        return map
    }

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
}
