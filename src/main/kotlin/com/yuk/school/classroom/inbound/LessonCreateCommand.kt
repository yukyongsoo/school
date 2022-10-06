package com.yuk.school.classroom.inbound

import com.yuk.school.classroom.Lesson
import com.yuk.school.classroom.LessonTime
import com.yuk.school.subject.SubjectId
import com.yuk.school.teacher.TeacherId
import java.time.LocalTime

data class LessonCreateCommand(
    val teacherId: String,
    val subjectId: String,
    val startTime: LocalTime,
    val endTime: LocalTime
) {
    fun toLesson(): Lesson {
        return Lesson(
            TeacherId(teacherId),
            SubjectId(subjectId),
            LessonTime(startTime, endTime)
        )
    }
}
