package com.yuk.school

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@SpringBootApplication
class SchoolApplication

fun main(args: Array<String>) {
	runApplication<SchoolApplication>(*args)
}
