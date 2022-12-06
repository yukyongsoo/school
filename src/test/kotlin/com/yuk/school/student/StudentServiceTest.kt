package com.yuk.school.student

import com.yuk.school.classroom.ClassRoomId
import com.yuk.school.getObjectId
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.given

@ExtendWith(MockitoExtension::class)
class StudentServiceTest {
    @Mock
    private lateinit var studentRepository: StudentRepository

    @InjectMocks
    private lateinit var studentService: StudentService

    @Test
    fun `학생 신규 등록`() {
        runBlocking {
            given(studentRepository.new(any())).willReturn(
                Student("학생", ClassRoomId.fromString(getObjectId()))
            )

            studentService.save("학생", ClassRoomId.fromString(getObjectId()))
        }
    }

    @Test
    fun `학생 조회`() {
        runBlocking {
            given(studentRepository.getOrNull(any())).willReturn(
                Student("학생", ClassRoomId.fromString(getObjectId()))
            )

            studentService.get(StudentId("507f1f77bcf86cd799439011"))
        }
    }
}
