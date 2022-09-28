package com.yuk.school.teacher.outbound

import com.yuk.school.teacher.Teacher
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface TeacherMongoRepository : ReactiveMongoRepository<Teacher, ObjectId>
