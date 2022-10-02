package com.yuk.school.classroom

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class TimeTable(
    classRoomId: ClassRoomId
) {
    companion object {
        fun empty(classRoomId: ClassRoomId) = TimeTable(classRoomId)
    }

    @Id
    private var _id = classRoomId.toObjectId()

    var lessons = listOf<Lesson>()
        private set
}
