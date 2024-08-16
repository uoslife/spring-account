package uoslife.springaccount.common.error.auth

import uoslife.springaccount.common.error.ErrorCode
import uoslife.springaccount.common.error.baseexception.EntityNotFoundException

class OtpNotFoundException : EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND)