package com.yuk.school.subject.inbound

import com.yuk.school.subject.Subject
import com.yuk.school.subject.SubjectService
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

@WebFluxTest(SubjectHandler::class)
class SubjectHandlerTest {
    @Autowired
    private lateinit var webTestClient: WebTestClient

    @MockBean
    private lateinit var subjectService: SubjectService

    @Test
    fun `과목 생성`() {
        runBlocking {
            given(subjectService.save(anyString())).willReturn(
                Subject("과목")
            )
        }

        val command = SubjectCreateCommand("과목")

        webTestClient.post().uri("/subject")
            .bodyValue(command)
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun `과목 조회`() {
        runBlocking {
            given(subjectService.get(any())).willReturn(
                Subject("과목")
            )
        }

        webTestClient.get()
            .uri("/subject/{id}", "507f1f77bcf86cd799439011")
            .exchange()
            .expectStatus().isOk
            .expectBody<Subject>()
    }
}
