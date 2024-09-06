package uoslife.springaccount.intrastructure.externalservice.sms.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import uoslife.springaccount.app.auth.dto.param.SmsParamDto
import uoslife.springaccount.app.auth.service.AuthService
import uoslife.springaccount.intrastructure.externalservice.sms.SmsFeignClient

@Service
class SmsService(
    private val smsFeignClient: SmsFeignClient,
    @Value("\${spring.profiles.default}") private val env:String,
    @Value("\${sms.api.sender}") private val sender: String,
    @Value("\${sms.api.accessKeyId}") private val accessKeyId: String,
    @Value("\${sms.api.accessKeySecret}") private val accessKeySecret: String
) {
    companion object{
        private val logger = LoggerFactory.getLogger(SmsService::class.java)
    }

    fun sendMessage(
        mobile: String,
        title: String,
        message: String
    ): Boolean {
        if (env=="prod"){
            return sendWithDirectSend(mobile, title, message)
        }

        if (env =="dev"){
            val body = "[ALPHA 환경] $message"
            return sendWithDevSend(mobile, title, body)
        }

        val body = "[${env.uppercase()} 환경] $message"
        return sendWithDevSend(mobile, title, body);

    }

    private fun sendWithDevSend(
        mobile: String,
        subtitle: String,
        message: String
    ): Boolean {
        val title = "💬 $mobile"
        logger.info(title + subtitle + message)
        return true
    }


    private fun sendWithDirectSend(
        mobile: String,
        title: String,
        message: String
    ): Boolean {
        val smsRequest = SmsParamDto.SmsRequest(
            sender = sender,
            username = accessKeyId,
            key = accessKeySecret,
            title = title,
            message = message,
            receiver = listOf(SmsParamDto.Receiver(mobile))
        )

        val result = smsFeignClient.sendSms(smsRequest)

        return result.isSuccess()
    }
}