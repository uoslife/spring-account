package uoslife.springaccount.app.identity.service

import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import uoslife.springaccount.app.identity.common.IdentityTest

class getIdentityListTest : IdentityTest() {

    @Test
    fun `getIdentityList should return identity list for given userId`() {
        // Given
        init();
        val userId = userDummyData1.id ?: throw IllegalArgumentException("User ID cannot be null")

        // When
        val result = identityService.getIdentityList(userId)

        // Then
        assertEquals(3, result?.size) // 더미 데이터에서 3개의 신분 정보가 있어야 합니다.
        assertEquals(identityDummyData1.type, result?.get(0)?.type)
        assertEquals(identityDummyData2.type, result?.get(1)?.type)
        assertEquals(identityDummyData3.type, result?.get(2)?.type)

        // identityRepository의 메서드가 호출되었는지 확인
        verify { identityRepository.findIdentitiesByUserId(userId) }
    }
}
