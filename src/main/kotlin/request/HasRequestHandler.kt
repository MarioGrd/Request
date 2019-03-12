package request

import kotlin.reflect.KClass

/**
 * Annotation for assigning appropriate handler to a request.
 *
 * Annotation for a {Request} pointing to its {RequestHandler<Request<TResponse>, TResponse>}.
 *
 * @param handler Handler of type {RequestHandler<Request<TResponse>, TResponse>} responsable for handling a request sent through {RequestBus}.
 * */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class HasRequestHandler(val handler: KClass<*>)