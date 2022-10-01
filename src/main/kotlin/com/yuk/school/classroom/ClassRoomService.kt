package com.yuk.school.classroom

import org.springframework.stereotype.Service

@Service
class ClassRoomService(
    private val classRoomRepository: ClassRoomRepository
) {
    suspend fun save(grade: Int, name: String): ClassRoom {
        val entity = ClassRoom(grade, name)

        val saved = classRoomRepository.save(entity)
            ?: throw RuntimeException()

        return saved
    }

    suspend fun get(classRoomId: ClassRoomId): ClassRoom {
        val classRoom = classRoomRepository.getOrNull(classRoomId)
            ?: throw RuntimeException()

        return classRoom
    }

    suspend fun exist(classRoomId: ClassRoomId): Boolean {
        return classRoomRepository.exist(classRoomId)
    }
}
