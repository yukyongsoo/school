package com.yuk.school.subject.outbound

import com.yuk.school.subject.Subject
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface SubjectMongoRepository : ReactiveMongoRepository<Subject, ObjectId>
