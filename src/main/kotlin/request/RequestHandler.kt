package request

/**
 * Defines a handler for a request.
 *
 * @param TRequest Type of request being handled.
 * @param TResponse Type of request response.
 * */
interface RequestHandler<in TRequest : Request<TResponse>, TResponse> {

    /**
     * Handles a request.
     *
     * @param request Request being handle.
     * @return returns an response of a request.
     * */
    fun handle(request: TRequest) : TResponse
}

