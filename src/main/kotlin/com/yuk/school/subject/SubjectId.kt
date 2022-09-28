package com.yuk.school.subject

import org.bson.types.ObjectId

data class SubjectId(
    val value: String
) {
    init {
        if (ObjectId.isValid(value).not())
            throw IllegalArgumentException()
    }

    fun toObjectId() = ObjectId(value)
}
