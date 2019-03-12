package request.internal

import java.lang.Exception

/**
 * Types of exception.
 **/
enum class CqrsExceptionType { ANNOTATION_NOT_FOUND, HANDLER_NOT_FOUND, HANDLER_NOT_VALID }

/**
 * Annotation not found exception.
 * @param msg Exception message.
 * **/
data class AnnotationNotFoundException(val msg: String) : Exception(msg)

/**
 * Handler not found exception.
 *
 * @param msg Exception message.
 * **/
class HandlerNotFoundException(val msg: String): Exception(msg)

/**
 * Handler not valid exception.
 *
 * @param msg Exception message.
 * **/
data class HandlerNotValidException(val msg: String) : Exception(msg)

/**
 * Handler not valid exception.
 *
 * @param msg Exception message.
 * @param type Exception type.
 * **/
data class CqrsException(val msg: String, val type: CqrsExceptionType): Exception(msg)