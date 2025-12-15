package deps

import org.gradle.api.artifacts.dsl.DependencyHandler
import test.TestDependencies

private fun DependencyHandler.implementationTesting() {
    testImplementation(TestDependencies.JUNIT)
    androidTestImplementation(TestDependencies.ANDROIDX_JUNIT)
    androidTestImplementation(TestDependencies.ANDROIDX_ESPRESSO_CORE)
    androidTestImplementation(TestDependencies.ANDROIDX_COMPOSE_UI_TEST_JUNIT4)
}

private fun DependencyHandler.implementationDebugTools() {
    debugImplementation(Dependencies.ANDROIDX_COMPOSE_UI_TOOLING)
    debugImplementation(TestDependencies.ANDROIDX_COMPOSE_UI_TEST_MANIFEST)
}

fun DependencyHandler.implementationUnitTest() {
    implementationTesting()
}

fun DependencyHandler.implementationIntegrationTest() {
    implementationDebugTools()
}