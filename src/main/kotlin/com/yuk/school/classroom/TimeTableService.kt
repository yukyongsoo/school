package com.yuk.school.classroom

import org.springframework.stereotype.Service

@Service
class TimeTableService(
    private val timeTableRepository: TimeTableRepository
) {
    suspend fun save(classRoomId: ClassRoomId): TimeTable {
        if (timeTableRepository.getOrNull(classRoomId) != null)
            throw IllegalStateException()

        return timeTableRepository.new(TimeTable.empty(classRoomId))
            ?: throw RuntimeException()
    }

    suspend fun get(classRoomId: ClassRoomId): TimeTable {
        return timeTableRepository.getOrNull(classRoomId)
            ?: throw RuntimeException()
    }

    suspend fun addLesson(classRoomId: ClassRoomId, newLesson: Lesson) {
        val timeTable = get(classRoomId)
        timeTable.addLesson(newLesson)
        timeTableRepository.new(timeTable)
    }
}
