package com.yuk.school.teacher

import org.springframework.stereotype.Service

@Service
class TeacherService(
    private val teacherRepository: TeacherRepository
) {
    suspend fun save(): Teacher {
        val entity = Teacher("선생님")

        return teacherRepository.save(entity)
            ?: throw RuntimeException()
    }

    suspend fun get(teacherId: TeacherId): Teacher {
        return teacherRepository.getOrNull(teacherId)
            ?: throw RuntimeException()
    }
}
