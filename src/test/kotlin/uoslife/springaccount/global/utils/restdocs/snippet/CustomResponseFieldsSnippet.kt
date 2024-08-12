package uoslife.springaccount.global.utils.restdocs.snippet

import org.springframework.http.MediaType
import org.springframework.restdocs.operation.Operation
import org.springframework.restdocs.payload.AbstractFieldsSnippet
import org.springframework.restdocs.payload.FieldDescriptor

open class CustomResponseFieldsSnippet(
    type: String,
    descriptors: List<FieldDescriptor>,
    ignoreUndocumentedFields: Boolean
) : AbstractFieldsSnippet(type, descriptors, null, ignoreUndocumentedFields) {

    override fun getContentType(operation: Operation): MediaType? {
        return operation.response.headers?.contentType
    }

    override fun getContent(operation: Operation): ByteArray {
        return operation.response.content ?: ByteArray(0)
    }
}
