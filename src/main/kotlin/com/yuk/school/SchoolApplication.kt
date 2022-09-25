package com.yuk.school

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.models.OpenAPI
import org.springdoc.core.GroupedOpenApi
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean


@SpringBootApplication
@OpenAPIDefinition(info = Info(title = "School Demo", version = "1.0", description = "Documentation APIs v1.0"))
class SchoolApplication

fun main(args: Array<String>) {
	runApplication<SchoolApplication>(*args)
}

