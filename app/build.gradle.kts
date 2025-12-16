import config.build.BuildConfig
import config.plugins.MainGradlePlugin
import deps.implementationAndroidX
import deps.implementationAppModule
import deps.implementationDI
import deps.implementationIntegrationTest
import deps.implementationUnitTest

plugins {
    id(config.plugins.PluginsConfig.ANDROID_APPLICATION)
    id(config.plugins.PluginsConfig.KOTLIN_KAPT)
    id(config.plugins.PluginsConfig.DAGGER_HILT)
}

apply<MainGradlePlugin>()

android {
    namespace = BuildConfig.APPLICATION_ID
}

dependencies {
    implementationAppModule()

    implementationAndroidX()
    implementationDI()
    implementationUnitTest()
    implementationIntegrationTest()
}