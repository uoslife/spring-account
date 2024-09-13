package uoslife.springaccount.intrastructure.externalservice.sms

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import uoslife.springaccount.app.auth.dto.param.SmsParamDto

@FeignClient(name = "smsFeignClient", url = "\${sms.api.endpoint}")
interface SmsFeignClient {

    @PostMapping
    fun sendSms(@RequestBody smsRequest: SmsParamDto.SmsRequest): SmsParamDto.SmsSendResult
}
