package com.yuk.school.classroom

import com.yuk.school.classroom.outbound.TimeTableMongoRepository
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.stereotype.Repository

@Repository
class TimeTableRepository(
    private val timeTableMongoRepository: TimeTableMongoRepository
) {
    suspend fun save(timeTable: TimeTable): TimeTable? {
        return timeTableMongoRepository.save(timeTable).awaitSingleOrNull()
    }

    suspend fun getOrNull(classRoomId: ClassRoomId): TimeTable? {
        return timeTableMongoRepository.findById(classRoomId.toObjectId()).awaitSingleOrNull()
    }
}
