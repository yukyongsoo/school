package com.yuk.school.classroom

import com.yuk.school.classroom.outbound.ClassRoomMongoRepository
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.stereotype.Repository

@Repository
class ClassRoomRepository(
    private val repository: ClassRoomMongoRepository
) {
    suspend fun save(entity: ClassRoom): ClassRoom? {
        return repository.save(entity).awaitFirstOrNull()
    }

    suspend fun getOrNull(classRoomId: ClassRoomId): ClassRoom? {
        return repository.findById(classRoomId.value).awaitFirstOrNull()
    }
}
