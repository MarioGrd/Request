package request

import request.internal.BaseRequest

/**
 * Marker interface to represent a request with a response.
 *
 * Every class implementing this interface can be send through an RequestBus.
 *
 * @param TResponse Response type.
 */
interface Request<out TResponse> : BaseRequest