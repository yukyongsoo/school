package com.yuk.school.teacher

import org.springframework.stereotype.Repository

@Repository
interface TeacherRepository {
    suspend fun new(entity: Teacher): Teacher?
    suspend fun getOrNull(teacherId: TeacherId): Teacher?
}
