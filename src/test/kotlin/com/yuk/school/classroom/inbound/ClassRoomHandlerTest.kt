package com.yuk.school.classroom.inbound

import com.yuk.school.classroom.ClassRoom
import com.yuk.school.classroom.ClassRoomService
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import reactor.core.publisher.Mono

@WebFluxTest(ClassRoomHandler::class)
class ClassRoomHandlerTest {
    @Autowired
    private lateinit var webTestClient: WebTestClient

    @MockBean
    private lateinit var classRoomService: ClassRoomService

    @Test
    fun `신규 반 생성`() {
        runBlocking {
            given(classRoomService.save()).willReturn(
                ClassRoom(1, "1")
            )
        }

        webTestClient.post()
            .uri("/classroom")
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun `반 조회`() {
        runBlocking {
            given(classRoomService.get(any())).willReturn(
               ClassRoom(1, "1")
            )
        }

        webTestClient.get()
            .uri("/classroom/{id}", "507f1f77bcf86cd799439011")
            .exchange()
            .expectStatus().isOk
            .expectBody<ClassRoom>()
    }
}