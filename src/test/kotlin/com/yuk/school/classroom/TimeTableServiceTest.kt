package com.yuk.school.classroom

import com.yuk.school.getObjectId
import com.yuk.school.subject.SubjectId
import com.yuk.school.teacher.TeacherId
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import java.time.LocalTime

@ExtendWith(MockitoExtension::class)
class TimeTableServiceTest {
    @Mock
    private lateinit var timeTableRepository: TimeTableRepository

    @InjectMocks
    private lateinit var timeTableService: TimeTableService

    @Test
    fun `반 시간표 생성`() {
        runBlocking {
            given(timeTableRepository.save(any())).willReturn(
                TimeTable.empty(ClassRoomId.fromString(getObjectId()))
            )

            timeTableService.save(ClassRoomId.fromString(getObjectId()))
        }
    }

    @Test
    fun `반 시간표 조회`() {
        runBlocking {
            given(timeTableRepository.getOrNull(any())).willReturn(
                TimeTable.empty(ClassRoomId.fromString(getObjectId()))
            )

            timeTableService.get(ClassRoomId.fromString(getObjectId()))
        }
    }

    @Test
    fun `수업 추가`() {
        runBlocking {
            given(timeTableRepository.getOrNull(any())).willReturn(
                TimeTable.empty(ClassRoomId.fromString(getObjectId()))
            )

            given(timeTableRepository.save(any())).willReturn(
                TimeTable.empty(ClassRoomId.fromString(getObjectId()))
            )

            timeTableService.addLesson(
                ClassRoomId.fromString(getObjectId()),
                Lesson(
                    TeacherId(getObjectId()),
                    SubjectId(getObjectId()),
                    LessonTime(
                        LocalTime.of(9, 0),
                        LocalTime.of(10, 0),
                    )
                )
            )
        }
    }
}
