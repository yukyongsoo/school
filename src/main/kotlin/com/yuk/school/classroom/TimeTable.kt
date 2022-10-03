package com.yuk.school.classroom

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document
class TimeTable(
    classRoomId: ClassRoomId
) {
    companion object {
        fun empty(classRoomId: ClassRoomId) = TimeTable(classRoomId)
    }

    @Id
    private var _id = classRoomId.toObjectId()

    @Field("lesson")
    private var _lessons = sortedSetOf<Lesson>()

    val lesson: Set<Lesson>
        get() = _lessons
}
