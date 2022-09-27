package com.yuk.school.student.outbound

import com.yuk.school.student.Student
import org.bson.types.ObjectId
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import reactor.test.StepVerifier

@DataMongoTest
@Testcontainers
class StudentMongoRepositoryTest {
    companion object {
        @Container
        val mongoDBContainer = MongoDBContainer(DockerImageName.parse("mongo:5.0.12"))

        @DynamicPropertySource
        @JvmStatic
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.mongodb.port", mongoDBContainer::getFirstMappedPort)
        }
    }

    @Autowired
    private lateinit var studentMongoRepository: StudentMongoRepository

    @Test
    fun `신규 반 저장`() {
        val newStudent = Student(
            "학생명"
        )

        StepVerifier.create(studentMongoRepository.save(newStudent))
            .assertNext { }
            .verifyComplete()
    }

    @Test
    fun `반 조회`() {
        StepVerifier.create(studentMongoRepository.findById(ObjectId("507f1f77bcf86cd799439011")))
            .verifyComplete()
    }
}
