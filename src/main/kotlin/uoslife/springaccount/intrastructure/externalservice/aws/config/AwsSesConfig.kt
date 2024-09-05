package uoslife.springaccount.intrastructure.externalservice.aws.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sts.StsClient
import uoslife.springaccount.intrastructure.externalservice.aws.AwsConfig


@Configuration
class AwsSesConfig(
    @Value("\${aws.ses.access-key}") private val accessKey: String,
    @Value("\${aws.ses.secret-key}") private val secretKey: String
) {


    @Bean
    fun stsClient(): StsClient {
        return StsClient.builder()
            .region(Region.AP_NORTHEAST_2)
            .credentialsProvider(
                StaticCredentialsProvider.create(
                    AwsSessionCredentials.create(
                        "your-access-key-id",     // 실제 값으로 변경
                        "your-secret-access-key", // 실제 값으로 변경
                        "your-session-token"      // 실제 값으로 변경
                    )
                )
            )
            .build()
    }
}