package com.yuk.school.classroom

import com.yuk.school.subject.SubjectId
import com.yuk.school.teacher.TeacherId
import java.time.LocalTime

data class Lesson(
    private val teacherId: TeacherId,
    private val subjectId: SubjectId,
    val lessonTime: LessonTime
) : Comparable<Lesson> {
    override fun compareTo(other: Lesson): Int {
        return lessonTime.compareTo(other.lessonTime)
    }

    fun inRange(newLesson: Lesson) {
        TODO("Not yet implemented")
    }
}

data class LessonTime(
    val start: LocalTime,
    val end: LocalTime
) : Comparable<LessonTime> {
    companion object {
        private val schoolStart = LocalTime.of(9, 0)
        private val schoolEnd = LocalTime.of(16, 0)
    }

    init {
        if (start.isAfter(end))
            throw IllegalArgumentException()

        if (start.plusHours(1) != end)
            throw IllegalArgumentException()

        if (start.isBefore(schoolStart) && start.plusHours(1).isAfter(schoolEnd))
            throw IllegalArgumentException()

        if (end.isAfter(schoolEnd))
            throw IllegalArgumentException()
    }

    override fun compareTo(other: LessonTime): Int {
        return start.compareTo(other.start)
    }

    fun isOverlapping(lessonTime: LessonTime): Boolean {
        return (lessonTime.start == start && lessonTime.end == end) ||
            (lessonTime.start.isBefore(start) && lessonTime.end.isAfter(start)) ||
            (lessonTime.start.isBefore(end) && lessonTime.end.isAfter(end))
    }
}
