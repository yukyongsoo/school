package com.yuk.school.classroom

import org.springframework.stereotype.Service

@Service
class ClassRoomService(
    private val classRoomRepository: ClassRoomRepository
) {
    suspend fun save(): ClassRoom {
        val entity = ClassRoom(1, "1")

        val saved = classRoomRepository.save(entity)
            ?: throw RuntimeException()

        return saved
    }

    suspend fun get(classRoomId: ClassRoomId): ClassRoom {
        val classRoom = classRoomRepository.get(classRoomId)
            ?: throw RuntimeException()

        return classRoom
    }
}
