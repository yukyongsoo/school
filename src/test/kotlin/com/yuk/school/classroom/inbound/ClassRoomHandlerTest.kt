package com.yuk.school.classroom.inbound

import com.yuk.school.WebErrorHandler
import com.yuk.school.classroom.ClassRoom
import com.yuk.school.classroom.ClassRoomId
import com.yuk.school.classroom.ClassRoomService
import com.yuk.school.classroom.TimeTable
import com.yuk.school.classroom.TimeTableService
import com.yuk.school.getObjectId
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import java.time.LocalTime

@WebFluxTest(ClassRoomHandler::class)
@Import(WebErrorHandler::class)
class ClassRoomHandlerTest {
    @Autowired
    private lateinit var webTestClient: WebTestClient

    @MockBean
    private lateinit var classRoomService: ClassRoomService

    @MockBean
    private lateinit var timeTableService: TimeTableService

    @Test
    fun `신규 반 생성`() {
        runBlocking {
            given(classRoomService.save(anyInt(), anyString())).willReturn(
                ClassRoom(1, "1")
            )
        }

        val command = ClassRoomCreateCommand(1, "1")

        webTestClient.post()
            .uri("/classroom")
            .bodyValue(command)
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

    @Test
    fun `반 조회 실패`() {
        runBlocking {
            given(classRoomService.get(any())).willThrow(
                RuntimeException()
            )
        }

        webTestClient.get()
            .uri("/classroom/{id}", "507f1f77bcf86cd799439011")
            .exchange()
            .expectStatus().isBadRequest
    }

    @Test
    fun `반 수업 시간 추가`() {
        runBlocking {
            given(timeTableService.addLesson(any(), any())).willReturn(Unit)
        }

        val command = LessonCreateCommand(
            "507f1f77bcf86cd799439011",
            "507f1f77bcf86cd799439011",
            LocalTime.of(13, 0),
            LocalTime.of(14, 0)
        )

        webTestClient.post()
            .uri("/classroom/{id}/lesson", "507f1f77bcf86cd799439011")
            .bodyValue(command)
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun `반 시간표 조회`() {
        runBlocking {
            given(timeTableService.get(any())).willReturn(
                TimeTable.empty(ClassRoomId(getObjectId()))
            )
        }

        webTestClient.get()
            .uri("/classroom/{id}/lesson", "507f1f77bcf86cd799439011")
            .exchange()
            .expectStatus().isOk
            .expectBody<TimeTable>()
    }
}
