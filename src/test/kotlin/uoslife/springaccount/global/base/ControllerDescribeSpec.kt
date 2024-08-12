package uoslife.springaccount.global.base

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.kotest.core.spec.style.DescribeSpec
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.payload.ResponseFieldsSnippet
import org.springframework.test.context.web.WebAppConfiguration
import uoslife.springaccount.global.utils.restdocs.dsl.OBJECT
import uoslife.springaccount.global.utils.restdocs.dsl.RestDocsField
import uoslife.springaccount.global.utils.restdocs.dsl.STRING
import uoslife.springaccount.global.utils.restdocs.dsl.responseBody
import uoslife.springaccount.global.utils.restdocs.dsl.type

@ExtendWith(RestDocumentationExtension::class)
@WebAppConfiguration
abstract class ControllerDescribeSpec(body: DescribeSpec.() -> Unit) : DescribeSpec(body) {
    companion object {
        private val mapper: ObjectMapper =
            ObjectMapper()
                .registerModule(JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)

        fun toJson(value: Any): String {
            return mapper.writeValueAsString(value)
        }

        fun <T> any(type: Class<T>): T = Mockito.any(type)

        fun envelopeResponseBody(
            vararg fields: RestDocsField,
            dataOptional: Boolean = false
        ): ResponseFieldsSnippet {
            return responseBody(
                    "code" type STRING means "응답 코드",
                    "message" type STRING means "응답 메시지",
                    "data" type OBJECT means "응답 데이터" isOptional dataOptional
                )
                .and(fields.map { it.descriptor })
        }
    }
}
