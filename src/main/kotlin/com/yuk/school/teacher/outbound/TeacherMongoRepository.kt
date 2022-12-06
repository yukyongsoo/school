package com.yuk.school.teacher.outbound

import com.yuk.school.teacher.Teacher
import com.yuk.school.teacher.TeacherId
import com.yuk.school.teacher.TeacherRepository
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface TeacherMongoRepository : ReactiveMongoRepository<Teacher, ObjectId>, TeacherRepository {
    override suspend fun new(entity: Teacher): Teacher? {
        return save(entity).awaitSingleOrNull()
    }

    override suspend fun getOrNull(teacherId: TeacherId): Teacher? {
        return findById(teacherId.toObjectId()).awaitSingleOrNull()
    }
}
