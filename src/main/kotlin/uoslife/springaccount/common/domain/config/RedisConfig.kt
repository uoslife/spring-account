package uoslife.springaccount.common.domain.config

import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.client.codec.StringCodec
import org.redisson.config.Config
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RedisConfig(
    @Value("\${spring.data.redis.host}") val host: String,
    @Value("\${spring.data.redis.port}") val port: Int,
) {

    @Bean
    fun redissonClient(): RedissonClient {
        val config = Config()
        config.setCodec(StringCodec.INSTANCE).useSingleServer().address = "redis://$host:$port"
        return Redisson.create(config)
    }
}
