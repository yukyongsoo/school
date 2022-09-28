package com.yuk.school.classroom

import org.bson.types.ObjectId

data class ClassRoomId(
    val value: String
) {
    init {
        if (ObjectId.isValid(value).not())
            throw IllegalArgumentException()
    }

    companion object {
        fun fromString(id: String): ClassRoomId {
            return ClassRoomId(id)
        }
    }

    fun toObjectId() = ObjectId(value)
}
