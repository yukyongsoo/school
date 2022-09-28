package com.yuk.school.subject

import nonapi.io.github.classgraph.json.Id
import org.bson.types.ObjectId
import org.springframework.data.annotation.Transient
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Subject(
    val name: String
) {
    @Id
    private var _id = ObjectId.get()

    @Transient
    val id = SubjectId(_id.toString())
}
