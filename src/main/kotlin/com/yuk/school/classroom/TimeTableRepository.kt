package com.yuk.school.classroom

import org.springframework.stereotype.Repository

@Repository
interface TimeTableRepository {
    suspend fun new(timeTable: TimeTable): TimeTable?
    suspend fun getOrNull(classRoomId: ClassRoomId): TimeTable?
}
