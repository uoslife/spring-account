package uoslife.springaccount.common.domain.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import java.time.Duration
import org.redisson.api.RedissonClient
import org.redisson.spring.data.connection.RedissonConnectionFactory
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
@EnableCaching
class RedisCacheConfig(
    private val redissonClient: RedissonClient,
) {
    @Bean
    fun cacheManager(): CacheManager {
        val objectMapper =
            ObjectMapper()
                .registerModule(JavaTimeModule())
                .activateDefaultTyping(
                    BasicPolymorphicTypeValidator.builder()
                        .allowIfBaseType(Any::class.java)
                        .build(),
                    ObjectMapper.DefaultTyping.EVERYTHING
                )

        val redisCacheConfiguration: RedisCacheConfiguration =
            RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(
                    RedisSerializationContext.SerializationPair.fromSerializer(
                        StringRedisSerializer()
                    )
                )
                .serializeValuesWith(
                    RedisSerializationContext.SerializationPair.fromSerializer(
                        GenericJackson2JsonRedisSerializer(objectMapper)
                    )
                )
                .entryTtl(Duration.ofMinutes(3L))

        return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(
                RedissonConnectionFactory(redissonClient)
            )
            .cacheDefaults(redisCacheConfiguration)
            .build()
    }
}
