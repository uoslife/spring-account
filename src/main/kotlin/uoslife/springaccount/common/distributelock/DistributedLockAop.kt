package uoslife.springaccount.common.distributelock

import io.github.oshai.kotlinlogging.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.redisson.api.RLock
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Aspect
@Component
class DistributedLockAop(
    private val redissonClient: RedissonClient,
    private val aopForTransaction: AopForTransaction
) {

    companion object {
        private const val REDISSON_LOCK_PREFIX = "LOCK:"
    }

    @Around("@annotation(uoslife.springaccount.common.distributelock.DistributedLock)")
    fun lock(joinPoint: ProceedingJoinPoint): Any? {
        val signature = joinPoint.signature as MethodSignature
        val method = signature.method

        val distributedLock = method.getAnnotation(DistributedLock::class.java) ?: return null

        val key =
            REDISSON_LOCK_PREFIX +
                CustomSpringELParser.getDynamicValue(
                    signature.parameterNames,
                    joinPoint.args,
                    distributedLock.key
                )
        val rLock: RLock = redissonClient.getLock(key) // (1)

        try {
            val available =
                rLock.tryLock(
                    distributedLock.waitTime,
                    distributedLock.leaseTime,
                    distributedLock.timeUnit
                ) // (2)
            if (!available) {
                return false
            }

            return aopForTransaction.proceed(joinPoint) // (3)
        } catch (e: InterruptedException) {
            throw e
        } finally {
            try {
                rLock.unlock() // (4)
            } catch (e: IllegalMonitorStateException) {
                logger.info {
                    "Redisson Lock Already UnLock {} {}" +
                        kv("serviceName", method.name) +
                        kv("key", key)
                }
            }
        }
    }

    private fun kv(key: String, value: Any): String {
        return "$key=$value"
    }
}
