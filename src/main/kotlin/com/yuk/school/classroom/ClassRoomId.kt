package com.yuk.school.classroom

import org.bson.types.ObjectId

data class ClassRoomId(
    val value: ObjectId
) {
    companion object {
        fun fromString(id: String): ClassRoomId {
            return ClassRoomId(
                ObjectId(id)
            )
        }
    }
}