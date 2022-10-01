package com.yuk.school.teacher

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.given

@ExtendWith(MockitoExtension::class)
class TeacherServiceTest {
    @Mock
    private lateinit var teacherRepository: TeacherRepository

    @InjectMocks
    private lateinit var teacherService: TeacherService

    @Test
    fun `선생님 신규 생성`() {
        runBlocking {
            given(teacherRepository.save(any())).willReturn(
                Teacher("선생님")
            )

            teacherService.save("선생님")
        }
    }

    @Test
    fun `선생님 조회`() {
        runBlocking {
            given(teacherRepository.getOrNull(any())).willReturn(
                Teacher("선생님")
            )

            teacherService.get(TeacherId("507f1f77bcf86cd799439011"))
        }
    }
}
