package com.yuk.school

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class RouterConfig {
    @Bean
    fun routerExample(): RouterFunction<ServerResponse> {
        return RouterFunctions.route()
            .GET {
                ServerResponse.ok().build()
            }
            .build()
    }
}