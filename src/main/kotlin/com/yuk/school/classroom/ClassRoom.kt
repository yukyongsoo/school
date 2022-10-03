package com.yuk.school.classroom

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class ClassRoom(
    val grade: Int,
    val name: String
) {
    @Id
    private var _id: ObjectId = ObjectId.get()

    val id
        get() = ClassRoomId(_id.toString())
}
