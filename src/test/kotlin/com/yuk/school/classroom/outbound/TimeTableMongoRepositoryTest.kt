package com.yuk.school.classroom.outbound

import com.yuk.school.classroom.ClassRoomId
import com.yuk.school.classroom.TimeTable
import com.yuk.school.getObjectId
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
class TimeTableMongoRepositoryTest {
    companion object {
        @Container
        val mongoDBContainer =
            MongoDBContainer(DockerImageName.parse("mongo:5.0.12"))

        @DynamicPropertySource
        @JvmStatic
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add(
                "spring.data.mongodb.port",
                mongoDBContainer::getFirstMappedPort
            )
        }
    }

    @Autowired
    private lateinit var timeTableMongoRepository: TimeTableMongoRepository

    @Test
    fun `시간표 생성`() {
        val timeTable = TimeTable.empty(ClassRoomId.fromString(getObjectId()))

        StepVerifier.create(
            timeTableMongoRepository.save(timeTable)
        )
            .assertNext { }
            .verifyComplete()
    }

    @Test
    fun `시간표 조회`() {
        StepVerifier.create(
            timeTableMongoRepository.findById(ObjectId(getObjectId()))
        )
            .verifyComplete()
    }
}
