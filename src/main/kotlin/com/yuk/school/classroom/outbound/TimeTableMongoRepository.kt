package com.yuk.school.classroom.outbound

import com.yuk.school.classroom.ClassRoomId
import com.yuk.school.classroom.TimeTable
import com.yuk.school.classroom.TimeTableRepository
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface TimeTableMongoRepository : ReactiveMongoRepository<TimeTable, ObjectId>, TimeTableRepository {
    override suspend fun new(timeTable: TimeTable): TimeTable? {
        return save(timeTable).awaitSingleOrNull()
    }

    override suspend fun getOrNull(classRoomId: ClassRoomId): TimeTable? {
        return findById(classRoomId.toObjectId()).awaitSingleOrNull()
    }
}
