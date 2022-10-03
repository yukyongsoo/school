package com.yuk.school.classroom

import com.yuk.school.getObjectId
import com.yuk.school.subject.SubjectId
import com.yuk.school.teacher.TeacherId
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalTime

class TimeTableTest {
    @Test
    fun `수업 추가`() {
        val table = TimeTable.empty(ClassRoomId.fromString(getObjectId()))

        table.addLesson(
            Lesson(
                TeacherId(getObjectId()),
                SubjectId(getObjectId()),
                LessonTime(
                    LocalTime.of(9, 0, 0),
                    LocalTime.of(10, 0, 0),
                )
            )
        )
    }

    @Test
    fun `겹치지 않는 수업 추가`() {
        val table = TimeTable.empty(ClassRoomId.fromString(getObjectId()))

        table.addLesson(
            Lesson(
                TeacherId(getObjectId()),
                SubjectId(getObjectId()),
                LessonTime(
                    LocalTime.of(9, 0, 0),
                    LocalTime.of(10, 0, 0),
                )
            )
        )

        table.addLesson(
            Lesson(
                TeacherId(getObjectId()),
                SubjectId(getObjectId()),
                LessonTime(
                    LocalTime.of(10, 0, 0),
                    LocalTime.of(11, 0, 0),
                )
            )
        )
    }

    @Test
    fun `겹치지 수업 추가 시 실패`() {
        val table = TimeTable.empty(ClassRoomId.fromString(getObjectId()))

        table.addLesson(
            Lesson(
                TeacherId(getObjectId()),
                SubjectId(getObjectId()),
                LessonTime(
                    LocalTime.of(9, 0, 0),
                    LocalTime.of(10, 0, 0),
                )
            )
        )

        assertThrows<IllegalArgumentException> {
            table.addLesson(
                Lesson(
                    TeacherId(getObjectId()),
                    SubjectId(getObjectId()),
                    LessonTime(
                        LocalTime.of(9, 30, 0),
                        LocalTime.of(10, 30, 0),
                    )
                )
            )
        }
    }
}
