package com.yuk.school.teacher

import org.bson.types.ObjectId

data class TeacherId(
    val value: String
) {
    init {
        if (ObjectId.isValid(value).not())
            throw IllegalArgumentException()
    }
    fun toObjectId() = ObjectId(value)
}
