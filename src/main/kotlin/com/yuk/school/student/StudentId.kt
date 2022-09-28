package com.yuk.school.student

import org.bson.types.ObjectId

data class StudentId(
    val value: String
) {
    init {
        if (ObjectId.isValid(value).not())
            throw IllegalArgumentException()
    }
    fun toObjectId() = ObjectId(value)
}
