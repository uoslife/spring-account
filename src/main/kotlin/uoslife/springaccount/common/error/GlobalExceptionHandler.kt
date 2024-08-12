package uoslife.springaccount.common.error

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import uoslife.springaccount.common.error.baseexception.AccessDeniedException
import uoslife.springaccount.common.error.baseexception.BusinessException
import uoslife.springaccount.common.error.baseexception.EntityNotFoundException
import uoslife.springaccount.common.error.response.ErrorResponse

@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(BusinessException::class)
    protected fun handleBusinessException(
        exception: BusinessException,
    ): ResponseEntity<ErrorResponse> {
        logger.error(exception.errorCode.code, exception)
        val errorCode = exception.errorCode
        val response = ErrorResponse(errorCode)
        return ResponseEntity(response, HttpStatus.valueOf(errorCode.status))
    }

    @ExceptionHandler(EntityNotFoundException::class)
    protected fun handleEntityNotFoundException(
        exception: EntityNotFoundException,
    ): ResponseEntity<ErrorResponse> {
        logger.error(exception.errorCode.code, exception)
        val errorCode = exception.errorCode
        val response = ErrorResponse(errorCode)
        return ResponseEntity(response, HttpStatus.valueOf(errorCode.status))
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    protected fun handleHttpRequestMethodNotSupportedException(
        exception: HttpRequestMethodNotSupportedException?,
    ): ResponseEntity<ErrorResponse> {
        logger.error("HttpRequestMethodNotSupportedException", exception)
        val errorCode = ErrorCode.METHOD_NOT_ALLOWED
        val response = ErrorResponse(errorCode)
        return ResponseEntity(response, HttpStatus.valueOf(errorCode.status))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun handleMethodArgumentNotValidException(
        exception: MethodArgumentNotValidException?,
    ): ResponseEntity<ErrorResponse> {
        logger.error("MethodArgumentTypeMismatchException", exception)
        val errorCode = ErrorCode.INVALID_INPUT_VALUE
        val response = ErrorResponse(errorCode)
        return ResponseEntity(response, HttpStatus.valueOf(errorCode.status))
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    protected fun handleHttpMessageNotReadableException(
        exception: HttpMessageNotReadableException?,
    ): ResponseEntity<ErrorResponse> {
        logger.error("HttpMessageNotReadableException", exception)
        val errorCode = ErrorCode.INVALID_INPUT_VALUE
        val response = ErrorResponse(errorCode)
        return ResponseEntity(response, HttpStatus.valueOf(errorCode.status))
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleHttpMessageNotReadableException(
        exception: MethodArgumentTypeMismatchException
    ): ResponseEntity<ErrorResponse> {
        logger.error("MethodArgumentTypeMismatchException", exception)
        val errorCode = ErrorCode.INVALID_INPUT_VALUE
        val response = ErrorResponse(errorCode)
        return ResponseEntity(response, HttpStatus.valueOf(errorCode.status))
    }

    @ExceptionHandler(Exception::class)
    protected fun handleException(
        exception: Exception,
    ): ResponseEntity<ErrorResponse> {
        logger.error("Exception", exception)
        val errorCode = ErrorCode.INTERNAL_SERVER_ERROR
        val response = ErrorResponse(errorCode)
        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun handleAuthenticationException(
        exception: AccessDeniedException,
    ): ResponseEntity<ErrorResponse> {
        logger.error(exception.errorCode.code, exception)
        val errorCode = exception.errorCode
        val response = ErrorResponse(errorCode)
        return ResponseEntity(response, HttpStatus.valueOf(errorCode.status))
    }
}
