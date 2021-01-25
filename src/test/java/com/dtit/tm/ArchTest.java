package com.dtit.tm;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.dtit.tm");

        noClasses()
            .that()
                .resideInAnyPackage("com.dtit.tm.service..")
            .or()
                .resideInAnyPackage("com.dtit.tm.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.dtit.tm.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
