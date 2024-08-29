package uoslife.springaccount.intrastructure.externalservice.slack

import com.slack.api.bolt.App
import com.slack.api.bolt.AppConfig
import com.slack.api.methods.SlackApiException
import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.slf4j.LoggerFactory
import java.io.IOException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class SlackService(
    @Value("\${slack.bot-token}") private val botToken: String,
    @Value("\${slack.signing-secret}") private val signingSecret: String
) {
    companion object{
        private val logger = LoggerFactory.getLogger(SlackService::class.java)
    }

    private lateinit var app: App

    @PostConstruct
    fun init() {
        val appConfig =
            AppConfig.builder()
                .singleTeamBotToken(botToken) // Bot token 설정
                .signingSecret(signingSecret)
                .build()

        app = App(appConfig)
    }

    @PreDestroy
    fun shutdown() {
        app.stop() // 애플리케이션 종료 시 Slack 앱을 안전하게 중지
    }

    fun sendMessage( text: String, channel: String) {
        try {
            val response =
                app.client().chatPostMessage { req ->
                    req.token(botToken) // API 호출 시 Bot token 사용
                        .channel(channel)
                        .text(text)
                }
            if (!response.isOk) {
                logger.error("Error sending message: ${response.error}")
            } else {
                logger.info("Message sent to $channel")
            }
        } catch (e: IOException) {
            logger.error("IO Exception: $e")
        } catch (e: SlackApiException) {
            logger.error("Slack API Exception: $e")
        }
    }
}
