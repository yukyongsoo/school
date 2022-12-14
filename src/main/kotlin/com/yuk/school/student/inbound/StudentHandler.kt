package com.yuk.school.student.inbound

import com.yuk.school.classroom.ClassRoomId
import com.yuk.school.student.StudentId
import com.yuk.school.student.StudentService
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait
import org.springframework.web.reactive.function.server.coRouter

@Component
class StudentHandler(
    private val studentService: StudentService
) {
    @Bean("studentRouter")
    fun route() = coRouter {
        "/student".nest {
            POST("", ::save)
            GET("/{id}", ::get)
        }
    }

    suspend fun save(request: ServerRequest): ServerResponse {
        val command = request.awaitBody<StudentCreateCommand>()
        val classRoomId = ClassRoomId.fromString(command.classId)

        studentService.save(command.name, classRoomId)

        return ServerResponse.ok().buildAndAwait()
    }

    suspend fun get(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id")
        val studentId = StudentId(id)

        val student = studentService.get(studentId)

        return ServerResponse.ok().bodyValueAndAwait(student)
    }
}
