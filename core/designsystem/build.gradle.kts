import config.plugins.MainGradlePlugin
import deps.implementationAndroidX
import deps.implementationPaging

plugins {
    id(config.plugins.PluginsConfig.ANDROID_LIBRARY)
}

apply<MainGradlePlugin>()

android {
    namespace = "com.yandey.designsystem"
}

dependencies {
    implementationAndroidX()
    implementationPaging()
}