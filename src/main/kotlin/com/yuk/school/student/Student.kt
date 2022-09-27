package com.yuk.school.student

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient

class Student(
    val name: String
) {
    @Id
    private var _id: ObjectId = ObjectId.get()

    @Transient
    val id = StudentId(_id.toString())
}
