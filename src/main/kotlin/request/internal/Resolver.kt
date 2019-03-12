package request.internal

import request.Request
import request.RequestHandler

/**
 * Resolve delegate for getting instances of a {RequestHandler<Request<TResponse>, TResponse>}.
 * **/
internal typealias HandlerResolver = (name: String) -> RequestHandler<Request<Any>, Any>


