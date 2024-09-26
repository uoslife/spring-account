package uoslife.springaccount.infrastructure

import io.kotest.core.spec.style.DescribeSpec
import uoslife.springaccount.intrastructure.externalservice.slack.SlackService

class MyClassTest :
    DescribeSpec({
        describe("MyClass initialization") {
            it("should initialize app correctly when init is called") {
                // Given
                val botToken = "testBotToken"
                val signingSecret = "testSigningSecret"
                val slackService = SlackService(botToken, signingSecret)

                // When
                slackService.init() // @PostConstruct로 자동 호출되는 메서드를 직접 호출

                // Then
                slackService.sendMessage("Send Test Message", "U06S3R47NQM")
            }
        }
    })
