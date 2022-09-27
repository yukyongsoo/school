package com.yuk.school.classroom

import org.bson.types.ObjectId

data class ClassRoomId(
    val value: String
) {
    companion object {
        fun fromString(id: String): ClassRoomId {
            if(ObjectId.isValid(id).not())
                throw IllegalArgumentException()

            return ClassRoomId(id)
        }
    }

    fun toObjectId() = ObjectId(value)
}
