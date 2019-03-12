package request

interface RequestBus {
    fun<TResponse> sendRequest(request: Request<TResponse>): TResponse
}