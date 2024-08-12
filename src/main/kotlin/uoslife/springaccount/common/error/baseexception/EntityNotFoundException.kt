package uoslife.springaccount.common.error.baseexception

import uoslife.springaccount.common.error.ErrorCode

open class EntityNotFoundException : BusinessException {

    constructor(errorCode: ErrorCode) : super(errorCode)

    constructor(message: String) : super(message, ErrorCode.ENTITY_NOT_FOUND)
}
