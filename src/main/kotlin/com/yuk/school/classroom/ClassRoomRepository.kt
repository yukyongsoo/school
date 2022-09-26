package com.yuk.school.classroom

import com.yuk.school.classroom.outbound.ClassRoomMongoRepository
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class ClassRoomRepository(
    private val repository: ClassRoomMongoRepository
) {
     suspend fun save(entity: ClassRoom): ClassRoom? {
        return repository.save(entity).awaitFirstOrNull()
    }

    suspend fun get(classRoomId: ClassRoomId): ClassRoom? {
        return repository.findById(classRoomId.value).awaitFirstOrNull()
    }
}