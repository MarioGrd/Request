package request

interface PreRequestHandler<in TRequest : Request<TResponse>, TResponse> {
    fun handle(request: TRequest)
}