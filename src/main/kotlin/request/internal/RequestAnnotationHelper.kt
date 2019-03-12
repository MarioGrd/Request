package request.internal

/**
* Request annotation helper.
*
* Finds a specific annotation for a given request.
*/
class RequestAnnotationHelper {
    companion object {
        /**
         * Finds an annotation in a safe way.
         *
         * Tries to find a {TAnnotation} on a request. If annotation is not found, it will throw an exception.
         *
         * @param request Request with annotation.
         * @throws AnnotationNotFoundException when {TAnnotation} on a request is not found.
         * @return {TAnnotation} for a given request.
         * */
        inline fun <reified TAnnotation> findAnnotationSafe(request: BaseRequest) : TAnnotation {
            val annotation =
                request::class.java.annotations.find { it -> it.annotationClass.simpleName == TAnnotation::class.simpleName }
                    ?: throw AnnotationNotFoundException("Annotation of type ${TAnnotation::class.simpleName} for request $request not found.".trimMargin())

            return annotation as TAnnotation
        }
    }
}

