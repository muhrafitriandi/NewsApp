import config.plugins.MainGradlePlugin
import deps.implementationAndroidX
import deps.implementationDI
import deps.implementationNetwork
import deps.implementationNewsModule

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
}