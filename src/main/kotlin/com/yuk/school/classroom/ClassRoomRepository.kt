package com.yuk.school.classroom

import org.springframework.stereotype.Repository

@Repository
interface ClassRoomRepository {
    suspend fun new(entity: ClassRoom): ClassRoom?
    suspend fun getOrNull(classRoomId: ClassRoomId): ClassRoom?
    suspend fun exist(classRoomId: ClassRoomId): Boolean
}
