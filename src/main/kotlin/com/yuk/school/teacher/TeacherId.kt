package com.yuk.school.teacher

import org.bson.types.ObjectId

data class TeacherId(
    val value: String
) {
    fun toObjectId() = ObjectId(value)

}
