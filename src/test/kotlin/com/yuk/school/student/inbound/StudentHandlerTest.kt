package com.yuk.school.student.inbound

import com.yuk.school.classroom.ClassRoomId
import com.yuk.school.getObjectId
import com.yuk.school.student.Student
import com.yuk.school.student.StudentService
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient

@WebFluxTest(StudentHandler::class)
class StudentHandlerTest {
    @Autowired
    private lateinit var webTestClient: WebTestClient

    @MockBean
    private lateinit var studentService: StudentService

    @Test
    fun `학생 등록`() {
        runBlocking {
            given(studentService.save(anyString(), any())).willReturn(
                Student("학생", ClassRoomId.fromString(getObjectId()))
            )
        }

        val command = StudentCreateCommand("학생", getObjectId())

        webTestClient.post().uri("/student")
            .bodyValue(command)
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun `학생 조회`() {
        runBlocking {
            given(studentService.get(any())).willReturn(
                Student("학생", ClassRoomId.fromString(getObjectId()))
            )
        }

        webTestClient.get().uri("/student/{id}", "507f1f77bcf86cd799439011")
            .exchange()
            .expectStatus().isOk
    }
}
