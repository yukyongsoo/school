package com.yuk.school.classroom.outbound

import com.yuk.school.classroom.ClassRoom
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import reactor.test.StepVerifier


@DataMongoTest
@Testcontainers
class ClassRoomMongoRepositoryTest {
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
    private lateinit var classRoomMongoRepository: ClassRoomMongoRepository

    @Test
    fun `신규 반 저장`() {
        val newClass = ClassRoom(
            1,
            "1"
        )

        StepVerifier.create(classRoomMongoRepository.save(newClass))
            .assertNext {  }
            .verifyComplete()
    }
}