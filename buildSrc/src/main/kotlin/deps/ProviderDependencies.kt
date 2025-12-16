package deps

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementationAndroidX() {
    implementation(Dependencies.ANDROIDX_CORE_KTX)
    implementation(Dependencies.ANDROIDX_LIFECYCLE_RUNTIME_KTX)
    implementation(Dependencies.ANDROIDX_ACTIVITY_COMPOSE)
    implementation(Dependencies.ANDROIDX_COMPOSE_UI)
    implementation(Dependencies.ANDROIDX_COMPOSE_UI_GRAPHICS)
    implementation(Dependencies.ANDROIDX_COMPOSE_UI_TOOLING_PREVIEW)
    implementation(Dependencies.ANDROIDX_COMPOSE_MATERIAL3)
}

private fun DependencyHandler.implementationHilt() {
    implementation(Dependencies.HILT_ANDROID)
    implementation(Dependencies.HILT_NAVIGATION_COMPOSE)
    implementation(Dependencies.HILT_WORK)
    kapt(Dependencies.HILT_COMPILER)
    kapt(Dependencies.HILT_ANDROID_GRADLE_PLUGIN)
    kapt(Dependencies.HILT_COMPILER_ANDROID)
}

private fun DependencyHandler.implementationRetrofit() {
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_CONVERTER_GSON)
}

private fun DependencyHandler.implementationOkHttp() {
    implementation(Dependencies.OKHTTP)
    implementation(Dependencies.OKHTTP_LOGGING_INTERCEPTOR)
}

private fun DependencyHandler.implementationRoom() {
    implementation(Dependencies.ROOM_KTX)
    implementation(Dependencies.ROOM_RUNTIME)
    kapt(Dependencies.ROOM_COMPILER)
}

fun DependencyHandler.implementationKotlinx() {
    implementation(Dependencies.KOTLINX_SERIALIZATION_JSON)
}

fun DependencyHandler.implementationNetwork() {
    implementationRetrofit()
    implementationOkHttp()
}

fun DependencyHandler.implementationLocal() {
    implementationRoom()
}

fun DependencyHandler.implementationDI() {
    implementationHilt()
}

fun DependencyHandler.implementationChucker() {
    debugImplementation(Dependencies.CHUCKER)
    releaseImplementation(Dependencies.CHUCKER_NO_OP)
}

fun DependencyHandler.implementationPaging() {
    implementation(Dependencies.PAGING_RUNTIME)
    implementation(Dependencies.PAGING_COMPOSE)
}

fun DependencyHandler.implementationCoil() {
    implementation(Dependencies.COIL_COMPOSE)
}