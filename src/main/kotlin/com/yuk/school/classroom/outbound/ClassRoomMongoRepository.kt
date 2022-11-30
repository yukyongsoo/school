package com.yuk.school.classroom.outbound

import com.yuk.school.classroom.ClassRoom
import com.yuk.school.classroom.ClassRoomId
import com.yuk.school.classroom.ClassRoomRepository
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ClassRoomMongoRepository : ReactiveMongoRepository<ClassRoom, ObjectId>, ClassRoomRepository {
    override suspend fun new(entity: ClassRoom): ClassRoom? {
        return save(entity).awaitFirstOrNull()
    }

    override suspend fun getOrNull(classRoomId: ClassRoomId): ClassRoom? {
        return findById(classRoomId.toObjectId()).awaitFirstOrNull()
    }

    override suspend fun exist(classRoomId: ClassRoomId): Boolean {
        return existsById(classRoomId.toObjectId()).awaitFirst()
    }
}
