package request

interface PostRequestHandler<in TRequest : Request<TResponse>, TResponse> {
    fun handle(request: TRequest)
}