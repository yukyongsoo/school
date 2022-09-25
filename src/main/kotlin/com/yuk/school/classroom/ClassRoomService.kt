package com.yuk.school.classroom

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ClassRoomService(
    private val classRoomRepository: ClassRoomRepository
) {
    fun save(): Mono<ClassRoom> {
        val entity = ClassRoom(1, "1")

        return classRoomRepository.save(entity)
    }

    fun get(classRoomId: ClassRoomId): Mono<ClassRoom> {
        return classRoomRepository.get(classRoomId)
            .switchIfEmpty(
                Mono.error(IllegalArgumentException())
            )
    }
}