package com.yuk.school.classroom

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "class_room")
class ClassRoom(
    val grade: Int,
    val name: String
) {
    @Id
    private var id: Long = 0



}