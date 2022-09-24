package com.yuk.school.classroom

import com.yuk.school.classroom.outbound.ClassRoomMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class ClassRoomRepository(
    private val repository: ClassRoomMongoRepository
) {
    fun save(entity: ClassRoom): Mono<ClassRoom> {
        return repository.save(entity)
    }
}