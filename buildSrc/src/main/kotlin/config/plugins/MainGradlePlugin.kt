package config.plugins

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.LibraryExtension
import config.build.BuildConfig
import config.release.ReleaseConfig
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import test.TestBuildConfig

class MainGradlePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.pluginManager.withPlugin(PluginsConfig.ANDROID_LIBRARY) {
            project.extensions.getByType(LibraryExtension::class.java).apply {
                applyPlugins(project)
                applyAndroid(project)
            }
        }

        project.pluginManager.withPlugin(PluginsConfig.ANDROID_APPLICATION) {
            project.extensions.getByType(ApplicationExtension::class.java).apply {
                applyPlugins(project)
                applyAndroid(project)
            }
        }
    }

    private fun Project.getAndroid(): Any {
        return when {
            extensions.findByType(LibraryExtension::class.java) != null -> extensions.getByType(
                LibraryExtension::class.java
            )

            extensions.findByType(ApplicationExtension::class.java) != null -> extensions.getByType(
                ApplicationExtension::class.java
            )

            else -> error("Android extension not found in project: $name")
        }
    }

    private fun CommonExtension<*, *, *, *, *, *>.setupAndroid(project: Project) {
        compileSdk = BuildConfig.COMPILE_SDK_VERSION

        defaultConfig {
            minSdk = BuildConfig.MIN_SDK_VERSION
            testInstrumentationRunner = TestBuildConfig.TEST_INSTRUMENTATION_RUNNER
        }

        when (this) {
            is LibraryExtension -> {
                buildTypes {
                    release {
                        isMinifyEnabled = false
                        proguardFiles(
                            getDefaultProguardFile(ReleaseConfig.PROGUARD_FILE),
                            ReleaseConfig.CONSUMER_PROGUARD_FILE
                        )
                    }
                }
            }

            is ApplicationExtension -> {
                buildTypes {
                    release {
                        isMinifyEnabled = false
                        proguardFiles(
                            getDefaultProguardFile(ReleaseConfig.PROGUARD_FILE),
                            ReleaseConfig.CONSUMER_PROGUARD_FILE
                        )
                    }
                }
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        setupCompilerOptions(project)

        buildFeatures {
            compose = true
            buildConfig = true
        }
    }

    private fun setupCompilerOptions(project: Project) {
        project.tasks.withType<KotlinCompile>().configureEach {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_11)
            }
        }
    }

    private fun applyPlugins(project: Project) {
        project.apply {
            plugin(PluginsConfig.KOTLIN_ANDROID)
            plugin(PluginsConfig.KOTLIN_COMPOSE)
            plugin(PluginsConfig.KOTLIN_SERIALIZATION)
        }
    }

    private fun applyAndroid(project: Project) {
        val androidExtension = project.getAndroid()

        when (androidExtension) {
            is LibraryExtension -> {
                androidExtension.apply {
                    setupAndroid(project)
                }
            }

            is ApplicationExtension -> {
                androidExtension.apply {
                    setupAndroid(project)
                }
            }
        }
    }
}