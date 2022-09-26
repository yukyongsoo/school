package com.yuk.school

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@OpenAPIDefinition(info = Info(title = "School Demo", version = "1.0", description = "Documentation APIs v1.0"))
class SchoolApplication

fun main(args: Array<String>) {
    runApplication<SchoolApplication>(*args)
}
