package uoslife.springaccount.error

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.restdocs.ManualRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.request
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.snippet.Attributes.key
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.web.context.WebApplicationContext
import uoslife.springaccount.common.error.ErrorCode
import uoslife.springaccount.global.base.ControllerDescribeSpec
import uoslife.springaccount.global.utils.restdocs.dsl.andDocument
import uoslife.springaccount.global.utils.restdocs.dsl.restDocMockMvcBuild
import uoslife.springaccount.global.utils.restdocs.snippet.CustomResponseFieldsSnippet

@WebMvcTest(controllers = [ErrorCodeController::class])
internal class ErrorCodeControllerTest(private val context: WebApplicationContext) :
    ControllerDescribeSpec({
        val restDocumentation = ManualRestDocumentation()

        val mockMvc = restDocMockMvcBuild(context, restDocumentation)
        beforeEach { restDocumentation.beforeTest(javaClass, it.name.testName) }
        afterEach { restDocumentation.afterTest() }

        fun fieldDescriptors(): List<FieldDescriptor> {
            val fieldDescriptors = mutableListOf<FieldDescriptor>()
            for (errorCode in ErrorCode.entries) {
                val fieldDescriptor =
                    fieldWithPath(errorCode.name)
                        .description("에러 코드")
                        .type("STRING")
                        .description("코드")
                        .type("STRING")
                        .description("메시지")
                        .type("NUMBER")
                        .description("상태")
                        .type("STRING")
                        .description("상태 코드")
                        .attributes(
                            key("code").value(errorCode.code),
                            key("message").value(errorCode.message),
                            key("statusCode").value(errorCode.status),
                            key("status").value(HttpStatus.valueOf(errorCode.status))
                        )
                fieldDescriptors.add(fieldDescriptor)
            }
            return fieldDescriptors.toList()
        }

        fun customResponseFields(
            snippetFilePrefix: String,
            fieldDescriptors: List<FieldDescriptor>
        ): CustomResponseFieldsSnippet {
            return CustomResponseFieldsSnippet(snippetFilePrefix, fieldDescriptors, true)
        }

        describe("ErrorCodeController") {
            it("findEnums") {
                val request = request(HttpMethod.GET, "/error-code").contentType("application/json")

                mockMvc
                    .perform(request)
                    .andExpect(status().isOk)
                    .andDocument(
                        "errorcode-response",
                        customResponseFields("errorcode-response", fieldDescriptors())
                    )
            }
        }
    })
