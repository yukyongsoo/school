package com.yuk.school.classroom

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

@ExtendWith(MockitoExtension::class)
class ClassRoomServiceTest {
    @Mock
    private lateinit var classRoomRepository: ClassRoomRepository

    @InjectMocks
    private lateinit var classRoomService: ClassRoomService

    @Test
    fun `신규 반 생성`() {
        given(classRoomRepository.save(any())).willReturn(
            Mono.just(ClassRoom(1, "1"))
        )

        StepVerifier.create(classRoomService.save())
            .assertNext {
                assertThat(it.grade).isEqualTo(1)
                assertThat(it.name).isEqualTo("1")
            }
            .verifyComplete()
    }

    @Test
    fun `반 정보 조회`() {
        given(classRoomRepository.get(any())).willReturn(
            Mono.just(ClassRoom(1, "1"))
        )

        StepVerifier.create(classRoomService.get(ClassRoomId.fromString("507f1f77bcf86cd799439011")))
            .assertNext {
                assertThat(it.grade).isEqualTo(1)
                assertThat(it.name).isEqualTo("1")
            }
            .verifyComplete()
    }
}