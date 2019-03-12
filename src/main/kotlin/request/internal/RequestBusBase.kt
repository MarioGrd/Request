package request.internal

import request.*
/**
 * Base request bus implementation.
 * **/
@Suppress("UNCHECKED_CAST")
abstract class RequestBusBase  {
    /**
     * Tries to find handler for a given request.
     *
     * @param handlerResolver Delegate function for instantiation.
     * @param request Given request.
     * @return Handler for a given request.
     * @throws HandlerNotValidException
     * @throws HandlerNotFoundException
     * **/
    protected inline fun <reified THandler, TResponse> handler(crossinline handlerResolver: HandlerResolver, request: Request<TResponse>): THandler {

        return this.handle {

            val handlerClass = RequestAnnotationHelper.findAnnotationSafe<HasRequestHandler>(request).handler.java

            if (!handlerClass.interfaces.any{it.name == RequestHandler::class.java.name}) {
                throw HandlerNotValidException("Unable to find correct handler of type '${RequestHandler::class.java}' for '${request::class.simpleName}' request. Did you use correct handler?")
            }

            handlerResolver.invoke(handlerClass.simpleName.decapitalize()) as? THandler
                ?: throw HandlerNotFoundException("Unable to find handler of type '${RequestHandler::class.java}' for '${request::class.simpleName}' request. Did you forget to register your handler?")
        }
    }
    protected inline fun <reified THandler> handle(f: () -> THandler): THandler {
        try {
            return f()
        } catch (e: AnnotationNotFoundException) {
            throw CqrsException(
                e.msg,
                CqrsExceptionType.ANNOTATION_NOT_FOUND
            )
        } catch (e: HandlerNotFoundException) {
            throw CqrsException(
                e.msg,
                CqrsExceptionType.HANDLER_NOT_FOUND
            )
        } catch (e: HandlerNotValidException) {
            throw CqrsException(
                e.msg,
                CqrsExceptionType.HANDLER_NOT_VALID
            )
        }
    }
}
