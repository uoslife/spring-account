package uoslife.springaccount.common.error.baseexception

import uoslife.springaccount.common.error.ErrorCode

open class ExternalApiFailedException : BusinessException {

    constructor(errorCode: ErrorCode) : super(errorCode)

    constructor(message: String, errorCode: ErrorCode) : super(message, errorCode)
}
