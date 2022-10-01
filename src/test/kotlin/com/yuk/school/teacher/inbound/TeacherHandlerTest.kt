package com.yuk.school.teacher.inbound

import com.yuk.school.teacher.Teacher
import com.yuk.school.teacher.TeacherService
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

@WebFluxTest(TeacherHandler::class)
class TeacherHandlerTest {
    @MockBean
    private lateinit var teacherService: TeacherService

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun `선생님 신규 추가`() {
        runBlocking {
            given(teacherService.save(anyString())).willReturn(
                Teacher("선생님")
            )
        }

        val command = TeacherCreateCommand("선생님")

        webTestClient.post().uri("/teacher")
            .bodyValue(command)
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun `선생님 조회`() {
        runBlocking {
            given(teacherService.get(any())).willReturn(
                Teacher("선생님")
            )
        }

        webTestClient.get()
            .uri("/teacher/{id}", "507f1f77bcf86cd799439011")
            .exchange()
            .expectStatus().isOk
            .expectBody<Teacher>()
    }
}
