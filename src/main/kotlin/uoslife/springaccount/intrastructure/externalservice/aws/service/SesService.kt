package uoslife.springaccount.intrastructure.externalservice.aws.service

import org.redisson.api.RedissonClient
import org.springframework.cache.CacheManager
import org.springframework.stereotype.Service
import software.amazon.awssdk.services.ses.SesClient
import software.amazon.awssdk.regions.Region
import uoslife.springaccount.intrastructure.externalservice.aws.AwsConfig
import java.time.Instant
import java.util.concurrent.TimeUnit

@Service
class SesService(
    private val stsService: StsService,
    private val cacheManager : RedissonClient
) {

    fun getAssumedClient(): SesClient {
        val key = "urn:uoslife:assumed-clients:ses"
        val region = Region.AP_NORTHEAST_2
        val cache = cacheManager.getBucket<SesClient>(key)

        val cachedClient = cache.get()
        if (cachedClient != null) {
            return cachedClient
        }

        val credentials = stsService.getAssumedRoleSession(
            roleArn = AwsConfig.SES.ROLE_ARN,
            roleSessionName = AwsConfig.SES.ROLE_SESSION_NAME
        )

        val sesClient = SesClient.builder()
            .region(region)
            .credentialsProvider { credentials }
            .build()

        cache.set(sesClient, AwsConfig.SESSION_TTL, TimeUnit.SECONDS)


        return sesClient
    }
}
