package deps

object Dependencies {
    const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:${DependenciesVersion.CORE_KTX}"
    const val ANDROIDX_LIFECYCLE_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:${DependenciesVersion.LIFECYCLE_RUNTIME_KTX}"
    const val ANDROIDX_ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${DependenciesVersion.ACTIVITY_COMPOSE}"
    const val ANDROIDX_COMPOSE_UI = "androidx.compose.ui:ui:${DependenciesVersion.COMPOSE_UI}"
    const val ANDROIDX_COMPOSE_UI_GRAPHICS = "androidx.compose.ui:ui-graphics:${DependenciesVersion.COMPOSE_UI}"
    const val ANDROIDX_COMPOSE_UI_TOOLING = "androidx.compose.ui:ui-tooling:${DependenciesVersion.COMPOSE_UI}"
    const val ANDROIDX_COMPOSE_UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview:${DependenciesVersion.COMPOSE_UI}"
    const val ANDROIDX_COMPOSE_MATERIAL3 = "androidx.compose.material3:material3:${DependenciesVersion.COMPOSE_MATERIAL3}"
    const val ANDROIDX_COMPOSE_MATERIAL_ICONS = "androidx.compose.material:material-icons-extended:${DependenciesVersion.COMPOSE_MATERIAL_ICONS}"

    // DI
    const val HILT_ANDROID = "com.google.dagger:hilt-android:${DependenciesVersion.HILT}"
    const val HILT_COMPILER = "com.google.dagger:hilt-compiler:${DependenciesVersion.HILT}"
    const val HILT_ANDROID_GRADLE_PLUGIN = "com.google.dagger:hilt-android-gradle-plugin:${DependenciesVersion.HILT}"
    const val HILT_NAVIGATION_COMPOSE = "androidx.hilt:hilt-navigation-compose:${DependenciesVersion.HILT_COMPOSE}"
    const val HILT_WORK = "androidx.hilt:hilt-work:${DependenciesVersion.HILT_COMPOSE}"
    const val HILT_COMPILER_ANDROID = "androidx.hilt:hilt-compiler:${DependenciesVersion.HILT_COMPOSE}"

    // Networking
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${DependenciesVersion.RETROFIT}"
    const val RETROFIT_CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${DependenciesVersion.RETROFIT}"
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${DependenciesVersion.OKHTTP}"
    const val OKHTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${DependenciesVersion.OKHTTP}"

    // Local
    const val ROOM_RUNTIME = "androidx.room:room-runtime:${DependenciesVersion.ROOM}"
    const val ROOM_KTX = "androidx.room:room-ktx:${DependenciesVersion.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${DependenciesVersion.ROOM}"

    // KotlinX
    const val KOTLINX_SERIALIZATION_JSON = "org.jetbrains.kotlinx:kotlinx-serialization-json:${DependenciesVersion.KOTLINX_SERIALIZATION_JSON}"

    // DataStore
    const val ANDROIDX_DATASTORE = "androidx.datastore:datastore:${DependenciesVersion.DATASTORE}"
    const val PROTOBUF_JAVA_LITE = "com.google.protobuf:protobuf-javalite:${DependenciesVersion.PROTOBUF_JAVA_LITE}"

    // Chucker
    const val CHUCKER = "com.github.chuckerteam.chucker:library:${DependenciesVersion.CHUCKER}"
    const val CHUCKER_NO_OP = "com.github.chuckerteam.chucker:library-no-op:${DependenciesVersion.CHUCKER}"

    // Paging
    const val PAGING_RUNTIME = "androidx.paging:paging-runtime:${DependenciesVersion.PAGING}"
    const val PAGING_COMPOSE = "androidx.paging:paging-compose:${DependenciesVersion.PAGING}"

    // Coil
    const val COIL_COMPOSE = "io.coil-kt:coil-compose:${DependenciesVersion.COIL}"
}