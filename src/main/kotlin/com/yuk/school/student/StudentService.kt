package com.yuk.school.student

import com.yuk.school.classroom.ClassRoomId
import com.yuk.school.classroom.ClassRoomService
import org.springframework.stereotype.Service

@Service
class StudentService(
    private val studentRepository: StudentRepository,
    private val classRoomService: ClassRoomService
) {
    suspend fun save(name: String, classRoomId: ClassRoomId): Student {
        if (classRoomService.exist(classRoomId).not())
            throw IllegalArgumentException()

        val entity = Student(
            name,
            classRoomId
        )

        return studentRepository.save(entity)
            ?: throw RuntimeException()
    }

    suspend fun get(studentId: StudentId): Student {
        return studentRepository.getOrNull(studentId)
            ?: throw RuntimeException()
    }
}
