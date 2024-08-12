package uoslife.springaccount.app.user.domain.entity

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import uoslife.springaccount.app.device.domain.entity.Device
import uoslife.springaccount.app.user.domain.repository.jpa.UserRepository

@DataJpaTest(showSql = true)
internal class UserEntityTest @Autowired constructor(private val userRepository: UserRepository) :
    DescribeSpec() {

    override fun extensions() = listOf(SpringExtension)

    init {
        describe("UserEntity") {
            context("create") {
                it("create user") {
                    val user =
                        User(
                            name = "test",
                            phoneNumber = "01012345678",
                        )
                    val userSecond =
                        User(
                            name = "test",
                            phoneNumber = "01012345678",
                        )
                    userRepository.save(user)
                    userRepository.save(userSecond)
                    userRepository.flush()
                    val device =
                        Device(
                            user = user,
                            osVersion = "test",
                            os = "test",
                            model = "test",
                            appVersion = "test",
                            codePushVersion = "test",
                        )
                    userRepository.save(user)
                    userRepository.flush()

                    println(user.devices.first().id)
                }
            }
        }
    }
}
