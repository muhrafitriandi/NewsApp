import config.plugins.MainGradlePlugin
import deps.implementationChucker
import deps.implementationDI
import deps.implementationDataModule
import deps.implementationIntegrationTest
import deps.implementationKotlinx
import deps.implementationNetwork
import deps.implementationUnitTest

plugins {
    id(config.plugins.PluginsConfig.ANDROID_LIBRARY)
    id(config.plugins.PluginsConfig.KOTLIN_KAPT)
}

apply<MainGradlePlugin>()

android {
    namespace = "com.yandey.data"
}

dependencies {
    implementationDataModule()

    implementationKotlinx()
    implementationNetwork()
    implementationDI()
    implementationChucker()
    implementationUnitTest()
    implementationIntegrationTest()
}