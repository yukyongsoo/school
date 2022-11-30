package com.yuk.school.classroom

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.library.Architectures.onionArchitecture
import org.junit.jupiter.api.Test

class ClassroomArchTest {
    private val serviceAndDomain = "com.yuk.school.classroom.."
    private val outbound = "com.yuk.school.classroom.outbound.."
    private val inbound = "com.yuk.school.classroom.inbound.."

    @Test
    fun `layer 테스트`() {
        val classes = ClassFileImporter().importPackages(serviceAndDomain)

        onionArchitecture()
            .ensureAllClassesAreContainedInArchitecture()
            .applicationServices(serviceAndDomain)
            .domainServices(serviceAndDomain)
            .domainModels(serviceAndDomain)
            .adapter("outbound", outbound)
            .adapter("inbound", inbound)
            .check(classes)
    }
}
