import config.plugins.MainGradlePlugin
import deps.implementationDI
import deps.implementationIntegrationTest
import deps.implementationNetwork
import deps.implementationUnitTest

plugins {
    id(config.plugins.PluginsConfig.ANDROID_LIBRARY)
    id(config.plugins.PluginsConfig.KOTLIN_KAPT)
}

apply<MainGradlePlugin>()

android {
    namespace = "com.yandey.utils"
}

dependencies {
    implementationNetwork()
    implementationDI()
    implementationUnitTest()
    implementationIntegrationTest()
}