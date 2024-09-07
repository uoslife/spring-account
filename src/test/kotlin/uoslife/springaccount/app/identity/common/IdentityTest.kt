package uoslife.springaccount.app.identity.common

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.redisson.api.RedissonClient
import org.springframework.boot.test.context.SpringBootTest
import uoslife.springaccount.app.identity.domain.entity.Identity
import uoslife.springaccount.app.identity.domain.repository.jpa.IdentityRepository
import uoslife.springaccount.app.identity.service.IdentityService
import uoslife.springaccount.app.user.domain.entity.User

@SpringBootTest
abstract class IdentityTest {

    val identityService = mockk<IdentityService>()
    val identityRepository = mockk<IdentityRepository>()
    val redissonClient = mockk<RedissonClient>()
//    @MockK
//    protected lateinit var identityService: IdentityService
//
//    @MockK
//    protected lateinit var identityRepository: IdentityRepository

    protected val userDummyData1 = User(
        name = "김영찬",
        phoneNumber = "010-2064-6347",
        email = "aacz1203@uos.ac.kr",
        nickname = "kyc",
        birthday = "1999-12-03",
        avatarUrl = null,
    )

    protected val identityDummyData1 = Identity(
        user = userDummyData1,
        type = "Student",
        status = "Active",
        idNumber = "123456789",
        university = "Seoul National University",
        universityCode = "SNU",
        department = "Computer Science",
        departmentCode = "CS",
        major = null,
        majorCode = null,
        isActive = false
    )

    protected val identityDummyData2 = Identity(
        user = userDummyData1,
        type = "Alumnus",
        status = "Inactive",
        idNumber = "987654321",
        university = "Korea University",
        universityCode = "KU",
        department = "Business Administration",
        departmentCode = "BA",
        major = "Marketing",
        majorCode = "MK",
        isActive = false
    )

    protected val identityDummyData3 = Identity(
        user = userDummyData1,
        type = "Student",
        status = "Active",
        idNumber = "111223344",
        university = "Yonsei University",
        universityCode = "YU",
        department = "Electrical Engineering",
        departmentCode = "EE",
        major = "Robotics",
        majorCode = "RO",
        isActive = false
    )

    companion object {
        @BeforeAll
        @JvmStatic
        fun init() {
            MockKAnnotations.init(this)
        }
    }

    @BeforeEach
    fun setUp() {
        // Mocking repository의 동작 정의
        every { userDummyData1.id?.let { identityRepository.findIdentitiesByUserId(it) } } returns listOf(identityDummyData1, identityDummyData2, identityDummyData3)

//        every { userService.findUser(any()) } answers {
//            val userId = firstArg<UUID>()
//            if (userId == user1.id) {
//                user1
//            } else {
//                user2
//            }
//        }
    }
}