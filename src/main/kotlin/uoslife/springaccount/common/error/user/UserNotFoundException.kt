package uoslife.springaccount.common.error.user

import uoslife.springaccount.common.error.ErrorCode
import uoslife.springaccount.common.error.baseexception.EntityNotFoundException

class UserNotFoundException : EntityNotFoundException(ErrorCode.USER_NOT_FOUND) {}
