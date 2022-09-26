package com.yuk.school.classroom.inbound

import com.yuk.school.classroom.ClassRoomId
import com.yuk.school.classroom.ClassRoomService
import io.swagger.v3.oas.annotations.Operation
import org.springdoc.core.annotations.RouterOperation
import org.springdoc.core.annotations.RouterOperations
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait
import org.springframework.web.reactive.function.server.coRouter
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono

@Component
class ClassRoomHandler(
    private val classRoomService: ClassRoomService
) {
    @Bean
    @RouterOperations(
        RouterOperation(path = "/classroom", beanMethod = "save", beanClass = ClassRoomHandler::class),
        RouterOperation(path = "/classroom/{id}", beanMethod = "get", beanClass = ClassRoomHandler::class),
    )
    fun route() = coRouter {
        "/classroom".nest {
            POST("", ::save)
            GET("/{id}", ::get)
        }
    }


    suspend fun save(request: ServerRequest): ServerResponse {
        classRoomService.save()

        return ServerResponse.ok().buildAndAwait()
    }

    suspend fun get(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id")
        val classId = ClassRoomId.fromString(id)

        val classRoom = classRoomService.get(classId)
        return ServerResponse.ok().bodyValueAndAwait(classRoom)
    }
}
