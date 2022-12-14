package com.yuk.school.teacher

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Teacher(
    val name: String
) {
    @Id
    private var _id: ObjectId = ObjectId.get()

    val id
        get() = TeacherId(_id.toString())
}
