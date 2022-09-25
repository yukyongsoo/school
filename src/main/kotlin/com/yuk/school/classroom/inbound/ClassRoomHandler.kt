package com.yuk.school.classroom.inbound

import com.yuk.school.classroom.ClassRoomId
import com.yuk.school.classroom.ClassRoomService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono

@Configuration
class ClassRoomHandler(
    private val classRoomService: ClassRoomService
) {
    @Bean
    fun route() = router {
        "/classroom".nest {
            POST("", ::save)
            GET("/{id}", ::get)
        }
    }

    fun save(request: ServerRequest): Mono<ServerResponse> {
        return classRoomService.save()
            .flatMap {
                ServerResponse.ok().build()
            }
    }

    fun get(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id")
        val classId = ClassRoomId.fromString(id)

        return classRoomService.get(classId)
            .flatMap {
                ServerResponse.ok().bodyValue(it)
            }
            .onErrorResume {
                ServerResponse.badRequest().build()
            }
    }
}
