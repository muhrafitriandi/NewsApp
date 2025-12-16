package deps

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

private fun DependencyHandler.implementationNewsDependencies() {
    moduleImplementation(project(":features:news"))
}

private fun DependencyHandler.implementationDataDependencies() {
    moduleImplementation(project(":core:data"))
}

private fun DependencyHandler.implementationDesignSystemDependencies() {
    moduleImplementation(project(":core:designsystem"))
}

private fun DependencyHandler.implementationUtilsDependencies() {
    moduleImplementation(project(":core:utils"))
}

fun DependencyHandler.implementationAppModule() {
    implementationNewsDependencies()
}

fun DependencyHandler.implementationDataModule() {
    implementationUtilsDependencies()
}

fun DependencyHandler.implementationNewsModule() {
    implementationDataDependencies()
    implementationDesignSystemDependencies()
    implementationUtilsDependencies()
}