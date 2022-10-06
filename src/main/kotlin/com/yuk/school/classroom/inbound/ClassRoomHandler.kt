package com.yuk.school.classroom.inbound

import com.yuk.school.classroom.ClassRoomId
import com.yuk.school.classroom.ClassRoomService
import com.yuk.school.classroom.TimeTableService
import org.springdoc.core.annotations.RouterOperation
import org.springdoc.core.annotations.RouterOperations
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait
import org.springframework.web.reactive.function.server.coRouter

@Component
class ClassRoomHandler(
    private val classRoomService: ClassRoomService,
    private val timeTableService: TimeTableService
) {
    @Bean("classRoomRouter")
    @RouterOperations(
        RouterOperation(path = "/classroom", beanMethod = "save", beanClass = ClassRoomHandler::class),
        RouterOperation(path = "/classroom/{id}", beanMethod = "get", beanClass = ClassRoomHandler::class),
        RouterOperation(path = "/classroom/{id}/lesson", beanMethod = "addLesson", beanClass = ClassRoomHandler::class),
        RouterOperation(path = "/classroom/{id}/lesson", beanMethod = "getTimeTable", beanClass = ClassRoomHandler::class)
    )
    fun route() = coRouter {
        "/classroom".nest {
            POST("", ::save)
            GET("/{id}", ::get)
            POST("/{id}/lesson", ::addLesson)
            GET("/{id}/lesson", ::getTimeTable)
        }
    }

    suspend fun save(request: ServerRequest): ServerResponse {
        val command = request.awaitBody<ClassRoomCreateCommand>()

        classRoomService.save(command.grade, command.name)

        return ServerResponse.ok().buildAndAwait()
    }

    suspend fun get(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id")
        val classId = ClassRoomId.fromString(id)

        val classRoom = classRoomService.get(classId)
        return ServerResponse.ok().bodyValueAndAwait(classRoom)
    }

    suspend fun getTimeTable(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id")
        val classId = ClassRoomId.fromString(id)

        val timeTable = timeTableService.get(classId)
        return ServerResponse.ok().bodyValueAndAwait(timeTable)
    }

    suspend fun addLesson(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id")
        val classId = ClassRoomId.fromString(id)

        val command = request.awaitBody<LessonCreateCommand>()

        timeTableService.addLesson(classId, command.toLesson())
        return ServerResponse.ok().buildAndAwait()
    }
}
