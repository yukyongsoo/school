package com.yuk.school.student

import com.yuk.school.classroom.ClassRoomId
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document
class Student(
    val name: String,
    classroomId: ClassRoomId
) {
    @Id
    private var _id: ObjectId = ObjectId.get()

    @Transient
    val id = StudentId(_id.toString())

    @Field("classRoomId")
    private var _classRoomId = classroomId.value

    @Transient
    val classroomId = ClassRoomId(_classRoomId)
}
