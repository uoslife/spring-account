package uoslife.springaccount.common.error.auth

import uoslife.springaccount.common.error.ErrorCode
import uoslife.springaccount.common.error.baseexception.InvalidValueException

class ExpiredTokenException : InvalidValueException(ErrorCode.TOKEN_EXPIRED_ERROR)
