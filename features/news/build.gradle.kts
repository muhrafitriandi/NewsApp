import config.plugins.MainGradlePlugin
import deps.implementationAndroidX
import deps.implementationCoil
import deps.implementationDI
import deps.implementationNetwork
import deps.implementationNewsModule
import deps.implementationPaging

plugins {
    id(config.plugins.PluginsConfig.ANDROID_LIBRARY)
    id(config.plugins.PluginsConfig.KOTLIN_KAPT)
}

apply<MainGradlePlugin>()

android {
    namespace = "com.yandey.news"
}

dependencies {
    implementationNewsModule()

    implementationAndroidX()
    implementationNetwork()
    implementationDI()
    implementationPaging()
    implementationCoil()
}