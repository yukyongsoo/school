package com.yuk.school.classroom

import com.yuk.school.subject.SubjectId
import com.yuk.school.teacher.TeacherId
import java.time.LocalTime

data class Lesson(
    private val teacherId: TeacherId,
    private val subjectId: SubjectId,
    val lessonTime: LessonTime
)

data class LessonTime(
    val start: LocalTime,
    val end: LocalTime
) {
    companion object {
        private val schoolStart = LocalTime.of(9, 0)
        private val schoolEnd = LocalTime.of(16, 0)
    }

    init {
        if (start.isBefore(end))
            throw IllegalArgumentException()

        if (start.plusHours(1) != end)
            throw IllegalArgumentException()

        if (start.isAfter(schoolStart) && start.plusHours(1).isBefore(schoolEnd))
            throw IllegalArgumentException()

        if (end.isBefore(schoolEnd))
            throw IllegalArgumentException()
    }
}
