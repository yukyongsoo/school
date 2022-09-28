package com.yuk.school.teacher

import com.yuk.school.teacher.outbound.TeacherMongoRepository
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.stereotype.Repository

@Repository
class TeacherRepository(
    private val teacherMongoRepository: TeacherMongoRepository
) {

    suspend fun save(entity: Teacher): Teacher? {
        return teacherMongoRepository.save(entity).awaitSingleOrNull()
    }

    suspend fun getOrNull(teacherId: TeacherId): Teacher? {
        return teacherMongoRepository.findById(teacherId.toObjectId()).awaitSingleOrNull()
    }
}
