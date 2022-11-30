package com.yuk.school.classroom

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.given

@ExtendWith(MockitoExtension::class)
class ClassRoomServiceTest {
    @Mock
    private lateinit var classRoomRepository: ClassRoomRepository

    @InjectMocks
    private lateinit var classRoomService: ClassRoomService

    @Test
    fun `신규 반 생성`() {
        runBlocking {
            given(classRoomRepository.new(any())).willReturn(
                ClassRoom(1, "1")
            )

            val saved = classRoomService.save(1, "1")

            assertThat(saved.grade).isEqualTo(1)
            assertThat(saved.name).isEqualTo("1")
        }
    }

    @Test
    fun `반 정보 조회`() {
        runBlocking {
            given(classRoomRepository.getOrNull(any())).willReturn(
                ClassRoom(1, "1")
            )

            val classRoom = classRoomService.get(ClassRoomId.fromString("507f1f77bcf86cd799439011"))

            assertThat(classRoom.grade).isEqualTo(1)
            assertThat(classRoom.name).isEqualTo("1")
        }
    }
}
