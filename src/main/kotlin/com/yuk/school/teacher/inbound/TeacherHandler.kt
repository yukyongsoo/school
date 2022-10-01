package com.yuk.school.teacher.inbound

import com.yuk.school.teacher.TeacherId
import com.yuk.school.teacher.TeacherService
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait
import org.springframework.web.reactive.function.server.coRouter

@Component
class TeacherHandler(
    private val teacherService: TeacherService
) {
    @Bean
    fun route() = coRouter {
        "/teacher".nest {
            POST("", ::save)
            GET("/{id}", ::get)
        }
    }

    suspend fun save(request: ServerRequest): ServerResponse {
        val command = request.awaitBody<TeacherCreateCommand>()

        teacherService.save(command.name)

        return ServerResponse.ok().buildAndAwait()
    }

    suspend fun get(request: ServerRequest): ServerResponse {
        val idValue = request.pathVariable("id")
        val teacherId = TeacherId(idValue)

        val teacher = teacherService.get(teacherId)

        return ServerResponse.ok().bodyValueAndAwait(teacher)
    }
}
