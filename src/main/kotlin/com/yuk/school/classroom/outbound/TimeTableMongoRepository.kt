package com.yuk.school.classroom.outbound

import com.yuk.school.classroom.TimeTable
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface TimeTableMongoRepository : ReactiveMongoRepository<TimeTable, ObjectId>
