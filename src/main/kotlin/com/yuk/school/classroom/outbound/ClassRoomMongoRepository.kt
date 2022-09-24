package com.yuk.school.classroom.outbound

import com.yuk.school.classroom.ClassRoom
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ClassRoomMongoRepository: ReactiveMongoRepository<ClassRoom, ObjectId>