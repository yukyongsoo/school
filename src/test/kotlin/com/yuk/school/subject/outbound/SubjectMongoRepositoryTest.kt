package com.yuk.school.subject.outbound

import com.yuk.school.subject.Subject
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
class SubjectMongoRepositoryTest {
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
    private lateinit var subjectMongoRepository: SubjectMongoRepository

    @Test
    fun `과목 생성`() {
        val entity = Subject("과목")

        StepVerifier.create(
            subjectMongoRepository.save(entity)
        )
            .assertNext { }
            .verifyComplete()
    }

    @Test
    fun `과목 조회`() {
        StepVerifier.create(
            subjectMongoRepository.findById(ObjectId("507f1f77bcf86cd799439011"))
        )
            .verifyComplete()
    }
}
