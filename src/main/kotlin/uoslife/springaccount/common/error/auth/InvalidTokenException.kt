package uoslife.springaccount.common.error.auth

import uoslife.springaccount.common.error.ErrorCode
import uoslife.springaccount.common.error.baseexception.InvalidValueException

class InvalidTokenException : InvalidValueException(ErrorCode.INVALID_TOKEN_VALUE)