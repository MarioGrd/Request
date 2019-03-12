package request

import request.internal.HandlerResolver
import request.internal.RequestBusBase
import request.internal.*
import java.util.*

/**
 * The mechanism that dispatches Request objects to:
 * 1. Registered PreRequestHandlers
 * 2. Registered RequestHandler
 * 3. Registered PostRequestHandlers
 *
 * Every request sent through this bus MUST implement {Request} interface and can have only one {RequestHandler} which can be specified
 * by adding {HasRequestHandler} annotation to the request.
 *
 * @param handlerResolver Delegate function which resolves instantiation of Request Handlers.
  * */
class SimpleRequestBus(
    private val handlerResolver: HandlerResolver
) : RequestBusBase(), RequestBus {

    private val preRequestHandlers = Collections.synchronizedCollection(mutableListOf<PreRequestHandler<Request<*>, *>>())
    private val postRequestHandlers = Collections.synchronizedCollection(mutableListOf<PostRequestHandler<Request<*>, *>>())

    /**
     * Sends a request of type {T}.
     *
     * @param request Given request.
     * @return TResponse
     *
     * @throws CqrsException
     **/
    override fun<TResponse> sendRequest(request: Request<TResponse>): TResponse {

        this.preRequestHandlers.forEach {it.handle(request)}

        val response =  handler<RequestHandler<Request<TResponse>, TResponse>, TResponse>(handlerResolver, request).handle(request )

        this.postRequestHandlers.forEach {it.handle(request)}

        return response
    }

    fun setPreRequestHandler(preRequestHandler: PreRequestHandler<Request<*>, *>) : SimpleRequestBus {
        this.preRequestHandlers.add(preRequestHandler)
        return this
    }

    fun setPostRequestHandler(postRequestHandler: PostRequestHandler<Request<*>, *>) : SimpleRequestBus {
        this.postRequestHandlers.add(postRequestHandler)
        return this
    }
}



