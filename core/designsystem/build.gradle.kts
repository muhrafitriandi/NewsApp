import config.plugins.MainGradlePlugin
import deps.implementationKotlinx

plugins {
    id(config.plugins.PluginsConfig.ANDROID_LIBRARY)
}

apply<MainGradlePlugin>()

android {
    namespace = "com.yandey.designsystem"
}

dependencies {
    implementationKotlinx()
}