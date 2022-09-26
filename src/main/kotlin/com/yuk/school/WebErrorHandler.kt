package com.yuk.school

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.web.server.WebExceptionHandler
import reactor.core.publisher.Mono

@Configuration
class WebErrorHandler {
    @Bean
    @Order(-2) // require the highest ordering
    fun exceptionHandler(): WebExceptionHandler {
        return WebExceptionHandler { exchange, ex ->
            if (ex is RuntimeException) {
                exchange.response.statusCode = HttpStatus.BAD_REQUEST
                return@WebExceptionHandler exchange.response.setComplete()
            }

            Mono.error(ex)
        }
    }
}
