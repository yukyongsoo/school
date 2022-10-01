package com.yuk.school.subject

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.given

@ExtendWith(MockitoExtension::class)
class SubjectServiceTest {
    @Mock
    private lateinit var subjectRepository: SubjectRepository

    @InjectMocks
    private lateinit var subjectService: SubjectService

    @Test
    fun `과목 생성`() {
        runBlocking {
            given(subjectRepository.save(any())).willReturn(
                Subject("과목")
            )

            subjectService.save("과목")
        }
    }

    @Test
    fun `과목 조회`() {
        runBlocking {
            given(subjectRepository.get(any())).willReturn(
                Subject("과목")
            )

            subjectService.get(SubjectId("507f1f77bcf86cd799439011"))
        }
    }
}
