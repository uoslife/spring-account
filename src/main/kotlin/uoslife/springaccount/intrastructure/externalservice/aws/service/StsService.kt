package uoslife.springaccount.intrastructure.externalservice.aws.service

import org.redisson.api.RedissonClient
import org.slf4j.LoggerFactory
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import software.amazon.awssdk.auth.credentials.AwsCredentials
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sts.StsClient
import software.amazon.awssdk.services.sts.model.AssumeRoleRequest
import software.amazon.awssdk.services.sts.model.AssumeRoleResponse
import uoslife.springaccount.intrastructure.externalservice.aws.AwsConfig
import java.util.concurrent.TimeUnit

@Service
class StsService(
    private val cacheManager: RedissonClient,
    private val stsClient: StsClient
) {

    private val logger = LoggerFactory.getLogger(StsService::class.java)

    fun getAssumedRoleSession(roleArn: String, roleSessionName: String): AwsCredentials {
        val key = "urn:uoslife:assumed-roles:$roleArn:$roleSessionName"

        // 캐시에서 자격 증명 조회
        val cached = cacheManager.getBucket<AwsCredentials>(key)
        if (cached.get() != null) {
            return cached.get()
        }

        // AssumeRole 요청 및 자격 증명 생성
        val assumeRoleRequest = AssumeRoleRequest.builder()
            .roleArn(roleArn)
            .roleSessionName(roleSessionName)
            .durationSeconds(3600) // TTL 또는 다른 설정
            .build()

        val assumeRoleResponse: AssumeRoleResponse = stsClient.assumeRole(assumeRoleRequest)
        val credentials = assumeRoleResponse.credentials()

        val result = AwsSessionCredentials.create(
            credentials.accessKeyId(),
            credentials.secretAccessKey(),
            credentials.sessionToken()
        )

        // 자격 증명 캐시 저장
        cached.set(result, AwsConfig.SESSION_TTL, TimeUnit.SECONDS)

        logger.info("Session is Created - ${result.accessKeyId()} (~${credentials.expiration()}")

        return result
    }
}
