package com.yuk.school.student.outbound

import com.yuk.school.student.Student
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface StudentMongoRepository : ReactiveMongoRepository<Student, ObjectId>
