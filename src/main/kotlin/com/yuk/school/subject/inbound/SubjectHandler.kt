package com.yuk.school.subject.inbound

import com.yuk.school.subject.SubjectId
import com.yuk.school.subject.SubjectService
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait
import org.springframework.web.reactive.function.server.coRouter

@Component
class SubjectHandler(
    private val subjectService: SubjectService
) {
    @Bean
    fun route() = coRouter {
        "/subject".nest {
            POST("", ::save)
            GET("/{id}", ::get)
        }
    }

    suspend fun save(request: ServerRequest): ServerResponse {
        subjectService.save()

        return ServerResponse.ok().buildAndAwait()
    }

    suspend fun get(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id")
        val subjectId = SubjectId(id)

        val subject = subjectService.get(subjectId)

        return ServerResponse.ok().bodyValueAndAwait(subject)
    }
}
