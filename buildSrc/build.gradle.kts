plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    api(kotlin("gradle-plugin:2.2.20"))
    implementation("com.android.tools.build:gradle:8.13.1")
    implementation("org.jetbrains.kotlin:compose-compiler-gradle-plugin:2.2.20")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.2.20")
    implementation("com.diffplug.spotless:spotless-plugin-gradle:7.2.1")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.8")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.57.2")
    implementation("org.jetbrains.kotlin:kotlin-serialization:2.2.20")
    implementation("com.google.protobuf:protobuf-gradle-plugin:0.9.5")
}