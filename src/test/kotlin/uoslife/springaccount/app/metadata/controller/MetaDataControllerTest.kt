package uoslife.springaccount.app.metadata.controller

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpMethod
import org.springframework.restdocs.ManualRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.request
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.web.context.WebApplicationContext
import uoslife.springaccount.app.metadata.domain.Realm
import uoslife.springaccount.app.metadata.dto.response.RealmProfileResponse
import uoslife.springaccount.app.metadata.service.MetaDataService
import uoslife.springaccount.global.base.ControllerDescribeSpec
import uoslife.springaccount.global.utils.restdocs.dsl.*

@WebMvcTest(controllers = [MetaDataController::class])
internal class MetaDataControllerTest(
    @MockkBean(relaxed = true) private val metaDataService: MetaDataService,
    @Autowired private val context: WebApplicationContext
) :
    ControllerDescribeSpec({
        val basePath = "/v2/meta-data"
        val restDocumentation = ManualRestDocumentation()
        val mockMvc = restDocMockMvcBuild(context, restDocumentation)

        beforeEach { restDocumentation.beforeTest(javaClass, it.name.testName) }
        afterEach { restDocumentation.afterTest() }

        describe("GET $basePath") {
            context("Realm 정보를 조회한다") {
                val response =
                    Realm.entries.map {
                        RealmProfileResponse(
                            code = it.code,
                            name = it.koreanName,
                            allowedDomains = it.allowedDomains
                        )
                    }

                val request = request(HttpMethod.GET, basePath).contentType("application/json")

                it("it should be 200") {
                    every { metaDataService.getRealms() } returns response

                    mockMvc
                        .perform(request)
                        .andExpect(status().isOk)
                        .andDocument(
                            "meta-data-get-realms",
                            responseBody(
                                "[].code" type STRING means "Realm 코드",
                                "[].name" type STRING means "Realm 이름",
                                "[].allowedDomains" type ARRAY means "허용 도메인"
                            )
                        )
                }
            }
        }
    })
