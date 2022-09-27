package com.yuk.school.student

import org.bson.types.ObjectId

data class StudentId(
    val value: String
) {
    fun toObjectId() = ObjectId(value)
}
