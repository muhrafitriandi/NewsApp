import config.plugins.MainGradlePlugin
import deps.implementationDI

plugins {
    id(config.plugins.PluginsConfig.ANDROID_LIBRARY)
    id(config.plugins.PluginsConfig.KOTLIN_KAPT)
}

apply<MainGradlePlugin>()

android {
    namespace = "com.yandey.utils"
}

dependencies {
    implementationDI()
}