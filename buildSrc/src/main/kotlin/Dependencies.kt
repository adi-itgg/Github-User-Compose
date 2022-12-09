@file:Suppress("unused")

object Deps {
    // =========================   Core    =========================
    private const val coreKTX = "androidx.core:core-ktx:$androidCoreKTX"
    // ========================= Lifecycle =========================
    private const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
    // =========================  Compose  =========================
    private const val composeActivity = "androidx.activity:activity-compose:$composeVersion"
    private const val composeUI = "androidx.compose.ui:ui:$composeVersion"
    private const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
    private const val composeMaterial3 = "androidx.compose.material3:material3:$composeMaterialVersion"
    private const val composeMaterial3Window = "androidx.compose.material3:material3-window-size-class:$composeMaterialVersion"
    // ================= Kotlinx json serialization ================
    private const val ktxJsonSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinXJsonSerialization"
    // ======================== Ktor Client ========================
    private const val ktorCore = "io.ktor:ktor-client-core:$ktorVersion"
    private const val ktorOkhttpEngine= "io.ktor:ktor-client-okhttp:$ktorVersion"
    private const val ktorContentNegotiation = "io.ktor:ktor-client-content-negotiation:$ktorVersion"
    private const val ktorSerializationJson = "io.ktor:ktor-serialization-kotlinx-json:$ktorVersion"
    private const val ktorResources = "io.ktor:ktor-client-resources:$ktorVersion"
    // ========================  Coroutine  ========================
    private const val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion"
    // ========================    Coil     ========================
    private const val coil = "io.coil-kt:coil-compose:2.2.2"
    // ========================    Koin     ========================
    private const val koinCore = "io.insert-koin:koin-core:$koinVersion"
    private const val koinAndroid = "io.insert-koin:koin-android:$koinAndroidVersion"
    private const val koinCompose = "io.insert-koin:koin-androidx-compose:$koinAndroidComposeVersion"
    // ========================    Room     ========================
    private const val room = "androidx.room:room-runtime:$roomVersion"
    private const val roomExt = "androidx.room:room-ktx:$roomVersion"
}

object DepsKapt {
    // ========================    Room     ========================
    private const val room = "androidx.room:room-compiler:$roomVersion"
}

object DepsTest {
    // =========================   Core    =========================
    private const val jUnit = "junit:junit:$jUnitVersion"
    // ========================  Coroutine  ========================
    private const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutineVersion"
    // ========================    Koin     ========================
    private const val koinTest = "io.insert-koin:koin-test:$koinVersion"
    private const val koinJunit4 = "io.insert-koin:koin-test-junit4:$koinVersion"
    // ========================    Mock     ========================
    private const val mock = "org.mockito:mockito-core:$mockitoVersion"
    // ========================    Room     ========================
    private const val room = "androidx.room:room-testing:$roomVersion"
}

object DepsAndroidTest {
    // ========================= Core Test =========================
    private const val jUnitExt = "androidx.test.ext:junit:$androidTestExtVersion"
    private const val espresso = "androidx.test.espresso:espresso-core:$androidEspresso"
    // =========================  Compose  =========================
    private const val composeUI = "androidx.compose.ui:ui-test-junit4:$composeVersion"
    // ========================    Koin     ========================
    private const val koinTest = "io.insert-koin:koin-test:$koinVersion"
}

object DepsDebug {
    // =========================  Compose  =========================
    private const val composeUITooling = "androidx.compose.ui:ui-tooling:$composeVersion"
    private const val composeUIManifest = "androidx.compose.ui:ui-test-manifest:$composeVersion"
}