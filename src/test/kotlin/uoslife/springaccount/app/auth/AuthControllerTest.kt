import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import org.redisson.api.RBucket
import org.redisson.api.RedissonClient
import uoslife.springaccount.app.auth.dto.param.AuthParamDto
import uoslife.springaccount.app.auth.service.AuthService
import uoslife.springaccount.app.auth.service.JwtService
import uoslife.springaccount.common.error.auth.OtpNotFoundException
import uoslife.springaccount.intrastructure.externalservice.slack.SlackService

class AuthControllerTest :
    BehaviorSpec({
        val redissonClient = mockk<RedissonClient>()
        val slackService = mockk<SlackService>()
        val jwt = mockk<JwtService>()

        val authService = AuthService(redissonClient, jwt, slackService)
        given("a repository with expected value") {
            // RBucket<String>을 모킹
            val mockBucket: RBucket<String> = mockk()

            // 모킹한 RBucket에서 get() 호출 시 반환될 값을 설정
            every { mockBucket.get() } returns "000000"

            // authService.getBucket() 호출 시 모킹한 RBucket을 반환하도록 설정
            every { redissonClient.getBucket<String>("01090068420") } returns mockBucket

            `when`("otp given different value") {
                then("it should throw OTP Not Found Exception") {
                    shouldThrow<OtpNotFoundException> {
                        authService.verifyPhoneAuthSession(
                            AuthParamDto.VerifyPhoneAuthSession("01090068420", "111111")
                        )
                    }
                }
            }
        }
    })
