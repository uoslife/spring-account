package uoslife.springaccount.common.domain.annotation

import org.springframework.core.annotation.AliasFor
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

/**
 * Indicates that an annotated class is a "Writer" (e.g. a data access object).
 *
 * <p>This annotation serves as a specialization of {@link Component @Component}, allowing for
 * implementation classes to be autodetected through classpath scanning.
 *
 * @see Component
 * @see Repository
 * @author komment
 */
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Component
annotation class Writer(
    /** Alias for {@link Component#value}. */
    @get:AliasFor(annotation = Component::class) val value: String = ""
)
