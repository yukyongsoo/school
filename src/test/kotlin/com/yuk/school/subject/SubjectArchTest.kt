package com.yuk.school.subject

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.library.Architectures.onionArchitecture
import org.junit.jupiter.api.Test

class SubjectArchTest {
    private val serviceAndDomain = "com.yuk.school.subject.."
    private val outbound = "com.yuk.school.subject.outbound.."
    private val inbound = "com.yuk.school.subject.inbound.."

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
